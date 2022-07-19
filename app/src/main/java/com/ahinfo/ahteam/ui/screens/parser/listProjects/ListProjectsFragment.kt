package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.clearFragmentResultListener
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.log
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentListProjectsBinding
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProjectsFragment :
    BaseFragment<FragmentListProjectsBinding, ListProjectsViewModel>(FragmentListProjectsBinding::inflate) {

    override val viewModel: ListProjectsViewModel by viewModels()

    private val listProjectsAdapter = ListProjectsAdapter()

    override fun initCallbacks() {
        listProjectsAdapter.callBackDeleteProject = { id ->
            viewModel.deleteProject(id)
        }

        listProjectsAdapter.callBackUpgradeProject = { parserProject ->
            setFragmentResult("upgrade_parser_project", bundleOf("parser_project" to parserProject))
            navigateTo(DestinationsParser.LIST_PROJECT_TO_UPGRADE_PROJECT.id)
        }

        listProjectsAdapter.callBackGoDetailProject = { parserProject ->
            setFragmentResult("detail_parser_project", bundleOf("parser_project" to parserProject))
            navigateTo(DestinationsParser.LIST_PROJECTS_TO_DETAIL_PROJECT.id)
        }
    }

    override fun initView() = with(binding) {
        recyclerView.adapter = listProjectsAdapter

        btnAddProject.setOnClickListener { navigateTo(DestinationsParser.LIST_PROJECTS_TO_ADD_PROJECT.id) }

        swipeRefresh.setOnRefreshListener {
            // TODO: данные нужно брать из префов
            viewModel.updateListProjectsData(1, 10)
        }

        setFragmentResultListener("add_project") { _, bundle ->
            val result = bundle.getBoolean("result_add_project")

            if(result) log("add_project setFragmentResultListener work")

            updatePageAndShowSnackbar(
                result = result,
                positiveMess = string(R.string.success_add_project),
                negativeMess = string(R.string.fail_add_project)
            )
        }

        setFragmentResultListener("update_project") { _, bundle ->
            val result = bundle.getBoolean("update_result")

            if(result) log("update_project setFragmentResultListener work")

            updatePageAndShowSnackbar(
                result = result,
                positiveMess = string(R.string.success_update_project),
                negativeMess = string(R.string.fail_update_project)
            )

            // TODO: проверить работоспособность этого кода, нужно понять из за чего по 2 запроса
//            clearFragmentResultListener("update_project")
        }
    }

    private fun updatePageAndShowSnackbar(
        result: Boolean,
        positiveMess: String,
        negativeMess: String
    ) {
        if (result) {



            viewModel.updateListProjectsData(1, 100)
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

    private fun loading(status: Boolean) = with(binding) {
        if (status) progressBar.isVisible = true
        else progressBar.isGone = true
    }

    private fun isRefreshingFalse() = with(binding) {
        swipeRefresh.isRefreshing = false
    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.LIST_PROJECT_TO_SECTIONS_APP.id) }
    }
}