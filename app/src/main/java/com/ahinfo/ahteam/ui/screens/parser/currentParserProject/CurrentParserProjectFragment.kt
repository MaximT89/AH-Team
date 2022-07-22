package com.ahinfo.ahteam.ui.screens.parser.currentParserProject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
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
        TODO("Not yet implemented")
    }

    override fun initObservers() {
        TODO("Not yet implemented")
    }

    override fun title() = with(binding){
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding){
        titleField.arrowBack.setOnClickListener { navigateTo(DestinationsParser.CURRENT_PARSER_PROJECT_TO_DETAIL_PROJECT.id) }
    }


}