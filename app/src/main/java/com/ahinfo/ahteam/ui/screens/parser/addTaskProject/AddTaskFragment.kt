package com.ahinfo.ahteam.ui.screens.parser.addTaskProject

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentAddTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddTaskFragment :
    BaseFragment<FragmentAddTaskBinding, AddTaskViewModel>(FragmentAddTaskBinding::inflate) {
    override val viewModel: AddTaskViewModel by viewModels()

    override fun initView() {

    }

    override fun initObservers() {

    }

    override fun title() = with(binding){
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding){
        titleField.arrowBack.setOnClickListener {
            navigateTo(DestinationsParser.ADD_TASK_PROJECT_TO_DETAIL_PROJECT.id)
        }
    }

}