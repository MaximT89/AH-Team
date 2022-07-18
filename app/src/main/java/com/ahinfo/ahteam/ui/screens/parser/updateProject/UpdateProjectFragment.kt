package com.ahinfo.ahteam.ui.screens.parser.updateProject

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.Destinations
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItem
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.databinding.FragmentUpdateProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProjectFragment :
    BaseFragment<FragmentUpdateProjectBinding, UpdateProjectViewModel>(FragmentUpdateProjectBinding::inflate) {
    override val viewModel: UpdateProjectViewModel by viewModels()

    override fun initView() = with(binding) {
        setFragmentResultListener("upgrade_parser_project") { _, bundle ->
            val parserItem = bundle.getParcelable<ElementsItem>("parser_project")
            viewModel.updateElementsItem(parserItem!!)
        }

        btnUpdateProject.setOnClickListener {
            val request = RequestUpdateProject(
                name = editNameProject.text.toString(),
                description = editTextDescription.text.toString(),
                projectId = viewModel.elementsItem.value!!.id
            )
            viewModel.updateProject(request)
        }
    }

    override fun initObservers() {
        viewModel.elementsItem.observe(viewLifecycleOwner) { elementItem ->
            updateUi(elementItem)
        }
        viewModel.updateProjectState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UpdateProjectsState.Error -> {}
                UpdateProjectsState.Loading -> {}
                is UpdateProjectsState.NoInternet -> {}
                is UpdateProjectsState.Success -> {
                    setFragmentResult("update_project", bundleOf("update_result" to state.data.result))
                    navigateTo(Destinations.UPDATE_PROJECT_TO_LIST_PROJECTS.id)
                }
            }
        }
    }

    private fun updateUi(parserItem: ElementsItem?) = with(binding) {
        editNameProject.setText(parserItem?.name ?: "")
        editTextDescription.setText(parserItem?.description ?: "")
    }
}