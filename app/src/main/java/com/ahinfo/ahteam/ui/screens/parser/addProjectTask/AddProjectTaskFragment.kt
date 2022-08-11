package com.ahinfo.ahteam.ui.screens.parser.addProjectTask

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.log
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.addProjectTask.remote.dto.RequestAddProjectTask
import com.ahinfo.ahteam.databinding.FragmentAddTaskBinding
import com.ahinfo.ahteam.ui.screens.parser.addProject.AddProjectFragment
import com.ahinfo.ahteam.ui.screens.parser.detailsProject.DetailProjectFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProjectTaskFragment :
    BaseFragment<FragmentAddTaskBinding, AddTaskViewModel>(FragmentAddTaskBinding::inflate) {

    companion object {
        const val RESULT_ADD_PROJECT_TASK = "result_add_project_task"
    }

    override val viewModel: AddTaskViewModel by viewModels()

    override fun initView() = with(binding) {

        btnCreateParsingTask.setOnClickListener {
            val request = RequestAddProjectTask(
                name = editNameProject.text.toString(),
                description = editTextDescription.text.toString(),
                projectId = viewModel.loadProjectId()
            )
            viewModel.addProjectTask(request)
        }
    }

    override fun listenerBundleArguments() {
        readArguments<Int>(DetailProjectFragment.ID_PROJECT,
            ifExist = { idProject -> viewModel.saveProjectId(idProject) })
    }

    override fun initObservers() {
        viewModel.addProjectTaskState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AddProjectTaskState.Error -> {}
                AddProjectTaskState.Loading -> {}
                is AddProjectTaskState.NoInternet -> {}
                is AddProjectTaskState.Success -> navigateTo(
                    DestinationsParser.ADD_TASK_PROJECT_TO_DETAIL_PROJECT.id,
                    bundleOf(RESULT_ADD_PROJECT_TASK to state.data.result)
                )
            }
        }
    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener {
            navigateTo(DestinationsParser.ADD_TASK_PROJECT_TO_DETAIL_PROJECT.id)
        }
    }
}