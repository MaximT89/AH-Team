package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import android.annotation.SuppressLint
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItemProject
import com.ahinfo.ahteam.databinding.FragmentDetailProjectBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("SetTextI18n")
class DetailProjectFragment :
    BaseFragment<FragmentDetailProjectBinding, DetailProjectViewModel>(FragmentDetailProjectBinding::inflate) {
    override val viewModel: DetailProjectViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    private var projectTasksAdapter: ProjectTasksAdapter? = null

    override fun initView() = with(binding) {

        projectTasksAdapter = ProjectTasksAdapter(resourceProvider)
        recyclerViewTasks.adapter = projectTasksAdapter

        setFragmentResultListener("detail_parser_project") { _, bundle ->
            val elementItem = bundle.getParcelable<ElementsItemProject>("parser_project")
            updateUi(elementItem)
            elementItem?.id?.let { viewModel.updateUiProjectTasks(it) }
            // TODO: тоже подумать о чистке setFragmentResultListener
        }
    }

    private fun updateUi(elementItem: ElementsItemProject?) = with(binding) {
        nameProject.text = "Название: ${elementItem?.name}"
        descriptionProject.text = "Описание: ${elementItem?.description}"
        idProject.text = "ID: ${elementItem?.id}"
        statusProject.text = "Статус: ${elementItem?.status}"
    }

    override fun initObservers() {
        // TODO: обработать все статусы
        viewModel.detailProjectState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DetailProjectState.Error -> {}
                DetailProjectState.ErrorDeleteProject -> {}
                DetailProjectState.Loading -> {}
                is DetailProjectState.NoInternet -> {}
                DetailProjectState.SuccessDeleteProject -> {}
                is DetailProjectState.Success -> {
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
            setFragmentResult("current_project", bundleOf("item_task" to itemTask))
            navigateTo(DestinationsParser.DETAIL_PROJECT_TO_UPDATE_TASK_PROJECT.id)
        }

        projectTasksAdapter?.callBackNavigateForTask = {
            navigateTo(DestinationsParser.DETAIL_PROJECT_TO_CURRENT_PARSER_PROJECT.id)
        }
    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.DETAIL_PROJECT_TO_LIST_PROJECTS.id) }
    }
}