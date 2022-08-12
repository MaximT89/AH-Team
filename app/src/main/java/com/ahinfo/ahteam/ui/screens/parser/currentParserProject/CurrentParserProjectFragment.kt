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
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetElementStatDomain
import com.ahinfo.ahteam.domain.parser.currentParserProject.entity.GetSectionStatDomain
import com.ahinfo.ahteam.ui.screens.parser.detailsProject.DetailProjectFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("UseCompatLoadingForDrawables, SetTextI18n")
class CurrentParserProjectFragment :
    BaseFragment<FragmentCurrentParserProjectBinding, CurrentParserProjectViewModel>(
        FragmentCurrentParserProjectBinding::inflate
    ) {

    override val viewModel: CurrentParserProjectViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun initView() = with(binding) {

        btnReloadCategoryStat.setOnClickListener { viewModel.getTaskSectionStat() }

        btnReloadElementStat.setOnClickListener { viewModel.getTaskElementStat() }

        rootHeaderSectionStatField.setOnClickListener {
            if (fieldStatisticSectionStat.isVisible) roolSectionStat()
            else unrollSectionStat()
        }

        rootHeaderParserManage.setOnClickListener {
            if (manageParserField.isVisible) roolManageParser()
            else unroolManageParser()
        }

        rootHeaderElementStatField.setOnClickListener {
            if (fieldStatisticElementStat.isVisible) roolElementStat()
            else unroolElementStat()
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


    private fun roolElementStat() {
        binding.fieldStatisticElementStat.hide()
        binding.arrowVisibleElementsStatField.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24, null)
        )
    }

    private fun unroolElementStat() {
        binding.fieldStatisticElementStat.show()
        binding.arrowVisibleElementsStatField.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24, null)
        )
    }


    private fun roolManageParser() {
        binding.manageParserField.hide()
        binding.arrowVisibleParserManage.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24, null)
        )
    }

    private fun unroolManageParser() {
        binding.manageParserField.show()
        binding.arrowVisibleParserManage.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24, null)
        )
    }

    private fun roolSectionStat() {
        binding.fieldStatisticSectionStat.hide()
        binding.arrowVisibleField.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24, null)
        )
    }

    private fun unrollSectionStat() {
        binding.fieldStatisticSectionStat.show()
        binding.arrowVisibleField.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24, null)
        )
    }

    override fun initObservers() {
        viewModel.currentParserState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is CurrentParserState.ParsingCreate -> {
                    log("CurrentParserState.ParsingCreate ")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadCategory()
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                }
                is CurrentParserState.MenuStart -> {
                    log("CurrentParserState.MenuStart")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                }
                is CurrentParserState.MenuComplete -> {
                    log("CurrentParserState.MenuComplete")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadArticles()
                    loadTaskSectionStat()
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                }
                is CurrentParserState.CatalogStart -> {
                    log("CurrentParserState.CatalogError")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                    visibleElementStat(false)
                }
                is CurrentParserState.CatalogComplete -> {
                    log("CurrentParserState.CatalogComplete")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadOffers()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                    visibleElementStat(false)
                }
                is CurrentParserState.ElementStart -> {

                    // TODO: добавить остановку парсинга

                    log("CurrentParserState.ElementStart")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                    visibleElementStat(false)
                }
                is CurrentParserState.ElementComplete -> {
                    log("CurrentParserState.CatalogError")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    loadTaskElementStat()
                    visibleCatalogStat(true)
                    visibleElementStat(true)
                }
                is CurrentParserState.CatalogError -> {
                    log("CurrentParserState.CatalogError")
                    updateCurrentStatus(state.status)
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    visibleRootProgressBar(false)
                }
                is CurrentParserState.ElementError -> {
                    log("CurrentParserState.ElementError")
                    updateCurrentStatus(state.status)
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    visibleRootProgressBar(false)
                }
                is CurrentParserState.Error -> {
                    log("CurrentParserState.Error")
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    visibleRootProgressBar(false)
                }
                CurrentParserState.LoadingRoot -> {
                    log("CurrentParserState.Loading")
                    visibleRootProgressBar(true)
                }
                is CurrentParserState.MenuError -> {
                    log("CurrentParserState.MenuError")
                    updateCurrentStatus(state.status)
                    visibleRootProgressBar(false)
                }
                is CurrentParserState.NoInternet -> {
                    log("CurrentParserState.NoInternet")
                    visibleRootProgressBar(false)
                }
                CurrentParserState.LoadingSectionStat -> visibleProgressSectionStat(true)
                is CurrentParserState.SuccessLoadSectionStat -> {
                    log("CurrentParserState.SuccessLoadSectionStat work")
                    visibleProgressSectionStat(false)
                    updateSectionStatField(state.data)
                }
                CurrentParserState.LoadingElementStat -> visibleProgressElementStat(true)
                is CurrentParserState.SuccessLoadElementStat -> {
                    visibleProgressElementStat(false)
                    updateElementStatField(state.data)
                }
            }
        }
    }

    private fun updateElementStatField(data: GetElementStatDomain) = with(binding) {
        statCountElements.text =
            resourceProvider.string(R.string.stat_count_element, data.countElements.toString())
        statCountOffers.text =
            resourceProvider.string(R.string.stat_count_offers, data.countOffers.toString())
        totalCountStore.text =
            resourceProvider.string(R.string.stat_count_store, data.countStore.toString())
    }

    private fun updateCurrentStatus(status: String) {
        binding.currentStatus.text = "Статус: $status"
    }

    private fun visibleProgressElementStat(status: Boolean) = with(binding) {
        if (status) {
            progressBarElementStat.show()
            fieldStatisticElementStat.hide()
        } else {
            progressBarElementStat.hide()
            fieldStatisticElementStat.show()
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

    private fun loadTaskElementStat() {
        viewModel.getTaskElementStat()
    }

    private fun visibleCatalogStat(status: Boolean) {
        if (status) binding.rootCatalogStatistic.show()
        else binding.rootCatalogStatistic.hide()
    }

    private fun visibleElementStat(status: Boolean) {
        if (status) binding.rootElementStatistic.show()
        else binding.rootElementStatistic.hide()
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