package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.extension.*
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.databinding.FragmentCurrentParserProjectBinding
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import com.ahinfo.ahteam.ui.screens.parser.detailsProject.DetailProjectFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CurrentParserProjectFragment :
    BaseFragment<FragmentCurrentParserProjectBinding, CurrentParserProjectViewModel>(
        FragmentCurrentParserProjectBinding::inflate
    ) {
    override val viewModel: CurrentParserProjectViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun initView() = with(binding) {

        if (arguments?.getParcelable<ElementsItemTask>(DetailProjectFragment.ITEM_TASK) != null) {
            val itemTask =
                arguments?.getParcelable<ElementsItemTask>(DetailProjectFragment.ITEM_TASK)
            viewModel.saveCurrentTaskId(itemTask!!)
            viewModel.getCurrentTaskStatus()
            arguments?.remove(DetailProjectFragment.ITEM_TASK)
        } else viewModel.getCurrentTaskStatus()
    }

    override fun initObservers() {
        viewModel.currentParserState.observe(viewLifecycleOwner) { state ->
            when (state) {
                CurrentParserState.CatalogComplete -> {
                    log("CurrentParserState.CatalogComplete")
                    hideProgressBar()
                    showBtnsField()
                    showBtnDownloadOffers()
                    loadTaskSectionStat()
                }
                CurrentParserState.CatalogError -> {
                    log("CurrentParserState.CatalogError")
                    hideProgressBar()
                }
                CurrentParserState.CatalogStart -> {
                    log("CurrentParserState.CatalogError")
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                }
                CurrentParserState.ElementComplete -> {
                    log("CurrentParserState.CatalogError")
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                }
                CurrentParserState.ElementError -> {
                    log("CurrentParserState.ElementError")
                    hideProgressBar()
                }
                CurrentParserState.ElementStart -> {
                    log("CurrentParserState.ElementStart")
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                }
                is CurrentParserState.Error -> {
                    log("CurrentParserState.Error")
                    hideProgressBar()
                }
                CurrentParserState.Loading -> {
                    log("CurrentParserState.Loading")
                    hideProgressBar()
                    showProgressBar()
                }
                CurrentParserState.MenuComplete -> {
                    log("CurrentParserState.MenuComplete")
                    hideProgressBar()
                    showBtnsField()
                    showBtnDownloadArticles()
                    loadTaskSectionStat()
                }
                CurrentParserState.MenuError -> {
                    log("CurrentParserState.MenuError")
                    hideProgressBar()
                }
                CurrentParserState.MenuStart -> {
                    log("CurrentParserState.MenuStart")
                    hideProgressBar()
                    showBtnsField()
                    allBtnNotActive()
                }
                is CurrentParserState.NoInternet -> {
                    log("CurrentParserState.NoInternet")
                    hideProgressBar()
                }
                CurrentParserState.ParsingCreate -> {
                    log("CurrentParserState.ParsingCreate ")
                    hideProgressBar()
                    showBtnsField()
                    showBtnDownloadCategory()
                }
                is CurrentParserState.SuccessLoadSectionStat -> {
                    log("CurrentParserState.SuccessLoadSectionStat work")
                    updateSectionStatField(state.data)
                }
            }
        }
    }

    private fun loadTaskSectionStat() {
        log("loadTaskSectionStat work")
        viewModel.getTaskSectionStat()
    }

    private fun updateSectionStatField(data: GetSectionStatDomain) = with(binding) {
        totalCountElements.text =
            resourceProvider.string(R.string.total_element_parsing, data.countElements.toString())
        countElementsExist.text =
            resourceProvider.string(R.string.count_elements_exist, data.exist.toString())
        minPriceElements.text =
            resourceProvider.string(R.string.min_price_elements, data.minPrice.toString())
        maxPriceElements.text =
            resourceProvider.string(R.string.max_price_elements, data.maxPrice.toString())
    }

    private fun showProgressBar() {
        binding.progressBar.show()
    }

    private fun hideProgressBar() {
        binding.progressBar.hide()
    }

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