package com.ahinfo.ahteam.ui.screens.parser.updateProject

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItemProject
import com.ahinfo.ahteam.data.parser.updateProject.remote.dto.RequestUpdateProject
import com.ahinfo.ahteam.databinding.FragmentUpdateProjectBinding
import com.ahinfo.ahteam.ui.screens.parser.listProjects.ListProjectsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProjectFragment :
    BaseFragment<FragmentUpdateProjectBinding, UpdateProjectViewModel>(FragmentUpdateProjectBinding::inflate) {

    companion object {
        const val SET_RESULT_UPDATE_PROJECT = "result_update_project"
    }

    override val viewModel: UpdateProjectViewModel by viewModels()

    override fun initView() = with(binding) {
        setFragmentResultListener(ListProjectsFragment.SET_RESULT_UPGRADE_PARSER) { _, bundle ->
            val parserItem = bundle.getParcelable<ElementsItemProject>("parser_project")
            viewModel.updateElementsItem(parserItem!!)
        }

        btnUpdateProject.setOnClickListener {
            val request = RequestUpdateProject(
                name = editNameProject.text.toString(),
                description = editTextDescription.text.toString(),
                projectId = viewModel.elementsItemProject.value!!.id
            )
            viewModel.updateProject(request)
        }
    }

    override fun initObservers() {
        viewModel.elementsItemProject.observe(viewLifecycleOwner) { elementItem ->
            updateUi(elementItem)
        }
        viewModel.updateProjectState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UpdateProjectsState.Error -> {}
                UpdateProjectsState.Loading -> {}
                is UpdateProjectsState.NoInternet -> {}
                is UpdateProjectsState.Success -> {
                    setFragmentResult(
                        SET_RESULT_UPDATE_PROJECT,
                        bundleOf("update_result" to state.data.result)
                    )
                    navigateTo(DestinationsParser.UPDATE_PROJECT_TO_LIST_PROJECTS.id)
                }
            }
        }
    }

    private fun updateUi(parserItem: ElementsItemProject?) = with(binding) {
        editNameProject.setText(parserItem?.name ?: "")
        editTextDescription.setText(parserItem?.description ?: "")
    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.UPDATE_PROJECT_TO_LIST_PROJECTS.id) }
    }
}