package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.*
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
        setFragmentResultListener(DetailProjectFragment.SET_RESULT_CURRENT_TASK) { _, bundle ->
            val itemTask = bundle.getParcelable<ElementsItemTask>("item_task")

            viewModel.saveCurrentTaskId(itemTask!!)
            viewModel.getCurrentTaskStatus()

            // TODO: далее нужно сохранить в локальном кеше номер проекта и номер задачи по парсингу
        }.let {

            // TODO: брать из префов значения и получить статус исходя из этих данных
        }
    }

    override fun initObservers() {
        viewModel.currentParserState.observe(viewLifecycleOwner) { state ->
            when (state) {
                CurrentParserState.CatalogComplete -> {
                    hideProgressBar()
                    showBtnsField()
                    showBtnDownloadOffers()
                }
                CurrentParserState.CatalogError -> {
                    hideProgressBar()
                }
                CurrentParserState.CatalogStart -> {
                    log("ui catalog start is work")
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                }
                CurrentParserState.ElementComplete -> {
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                }
                CurrentParserState.ElementError -> {
                    hideProgressBar()
                }
                CurrentParserState.ElementStart -> {
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                }
                is CurrentParserState.Error -> {
                    hideProgressBar()
                }
                CurrentParserState.Loading -> {
                    hideProgressBar()
                    showProgressBar()
                }
                CurrentParserState.MenuComplete -> {
                    hideProgressBar()
                    showBtnsField()
                    showBtnDownloadArticles()
                }
                CurrentParserState.MenuError -> {
                    hideProgressBar()
                }
                CurrentParserState.MenuStart -> {
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                }
                is CurrentParserState.NoInternet -> {
                    hideProgressBar()
                }
                CurrentParserState.ParsingCreate -> {
                    hideProgressBar()
                    showBtnsField()
                    showBtnDownloadCategory()
                }
            }
        }
    }

    private fun showProgressBar() { binding.progressBar.show() }

    private fun hideProgressBar() { binding.progressBar.hide() }

    private fun showBtnDownloadCategory() = with(binding) {
        btnDownloadCategory.active()
        btnDownloadArticles.notActive()
        btnDownloadOffers.notActive()
    }

    private fun showBtnDownloadArticles() = with(binding) {
        btnDownloadCategory.notActive()
        btnDownloadArticles.active()
        btnDownloadOffers.notActive()
    }

    private fun showBtnDownloadOffers() = with(binding) {
        btnDownloadCategory.notActive()
        btnDownloadArticles.notActive()
        btnDownloadOffers.active()
    }

    private fun allBtnNotActive() = with(binding) {
        btnDownloadCategory.notActive()
        btnDownloadArticles.notActive()
        btnDownloadOffers.notActive()
    }

    private fun hideBtnsField() = with(binding) { fieldStateBtn.hide() }

    private fun showBtnsField() = with(binding) { fieldStateBtn.show() }

    override fun title() = with(binding) { titleField.title.text = viewModel.title() }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.CURRENT_PARSER_PROJECT_TO_DETAIL_PROJECT.id) }
    }
}