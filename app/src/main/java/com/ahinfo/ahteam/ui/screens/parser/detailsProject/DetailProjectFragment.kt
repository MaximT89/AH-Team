package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import android.annotation.SuppressLint
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.extension.log
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItem
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

    val projectTasksAdapter = ProjectTasksAdapter(resourceProvider)

    override fun initView() {

        setFragmentResultListener("detail_parser_project") { _, bundle ->
            val elementItem = bundle.getParcelable<ElementsItem>("parser_project")
            updateUi(elementItem)

            // TODO: тоже подумать о чистке setFragmentResultListener
            // TODO: запустить запрос на получение данных по всем задачам
        }
    }

    private fun updateUi(elementItem: ElementsItem?) = with(binding){
        nameProject.text = "Название: ${elementItem?.name}"
        descriptionProject.text = "Описание: ${elementItem?.description}"
        idProject.text = "ID: ${elementItem?.id}"
        statusProject.text = "Статус: ${elementItem?.status}"
    }

    override fun initObservers() {

    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.DETAIL_PROJECT_TO_LIST_PROJECTS.id) }
    }
}