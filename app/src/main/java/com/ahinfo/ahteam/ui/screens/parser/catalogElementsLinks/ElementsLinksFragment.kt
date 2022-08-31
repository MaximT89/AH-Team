package com.ahinfo.ahteam.ui.screens.parser.catalogElementsLinks

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentElementsLinksBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ElementsLinksFragment :
    BaseFragment<FragmentElementsLinksBinding, ElementsLinksViewModel>(FragmentElementsLinksBinding::inflate) {
    override val viewModel: ElementsLinksViewModel by viewModels()

    override fun initView(): Unit? {
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
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