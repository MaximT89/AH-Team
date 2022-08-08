package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.databinding.FragmentDetailProjectBinding
import com.ahinfo.ahteam.ui.screens.parser.listProjects.ListProjectsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("SetTextI18n")
class DetailProjectFragment :
    BaseFragment<FragmentDetailProjectBinding, DetailProjectViewModel>(FragmentDetailProjectBinding::inflate) {

    companion object {
        const val ITEM_TASK = "item_task"
    }

    override val viewModel: DetailProjectViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private var projectTasksAdapter: ProjectTasksAdapter? = null

    override fun initView() = with(binding) {

        projectTasksAdapter = ProjectTasksAdapter(resourceProvider)
        recyclerViewTasks.adapter = projectTasksAdapter

        if (arguments?.getInt(ListProjectsFragment.PARSER_PROJECT_ID) != null) {
            val projectId = arguments?.getInt(ListProjectsFragment.PARSER_PROJECT_ID)
            viewModel.saveProjectIdInPrefs(projectId!!)
            viewModel.updateUiProjectTasks()
            arguments?.remove(ListProjectsFragment.PARSER_PROJECT_ID)
        } else {
            viewModel.updateUiProjectTasks()
        }
    }

    override fun initObservers() {
        // TODO: обработать все статусы
        viewModel.detailProjectState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailProjectState.Error -> {}
                DetailProjectState.ErrorDeleteProject -> {}
                DetailProjectState.Loading -> {}
                is DetailProjectState.NoInternet -> {}
                DetailProjectState.SuccessDeleteProject -> {}
                is DetailProjectState.Success -> {
                    updateRecyclerView(state.data.elements)
                }
            }
        }
    }

    private fun updateRecyclerView(elements: List<ElementsItemTask?>?) {
        projectTasksAdapter?.submitList(elements)
    }

    override fun initCallbacks() {
        projectTasksAdapter?.callBackDeleteTask = { idTask ->
            viewModel.deleteTask(idTask)
        }

        projectTasksAdapter?.callBackUpdateTask = { itemTask ->
            navigateTo(
                DestinationsParser.DETAIL_PROJECT_TO_UPDATE_TASK_PROJECT.id,
                bundleOf(ITEM_TASK to itemTask)
            )
        }

        projectTasksAdapter?.callBackNavigateForTask = { itemTask ->
            navigateTo(
                DestinationsParser.DETAIL_PROJECT_TO_CURRENT_PARSER_PROJECT.id,
                bundleOf(ITEM_TASK to itemTask)
            )
        }
    }

    override fun title() {
        binding.titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() {
        binding.titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.DETAIL_PROJECT_TO_LIST_PROJECTS.id) }
    }
}