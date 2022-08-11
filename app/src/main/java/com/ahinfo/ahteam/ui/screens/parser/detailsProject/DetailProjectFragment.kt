package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.extension.isRefreshingFalse
import com.ahinfo.ahteam.core.extension.isRefreshingTrue
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.databinding.FragmentDetailProjectBinding
import com.ahinfo.ahteam.ui.screens.parser.addProjectTask.AddProjectTaskFragment
import com.ahinfo.ahteam.ui.screens.parser.listProjects.ListProjectsFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailProjectFragment :
    BaseFragment<FragmentDetailProjectBinding, DetailProjectViewModel>(FragmentDetailProjectBinding::inflate) {

    companion object {
        const val ID_PROJECT = "id_project"
        const val ITEM_TASK = "item_task"
    }

    override val viewModel: DetailProjectViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private var projectTasksAdapter: ProjectTasksAdapter? = null

    override fun initView() = with(binding) {
        projectTasksAdapter = ProjectTasksAdapter(resourceProvider)
        recyclerViewTasks.adapter = projectTasksAdapter

        btnAddTask.setOnClickListener {
            navigateTo(
                DestinationsParser.DETAIL_PROJECT_TO_ADD_TASK.id,
                bundleOf(ID_PROJECT to viewModel.loadProjectIdInPrefs())
            )
        }

        swipeRefresh.setOnRefreshListener { viewModel.updateUiProjectTasks() }
    }

    override fun listenerBundleArguments() {
        readArguments<Int>(ListProjectsFragment.PARSER_PROJECT_ID,
            ifExist = { projectId ->
                viewModel.saveProjectIdInPrefs(projectId)
                viewModel.updateUiProjectTasks()
            },
            dontExist = {
                viewModel.updateUiProjectTasks()
            })

        readArguments<Boolean>(AddProjectTaskFragment.RESULT_ADD_PROJECT_TASK,
            ifExist = { result ->
                readResultAndShowSnackbar(
                    result = result,
                    positiveMess = string(R.string.success_add_project_task),
                    negativeMess = string(R.string.fail_add_project_task),
                    positiveResult = {
                        viewModel.updateUiProjectTasks()
                    }
                )
            })
    }

    override fun initObservers() {
        // TODO: обработать все статусы
        viewModel.detailProjectState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailProjectState.Error -> loading(false)
                DetailProjectState.ErrorDeleteProject -> loading(false)
                DetailProjectState.Loading -> loading(true)
                is DetailProjectState.NoInternet -> loading(false)
                DetailProjectState.SuccessDeleteProject -> loading(false)
                is DetailProjectState.Success -> {
                    loading(false)
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

    private fun loading(status: Boolean) = with(binding) {
        if (status) isRefreshingTrue(swipeRefresh)
        else isRefreshingFalse(swipeRefresh)
    }

    override fun title() {
        binding.titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() {
        binding.titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.DETAIL_PROJECT_TO_LIST_PROJECTS.id) }
    }
}