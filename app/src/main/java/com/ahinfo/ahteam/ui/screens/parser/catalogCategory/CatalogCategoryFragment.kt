package com.ahinfo.ahteam.ui.screens.parser.catalogCategory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentCatalogCategoryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogCategoryFragment : BaseFragment<FragmentCatalogCategoryBinding, CatalogCategoryViewModel>(FragmentCatalogCategoryBinding::inflate) {
    override val viewModel: CatalogCategoryViewModel by viewModels()

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
            navigateTo(DestinationsParser.CATALOG_CATEGORY_TO_CURRENT_PARSER_PROJECT.id)
        }
    }
}