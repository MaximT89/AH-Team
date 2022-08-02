package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.databinding.FragmentCurrentParserProjectBinding
import com.ahinfo.ahteam.ui.screens.parser.detailsProject.DetailProjectFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentParserProjectFragment :
    BaseFragment<FragmentCurrentParserProjectBinding, CurrentParserProjectViewModel>(
        FragmentCurrentParserProjectBinding::inflate
    ) {
    override val viewModel: CurrentParserProjectViewModel by viewModels()

    override fun initView() {

    }

    override fun initCallbacks() {
        setFragmentResultListener(DetailProjectFragment.SET_RESULT_CURRENT_TASK) { _ , bundle ->
            val itemTask = bundle.getParcelable<ElementsItemTask>("item_task")

            // TODO: нужно запустить статус загрузки и пока идет загрузка определиться с дельнейшим статуслм
            // TODO: далее нужно сохранить в локальном кеше номер проекта и номер задачи по парсингу
        }
    }

    override fun initObservers() {
        viewModel.currentParserState.observe(viewLifecycleOwner){
            state ->
            when(state){
                is CurrentParserState.Error -> {}
                CurrentParserState.Loading -> {}
                is CurrentParserState.NoInternet -> {}
                is CurrentParserState.Success -> {}
                is CurrentParserState.EmptyParser -> TODO()
            }
        }
    }

    override fun title() = with(binding){
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding){
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.CURRENT_PARSER_PROJECT_TO_DETAIL_PROJECT.id) }
    }
}