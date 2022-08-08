package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentListProjectsBinding
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import com.ahinfo.ahteam.ui.screens.parser.addProject.AddProjectFragment
import com.ahinfo.ahteam.ui.screens.parser.updateProject.UpdateProjectFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProjectsFragment :
    BaseFragment<FragmentListProjectsBinding, ListProjectsViewModel>(FragmentListProjectsBinding::inflate) {

    companion object {
        const val PARSER_PROJECT_ID = "parser_project_id"
    }

    override val viewModel: ListProjectsViewModel by viewModels()

    private val listProjectsAdapter = ListProjectsAdapter()

    override fun initCallbacks() {
        listProjectsAdapter.callBackDeleteProject = { id ->
            viewModel.deleteProject(id)
        }

        listProjectsAdapter.callBackUpgradeProject = { parserProject ->
            navigateTo(
                DestinationsParser.LIST_PROJECT_TO_UPGRADE_PROJECT.id,
                bundleOf(PARSER_PROJECT_ID to parserProject)
            )
        }

        listProjectsAdapter.callBackGoDetailProject = { projectId ->
            navigateTo(
                DestinationsParser.LIST_PROJECTS_TO_DETAIL_PROJECT.id,
                bundleOf(PARSER_PROJECT_ID to projectId)
            )
        }
    }

    override fun initView() = with(binding) {
        recyclerView.adapter = listProjectsAdapter

        btnAddProject.setOnClickListener { navigateTo(DestinationsParser.LIST_PROJECTS_TO_ADD_PROJECT.id) }

        swipeRefresh.setOnRefreshListener { viewModel.updateListProjectsData() }

        if (arguments?.getBoolean(AddProjectFragment.RESULT_ADD_PROJECT) != null) {
            val result = arguments?.getBoolean(AddProjectFragment.RESULT_ADD_PROJECT)
            updatePageAndShowSnackbar(
                result = result!!,
                positiveMess = string(R.string.success_add_project),
                negativeMess = string(R.string.fail_add_project)
            )
            arguments?.remove(AddProjectFragment.RESULT_ADD_PROJECT)
        }

        if (arguments?.getBoolean(UpdateProjectFragment.RESULT_UPDATE_PROJECT) != null) {
            val result = arguments?.getBoolean(UpdateProjectFragment.RESULT_UPDATE_PROJECT)
            updatePageAndShowSnackbar(
                result = result!!,
                positiveMess = string(R.string.success_update_project),
                negativeMess = string(R.string.fail_update_project)
            )
            arguments?.remove(UpdateProjectFragment.RESULT_UPDATE_PROJECT)
        }
    }

    private fun updatePageAndShowSnackbar(
        result: Boolean,
        positiveMess: String,
        negativeMess: String
    ) {
        if (result) {
            viewModel.updateListProjectsData()
            // TODO: вывети снекбар кастомизированный зеленый
            showSnackbar(binding.root, positiveMess)
        } else {
            // TODO: вывести снекбар кастомизированный красный в котором будет ошибка
            showSnackbar(binding.root, negativeMess)
        }
    }

    override fun initObservers() {
        viewModel.listProjectState.observe(this@ListProjectsFragment) { state ->

            when (state) {
                is ListProjectsState.Error -> {
                    isRefreshingFalse()
                    loading(false)
                    showContent(false)
                }
                ListProjectsState.Loading -> {
                    loading(true)
                    showContent(false)
                }
                is ListProjectsState.NoInternet -> {
                    isRefreshingFalse()
                    loading(false)
                    showContent(false)
                }
                is ListProjectsState.Success -> {
                    isRefreshingFalse()
                    loading(false)
                    showContent(true)
                    updateContent(state.data)
                }
                ListProjectsState.ErrorDeleteProject -> {
                    // TODO: вывести снекбар где описана ошибка удаления проекта
                }
                ListProjectsState.SuccessDeleteProject -> {
                    // TODO: вывести снекбар где описано успешное удаление проекта
                }
            }
        }
    }

    private fun updateContent(data: ListProjectsGetDomain) {
        listProjectsAdapter.submitList(data.elements)
    }

    private fun showContent(status: Boolean) = with(binding) {
        if (status) recyclerView.isVisible = true
        else recyclerView.isGone = true
    }

    private fun loading(status: Boolean) {
        if (status) isRefreshingTrue()
        else isRefreshingFalse()
    }

    private fun isRefreshingFalse() {
        binding.swipeRefresh.isRefreshing = false
    }

    private fun isRefreshingTrue() {
        binding.swipeRefresh.isRefreshing = true
    }

    override fun title() {
        binding.titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.LIST_PROJECT_TO_SECTIONS_APP.id) }
    }
}