package com.ahinfo.ahteam.ui.screens.parser.updateTaskProject

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.data.parser.updateTaskProject.remote.dto.RequestUpdateProjectTask
import com.ahinfo.ahteam.databinding.FragmentUpdateTaskProjectBinding
import com.ahinfo.ahteam.ui.screens.parser.detailsProject.DetailProjectFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTaskProjectFragment :
    BaseFragment<FragmentUpdateTaskProjectBinding, UpdateTaskProjectViewModel>(
        FragmentUpdateTaskProjectBinding::inflate
    ) {

    companion object {
        const val RESULT_UPDATE_PROJECT_TASK = "result_update_project_task"
    }

    override val viewModel: UpdateTaskProjectViewModel by viewModels()

    override fun initView() = with(binding) {
        btnUpdateProject.setOnClickListener {
            val request = RequestUpdateProjectTask(
                name = editNameProject.text.toString(),
                description = editTextDescription.text.toString(),
                parsingId = viewModel.elementsItemTask.value!!.parsingId
            )
            viewModel.updateProjectTask(request)
        }
    }

    override fun listenerBundleArguments() {
        readArguments<ElementsItemTask>(DetailProjectFragment.ITEM_TASK,
            ifExist = { viewModel.updateElementItemTask(it) })
    }

    override fun initObservers() {
        viewModel.elementsItemTask.observe(viewLifecycleOwner) { item ->
            updateUi(item)
        }

        viewModel.updateProjectTaskState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UpdateProjectTaskState.Error -> {}
                UpdateProjectTaskState.Loading -> {}
                is UpdateProjectTaskState.NoInternet -> {}
                is UpdateProjectTaskState.Success -> navigateTo(
                    DestinationsParser.UPDATE_TASK_PROJECT_TO_DETAIL_PROJECT.id,
                    bundleOf(RESULT_UPDATE_PROJECT_TASK to state.data.result)
                )
            }
        }
    }

    private fun updateUi(item: ElementsItemTask?) = with(binding) {
        editNameProject.setText(item?.name ?: "")
        editTextDescription.setText(item?.description ?: "")
    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener {
            navigateTo(DestinationsParser.UPDATE_TASK_PROJECT_TO_DETAIL_PROJECT.id)
        }
    }
}