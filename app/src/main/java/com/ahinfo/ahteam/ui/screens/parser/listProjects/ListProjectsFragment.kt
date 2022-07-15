package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentListProjectsBinding
import com.ahinfo.ahteam.domain.parser.listProjects.entity.ListProjectsGetDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProjectsFragment :
    BaseFragment<FragmentListProjectsBinding, ListProjectsViewModel>(FragmentListProjectsBinding::inflate) {

    private val listProjectsAdapter = ListProjectsAdapter()

    override val viewModel: ListProjectsViewModel by viewModels()

    override fun initView() = with(binding) {
        recyclerView.adapter = listProjectsAdapter

        swipeRefresh.setOnRefreshListener {
            // TODO: данные нужно брать из префов
            viewModel.updateListProjectsData(1, 10)
        }

        listProjectsAdapter.callBackDeleteProject = {
            id -> viewModel.deleteProject(id)
        }
    }

    override fun initObservers() = with(binding) {
        viewModel.listProjectState.observe(this@ListProjectsFragment) {

            when (it) {
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
                    updateContent(it.data)
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