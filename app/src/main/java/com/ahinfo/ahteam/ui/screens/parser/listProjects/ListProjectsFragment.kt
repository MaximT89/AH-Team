package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.Destinations
import com.ahinfo.ahteam.databinding.FragmentListProjectsBinding
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProjectsFragment :
    BaseFragment<FragmentListProjectsBinding, ListProjectsViewModel>(FragmentListProjectsBinding::inflate) {

    override val viewModel: ListProjectsViewModel by viewModels()

    private val listProjectsAdapter = ListProjectsAdapter()

    override fun initView() = with(binding) {
        recyclerView.adapter = listProjectsAdapter

        swipeRefresh.setOnRefreshListener {
            // TODO: данные нужно брать из префов
            viewModel.updateListProjectsData(1, 10)
        }

        listProjectsAdapter.callBackDeleteProject = { id ->
            viewModel.deleteProject(id)
        }

        listProjectsAdapter.callBackUpgradeProject = { parserProject ->
            setFragmentResult("upgrade_parser_project", bundleOf("parser_project" to parserProject))
            navigateTo(Destinations.LIST_PROJECT_TO_UPGRADE_PROJECT.id)
        }

        btnAddProject.setOnClickListener { navigateTo(Destinations.LIST_PROJECTS_TO_ADD_PROJECT.id) }

        setFragmentResultListener("add_project") { _, bundle ->
            val result = bundle.getBoolean("result_add_project")
            updatePageAndShowSnackbar(result, "Проект добавлен", "Ошибка добавления проекта")
        }

        setFragmentResultListener("update_project") { _ , bundle ->
            val result = bundle.getBoolean("update_result")
            updatePageAndShowSnackbar(result, "Проект изменен", "Ошибка изменения проекта")
        }
    }

    private fun updatePageAndShowSnackbar(result: Boolean, positiveMess : String, negativeMess : String) {
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
}