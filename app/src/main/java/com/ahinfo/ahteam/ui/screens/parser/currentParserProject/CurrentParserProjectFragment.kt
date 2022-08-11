package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import android.annotation.SuppressLint
import androidx.core.view.isVisible
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
@SuppressLint("UseCompatLoadingForDrawables")
class CurrentParserProjectFragment :
    BaseFragment<FragmentCurrentParserProjectBinding, CurrentParserProjectViewModel>(
        FragmentCurrentParserProjectBinding::inflate
    ) {

    override val viewModel: CurrentParserProjectViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun initView() = with(binding) {

        btnReloadCategoryStat.setOnClickListener { viewModel.getCurrentTaskStatus() }

        rootHeaderSectionStatField.setOnClickListener {
            if (fieldStatisticSectionStat.isVisible) roolSectionStat()
            else unrollSectionStat()
        }
    }

    override fun listenerBundleArguments() {
        readArguments<ElementsItemTask>(DetailProjectFragment.ITEM_TASK,
            ifExist = { itemTask ->
                viewModel.saveCurrentTaskId(itemTask)
                viewModel.getCurrentTaskStatus()
            },
            dontExist = {
                viewModel.getCurrentTaskStatus()
            })
    }

    private fun roolSectionStat() {
        binding.fieldStatisticSectionStat.hide()
        binding.arrowVisibleField.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_baseline_keyboard_arrow_down_24,
                null
            )
        )
    }

    private fun unrollSectionStat() {
        binding.fieldStatisticSectionStat.show()
        binding.arrowVisibleField.setImageDrawable(
            resources.getDrawable(
                R.drawable.ic_baseline_keyboard_arrow_up_24,
                null
            )
        )
    }

    override fun initObservers() {
        viewModel.currentParserState.observe(viewLifecycleOwner) { state ->
            when (state) {
                CurrentParserState.ParsingCreate -> {
                    log("CurrentParserState.ParsingCreate ")
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadCategory()
                    visibleCatalogStat(false)
                }
                CurrentParserState.MenuStart -> {
                    log("CurrentParserState.MenuStart")
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    visibleCatalogStat(false)
                }
                CurrentParserState.MenuComplete -> {
                    log("CurrentParserState.MenuComplete")
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadArticles()
                    loadTaskSectionStat()
                    visibleCatalogStat(false)
                }
                CurrentParserState.CatalogStart -> {
                    log("CurrentParserState.CatalogError")
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                }
                CurrentParserState.CatalogComplete -> {
                    log("CurrentParserState.CatalogComplete")
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadOffers()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                }
                CurrentParserState.ElementStart -> {

                    // TODO: добавить остановку парсинга

                    log("CurrentParserState.ElementStart")
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                }
                CurrentParserState.ElementComplete -> {
                    log("CurrentParserState.CatalogError")
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                }
                CurrentParserState.CatalogError -> {
                    log("CurrentParserState.CatalogError")
                    visibleCatalogStat(false)
                    visibleRootProgressBar(false)
                }
                CurrentParserState.ElementError -> {
                    log("CurrentParserState.ElementError")
                    visibleCatalogStat(false)
                    visibleRootProgressBar(false)
                }
                is CurrentParserState.Error -> {
                    log("CurrentParserState.Error")
                    visibleCatalogStat(false)
                    visibleRootProgressBar(false)
                }
                CurrentParserState.LoadingRoot -> {
                    log("CurrentParserState.Loading")
                    visibleRootProgressBar(true)
                }
                CurrentParserState.MenuError -> {
                    log("CurrentParserState.MenuError")
                    visibleRootProgressBar(false)
                }
                is CurrentParserState.NoInternet -> {
                    log("CurrentParserState.NoInternet")
                    visibleRootProgressBar(false)
                }
                is CurrentParserState.SuccessLoadSectionStat -> {
                    log("CurrentParserState.SuccessLoadSectionStat work")
                    visibleProgressSectionStat(false)
                    updateSectionStatField(state.data)
                }
                CurrentParserState.LoadingSectionStat -> {
                    visibleProgressSectionStat(true)
                }
            }
        }
    }

    private fun visibleProgressSectionStat(status: Boolean) = with(binding) {
        if (status) {
            progressBarCatalogStat.show()
            fieldStatisticSectionStat.hide()
        } else {
            progressBarCatalogStat.hide()
            fieldStatisticSectionStat.show()
        }
    }

    private fun loadTaskSectionStat() {
        viewModel.getTaskSectionStat()
    }

    private fun visibleCatalogStat(status: Boolean) {
        if (status) binding.rootCatalogStatistic.show()
        else binding.rootCatalogStatistic.hide()
    }

    private fun updateSectionStatField(data: GetSectionStatDomain) = with(binding) {
        avgPrice.text = resourceProvider.string(R.string.avg_price, data.avgPrice.toString())
        avgWeight.text = resourceProvider.string(R.string.avg_weight, data.avgWeight.toString())
        totalCountElements.text =
            resourceProvider.string(R.string.total_element_parsing, data.countElements.toString())
        countElementsExist.text =
            resourceProvider.string(R.string.count_elements_exist, data.exist.toString())
        minPriceElements.text =
            resourceProvider.string(R.string.min_price_elements, data.minPrice.toString())
        maxPriceElements.text =
            resourceProvider.string(R.string.max_price_elements, data.maxPrice.toString())
    }

    private fun visibleRootProgressBar(status: Boolean) {
        if (status) binding.progressBar.show()
        else binding.progressBar.hide()
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