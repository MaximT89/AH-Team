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
        setFragmentResultListener(DetailProjectFragment.SET_RESULT_CURRENT_TASK) { _ , bundle ->
            val itemTask = bundle.getParcelable<ElementsItemTask>("item_task")

            viewModel.saveCurrentTaskId(itemTask!!)
            viewModel.getCurrentTaskStatus()

            // TODO: далее нужно сохранить в локальном кеше номер проекта и номер задачи по парсингу
        }.let {

            // TODO: брать из префов значения и получить статус исходя из этих данных
        }
    }

    override fun initObservers() {
        viewModel.currentParserState.observe(viewLifecycleOwner){
            state ->
            when(state){
                CurrentParserState.CatalogComplete -> {}
                CurrentParserState.CatalogError -> {}
                CurrentParserState.CatalogStart -> {}
                CurrentParserState.ElementComplete -> {}
                CurrentParserState.ElementError -> {}
                CurrentParserState.ElementStart -> {}
                is CurrentParserState.Error -> {}
                CurrentParserState.Loading -> {}
                CurrentParserState.MenuComplete -> {}
                CurrentParserState.MenuError -> {}
                CurrentParserState.MenuStart -> {}
                is CurrentParserState.NoInternet -> {}
                CurrentParserState.ParsingCreate -> {}
            }
        }
    }

    private fun showBtnDownloadCategory() = with(binding){
        btnDownloadCategoty.isVisible = true
        btnDownloadArticles.isGone = true
        btnDownloadOffers.isGone = true
    }

    private fun showBtnDownloadArticles() = with(binding){
        btnDownloadCategoty.isGone = true
        btnDownloadArticles.isVisible = true
        btnDownloadOffers.isGone = true
    }

    private fun showBtnDownloadOffers() = with(binding){
        btnDownloadCategoty.isGone = true
        btnDownloadArticles.isGone = true
        btnDownloadOffers.isVisible = true
    }

    private fun hideAllStateBtn(){
        btnDownloadCategoty.isGone = true
        btnDownloadArticles.isGone = true
        btnDownloadOffers.isGone  = true
    }

    override fun title() = with(binding){
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding){
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.CURRENT_PARSER_PROJECT_TO_DETAIL_PROJECT.id) }
    }
}