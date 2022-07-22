package com.ahinfo.ahteam.ui.screens.parser.updateTaskProject

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.databinding.FragmentUpdateTaskProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTaskProjectFragment :
    BaseFragment<FragmentUpdateTaskProjectBinding, UpdateTaskProjectViewModel>(
        FragmentUpdateTaskProjectBinding::inflate
    ) {
    override val viewModel: UpdateTaskProjectViewModel by viewModels()

    override fun initView() {
        setFragmentResultListener("current_project") { _, bundle ->

            val itemTask = bundle.getParcelable<ElementsItemTask>("item_task")
            // TODO: раскинуть по полям все значения которых хотим апдейтить
        }
    }

    override fun initObservers() {
        TODO("Not yet implemented")
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