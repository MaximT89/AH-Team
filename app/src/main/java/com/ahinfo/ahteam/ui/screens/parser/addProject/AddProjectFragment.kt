package com.ahinfo.ahteam.ui.screens.parser.addProject

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.Destinations
import com.ahinfo.ahteam.data.parser.addProject.remote.dto.RequestAddProject
import com.ahinfo.ahteam.databinding.FragmentAddProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProjectFragment :
    BaseFragment<FragmentAddProjectBinding, AddProjectViewModel>(FragmentAddProjectBinding::inflate) {
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
                is AddProjectsState.Error -> {}
                AddProjectsState.Loading -> {}
                is AddProjectsState.NoInternet -> {}
                is AddProjectsState.Success -> {

                    // TODO: записать в бандл результат

                    navigateTo(Destinations.ADD_PROJECT_TO_LIST_PROJECTS.id)
                }
            }
        }
    }
}