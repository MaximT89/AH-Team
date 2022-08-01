package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentCurrentParserProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrentParserProjectFragment :
    BaseFragment<FragmentCurrentParserProjectBinding, CurrentParserProjectViewModel>(
        FragmentCurrentParserProjectBinding::inflate
    ) {
    override val viewModel: CurrentParserProjectViewModel by viewModels()

    override fun initView() {

    }

    override fun initObservers() {

    }

    override fun title() = with(binding){
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding){
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.CURRENT_PARSER_PROJECT_TO_DETAIL_PROJECT.id) }
    }
}