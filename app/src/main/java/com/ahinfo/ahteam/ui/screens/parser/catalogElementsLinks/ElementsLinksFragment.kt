package com.ahinfo.ahteam.ui.screens.parser.catalogElementsLinks

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.isRefreshingFalse
import com.ahinfo.ahteam.core.extension.isRefreshingTrue
import com.ahinfo.ahteam.core.extension.log
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentElementsLinksBinding
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementsLinksDomain
import com.ahinfo.ahteam.ui.screens.parser.currentParserProject.CurrentParserProjectFragment
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElementsLinksFragment :
    BaseFragment<FragmentElementsLinksBinding, ElementsLinksViewModel>(FragmentElementsLinksBinding::inflate) {
    override val viewModel: ElementsLinksViewModel by viewModels()

    private val catalogAdapter : ElementsLinksAdapter = ElementsLinksAdapter()

    override fun initView() = with(binding){

        swipeRefresh.setOnRefreshListener { isRefreshingFalse(swipeRefresh) }

        recyclerView.adapter = catalogAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){

            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    Snackbar.make(requireActivity(), binding.root, "Долистали до конца", Snackbar.LENGTH_LONG).show()
                    viewModel.incPageAndAddItems()
                }
            }
        })
    }

    override fun listenerBundleArguments() {
        readArguments<Int>(CurrentParserProjectFragment.PARSER_TASK_ID,
        ifExist = {
            viewModel.saveCurrentParserTaskId(it)
            viewModel.getElementsLinks()
        },
        notExist = {
            viewModel.getElementsLinks()
        })
    }

    override fun initObservers() = with(viewModel) {
        elementsLinksState.observe {
            when(it){
                is ElementsLinksState.Error -> {}
                ElementsLinksState.LoadingCatalog -> {
                    isRefreshingTrue(binding.swipeRefresh)
                }
                ElementsLinksState.LoadingFilter -> {}
                ElementsLinksState.LoadingStat -> {}
                is ElementsLinksState.NoInternet -> {}
                is ElementsLinksState.SuccessLoadCatalog -> {
                    updateCatalog(it.data)
                    isRefreshingFalse(binding.swipeRefresh)
                }
                is ElementsLinksState.SuccessLoadFilter -> {}
                is ElementsLinksState.SuccessLoadStat -> {}
            }
        }

        currentPage.observe {
            log("TAG1", "current page = $it")
        }
    }

    private fun updateCatalog(elements: ElementsLinksDomain) {
        val currentElements = catalogAdapter.currentList + elements.elements
        catalogAdapter.submitList(currentElements)
        updateMaxPage(elements.pageCount)
    }

    private fun updateMaxPage(pageCount: Int?) {
        pageCount?.let { viewModel.saveMaxPages(it) }
    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.setOnClickListener {
            navigateTo(DestinationsParser.ELEMENTS_LINKS_TO_CURRENT_PARSER_PROJECT.id)
        }
    }
}