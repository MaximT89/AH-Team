package com.ahinfo.ahteam.ui.screens.parser.addProject

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.log
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.databinding.FragmentAddProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProjectFragment :
    BaseFragment<FragmentAddProjectBinding, AddProjectViewModel>(FragmentAddProjectBinding::inflate) {

    companion object{
        const val SET_RESULT_ADD_PROJECT = "result_add_project"
    }

    override val viewModel: AddProjectViewModel by viewModels()

    override fun initView() = with(binding) {
        btnCreateProject.setOnClickListener {
            val request = RequestAddProject(
                name = editNameProject.text.toString(),
                description = editTextDescription.text.toString()
            )
            viewModel.addProject(request)
        }
    }

    override fun initObservers() {
        viewModel.addProjectState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AddProjectsState.Error -> { }
                AddProjectsState.Loading -> {}
                is AddProjectsState.NoInternet -> {}
                is AddProjectsState.Success -> {

                    setFragmentResult(
                        SET_RESULT_ADD_PROJECT, bundleOf("result_add_project" to state.data.result!!)
                    )

                    navigateTo(DestinationsParser.ADD_PROJECT_TO_LIST_PROJECTS.id)
                }
            }
        }
    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding){
        titleField.arrowBack.setOnClickListener {
            navigateTo(DestinationsParser.ADD_PROJECT_TO_LIST_PROJECTS.id)
        }
    }
}