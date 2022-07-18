package com.ahinfo.ahteam.ui.screens.parser.addProject

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.log
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

            log("btnCreateProject : work , request name ${request.name}, request desc ${request.description}")
            viewModel.addProject(request)
        }
    }

    override fun initObservers() {
        viewModel.addProjectState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is AddProjectsState.Error -> { log(state.messageError) }
                AddProjectsState.Loading -> {log("Загрузка")}
                is AddProjectsState.NoInternet -> {log(state.messageNoInternet) }
                is AddProjectsState.Success -> {

                    setFragmentResult(
                        "add_project", bundleOf("result_add_project" to state.data.result!!)
                    )

                    navigateTo(Destinations.ADD_PROJECT_TO_LIST_PROJECTS.id)
                }
            }
        }
    }
}