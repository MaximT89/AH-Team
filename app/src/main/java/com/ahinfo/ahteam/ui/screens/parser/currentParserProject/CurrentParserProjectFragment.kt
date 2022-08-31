package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import android.annotation.SuppressLint
import android.content.Intent
import android.view.View
import android.widget.ImageView
import androidx.core.view.isGone
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

    companion object {
        const val PARSER_TASK_ID = "parser_task_id"
    }

    override val viewModel: CurrentParserProjectViewModel by viewModels()

    @Inject
    lateinit var resourceProvider: ResourceProvider

    override fun initView() = with(binding) {

        btnReloadCategoryStat.setOnClickListener { viewModel.getTaskSectionStat() }

        btnReloadElementStat.setOnClickListener { viewModel.getTaskElementStat() }

        btnParserStop.setOnClickListener { viewModel.parserStop() }

        btnCategory.setOnClickListener {
            navigateTo(DestinationsParser.CURRENT_PARSER_PROJECT_TO_CATALOG_CATEGORY.id)
        }

        btnCatalogFull.setOnClickListener {
            navigateTo(DestinationsParser.CURRENT_PARSER_PROJECT_TO_ELEMENTS_LINKS.id)
        }

        btnShareCatalogStat.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, viewModel.getSectionStatShareText())
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Статистика по каталогу: "))
        }

        btnShareElementStat.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, viewModel.getElementStatShareText())
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Статистика по элементам: "))
        }

        rootHeaderSectionStatField.setOnClickListener {
            if (fieldStatisticSectionStat.isVisible) rool(
                fieldStatisticSectionStat,
                arrowVisibleField
            )
            else unrool(fieldStatisticSectionStat, arrowVisibleField)
        }

        rootHeaderParserManage.setOnClickListener {
            if (manageParserField.isVisible) rool(manageParserField, arrowVisibleParserManage)
            else unrool(manageParserField, arrowVisibleParserManage)
        }

        rootHeaderElementStatField.setOnClickListener {
            if (fieldStatisticElementStat.isVisible) rool(
                fieldStatisticElementStat,
                arrowVisibleElementsStatField
            )
            else unrool(fieldStatisticElementStat, arrowVisibleElementsStatField)
        }

        rootHeaderNavCatalog.setOnClickListener {
            if (fieldNavCatalogBtns.isVisible) rool(fieldNavCatalogBtns, arrowVisibleNavCatalog)
            else unrool(fieldNavCatalogBtns, arrowVisibleNavCatalog)
        }
    }

    override fun listenerBundleArguments() {
        readArguments<String>(PARSER_TASK_ID,
            ifExist = {
                viewModel.saveCurrentTaskId(it)
                viewModel.getCurrentTaskStatus()
            })

        readArguments<ElementsItemTask>(DetailProjectFragment.ITEM_TASK,
            ifExist = { itemTask ->
                viewModel.saveCurrentTaskId(itemTask)
                viewModel.getCurrentTaskStatus()
            },
            notExist = {
                viewModel.getCurrentTaskStatus()
            })
    }

    private fun updateCurrentTaskId(id: Int) {
        binding.currentTaskId.text = "Parser: $id"
    }

    private fun activeBtnsCatalog(count: Int) = with(binding) {
        when (count) {
            -1 -> {
                rootNavWatchingOffers.hide()
            }
            0 -> {
                rootNavWatchingOffers.show()
                btnCategory.notActive()
                btnCatalogSimple.notActive()
                btnCatalogFull.notActive()
            }
            1 -> {
                rootNavWatchingOffers.show()
                btnCategory.active()
                btnCatalogSimple.notActive()
                btnCatalogFull.notActive()
            }
            2 -> {
                rootNavWatchingOffers.show()
                btnCategory.active()
                btnCatalogSimple.active()
                btnCatalogFull.notActive()
            }
            3 -> {
                rootNavWatchingOffers.show()
                btnCategory.active()
                btnCatalogSimple.active()
                btnCatalogFull.active()
            }
        }
    }

    private fun rool(roolView: View, imgArrow: ImageView) {
        roolView.hide()
        imgArrow.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_down_24, null)
        )
    }

    private fun unrool(roolView: View, imgArrow: ImageView) {
        roolView.show()
        imgArrow.setImageDrawable(
            resources.getDrawable(R.drawable.ic_baseline_keyboard_arrow_up_24, null)
        )
    }

    override fun initObservers() {
        viewModel.currentParserState.observe { state ->
            when (state) {
                is CurrentParserState.ParsingCreate -> {
                    log("CurrentParserState.ParsingCreate ")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadCategory()
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    activeBtnsCatalog(0)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.MenuStart -> {
                    log("CurrentParserState.MenuStart")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    activeBtnsCatalog(0)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.MenuComplete -> {
                    log("CurrentParserState.MenuComplete")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadArticles()
                    loadTaskSectionStat()
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    activeBtnsCatalog(1)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.CatalogStart -> {
                    log("CurrentParserState.CatalogError")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                    visibleElementStat(false)
                    activeBtnsCatalog(1)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.CatalogComplete -> {
                    log("CurrentParserState.CatalogComplete")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleRootProgressBar(false)
                    showBtnsField()
                    showBtnDownloadOffers()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                    visibleElementStat(false)
                    activeBtnsCatalog(2)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.ElementStart -> {
                    log("CurrentParserState.ElementStart")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    visibleCatalogStat(true)
                    visibleElementStat(false)
                    activeBtnsCatalog(2)
                    visibleBtnStopParser(true)
                }
                is CurrentParserState.ElementComplete -> {
                    log("CurrentParserState.CatalogError")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleRootProgressBar(false)
                    showBtnsField()
                    allBtnNotActive()
                    loadTaskSectionStat()
                    loadTaskElementStat()
                    visibleCatalogStat(true)
                    visibleElementStat(true)
                    activeBtnsCatalog(3)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.CatalogError -> {
                    log("CurrentParserState.CatalogError")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    visibleRootProgressBar(false)
                    activeBtnsCatalog(-1)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.ElementError -> {
                    log("CurrentParserState.ElementError")
                    updateCurrentStatus(state.status)
                    updateCurrentTaskId(viewModel.loadCurrentTaskId())
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    visibleRootProgressBar(false)
                    activeBtnsCatalog(-1)
                    visibleBtnStopParser(false)
                }
                is CurrentParserState.Error -> {
                    log("CurrentParserState.Error")
                    visibleCatalogStat(false)
                    visibleElementStat(false)
                    visibleRootProgressBar(false)
                    activeBtnsCatalog(-1)
                    visibleBtnStopParser(false)
                }
                CurrentParserState.LoadingRoot -> {
                    log("CurrentParserState.Loading")
                    visibleRootProgressBar(true)
                    visibleProgressSectionStat(true)
                    visibleProgressElementStat(true)
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

    private fun visibleBtnStopParser(status: Boolean) {
        if (status) binding.btnParserStop.isVisible = true
        else binding.btnParserStop.isGone = true
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