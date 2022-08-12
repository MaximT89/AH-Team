package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.isRefreshingFalse
import com.ahinfo.ahteam.core.extension.isRefreshingTrue
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

    override fun initView() = with(binding) {
        recyclerView.adapter = listProjectsAdapter

        btnAddProject.setOnClickListener { navigateTo(DestinationsParser.LIST_PROJECTS_TO_ADD_PROJECT.id) }

        swipeRefresh.setOnRefreshListener { viewModel.updateListProjectsData() }
    }

    override fun listenerBundleArguments() {

        readArguments<Boolean>(AddProjectFragment.RESULT_ADD_PROJECT,
            ifExist = { result ->
                readResultAndShowSnackbar(
                    result = result,
                    positiveMess = string(R.string.success_add_project),
                    negativeMess = string(R.string.fail_add_project),
                    positiveResult = {
                        viewModel.updateListProjectsData()
                    }
                )
            })

        readArguments<Boolean>(UpdateProjectFragment.RESULT_UPDATE_PROJECT,
            ifExist = { result ->
                readResultAndShowSnackbar(
                    result = result,
                    positiveMess = string(R.string.success_update_project),
                    negativeMess = string(R.string.fail_update_project),
                    positiveResult = {
                        viewModel.updateListProjectsData()
                    }
                )
            })
    }

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

    override fun initObservers() {
        viewModel.listProjectState.observe(this@ListProjectsFragment) { state ->

            when (state) {
                is ListProjectsState.Error -> {
                    isRefreshingFalse(binding.swipeRefresh)
                    loading(false)
                    showContent(false)
                }
                ListProjectsState.Loading -> {
                    loading(true)
                }
                is ListProjectsState.NoInternet -> {
                    isRefreshingFalse(binding.swipeRefresh)
                    loading(false)
                    showContent(false)
                }
                is ListProjectsState.Success -> {
                    isRefreshingFalse(binding.swipeRefresh)
                    loading(false)
                    showContent(true)
                    updateContent(state.data)
                }
                ListProjectsState.ErrorDeleteProject -> {
                    readResultAndShowSnackbar(
                        result = false,
                        positiveMess = string(R.string.success_delete_project),
                        negativeMess = string(R.string.fail_delete_project)
                    )
                }
                ListProjectsState.SuccessDeleteProject -> {
                    readResultAndShowSnackbar(
                        result = true,
                        positiveMess = string(R.string.success_delete_project),
                        negativeMess = string(R.string.fail_delete_project)
                    )
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
        if (status) isRefreshingTrue(binding.swipeRefresh)
        else isRefreshingFalse(binding.swipeRefresh)
    }

    override fun title() {
        binding.titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.LIST_PROJECT_TO_SECTIONS_APP.id) }
    }
}