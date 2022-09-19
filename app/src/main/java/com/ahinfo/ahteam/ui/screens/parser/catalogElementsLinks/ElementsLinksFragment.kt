package com.ahinfo.ahteam.ui.screens.parser.catalogElementsLinks

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentElementsLinksBinding
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementLinks
import com.ahinfo.ahteam.ui.screens.parser.currentParserProject.CurrentParserProjectFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElementsLinksFragment :
    BaseFragment<FragmentElementsLinksBinding, ElementsLinksViewModel>(FragmentElementsLinksBinding::inflate) {
    override val viewModel: ElementsLinksViewModel by viewModels()

    private val catalogAdapter : ElementsLinksAdapter = ElementsLinksAdapter()

    override fun initView() = with(binding){
        recyclerView.adapter = catalogAdapter
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

    override fun initObservers() {
        viewModel.elementsLinksState.observe {
            when(it){
                is ElementsLinksState.Error -> {}
                ElementsLinksState.LoadingCatalog -> {}
                ElementsLinksState.LoadingFilter -> {}
                ElementsLinksState.LoadingStat -> {}
                is ElementsLinksState.NoInternet -> {}
                is ElementsLinksState.SuccessLoadCatalog -> updateCatalog(it.data.elements)
                is ElementsLinksState.SuccessLoadFilter -> {}
                is ElementsLinksState.SuccessLoadStat -> {}
            }
        }
    }

    private fun updateCatalog(elements: List<ElementLinks>) {
        catalogAdapter.submitList(elements)
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