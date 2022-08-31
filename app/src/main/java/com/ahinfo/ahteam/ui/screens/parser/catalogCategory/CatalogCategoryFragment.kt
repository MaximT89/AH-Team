package com.ahinfo.ahteam.ui.screens.parser.catalogCategory

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.hide
import com.ahinfo.ahteam.core.extension.isRefreshingFalse
import com.ahinfo.ahteam.core.extension.isRefreshingTrue
import com.ahinfo.ahteam.core.extension.show
import com.ahinfo.ahteam.core.navigation.DestinationsParser
import com.ahinfo.ahteam.databinding.FragmentCatalogCategoryBinding
import com.ahinfo.ahteam.domain.parser.catalogCategory.entity.GetCatalogCategoriesDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogCategoryFragment : BaseFragment<FragmentCatalogCategoryBinding, CatalogCategoryViewModel>(FragmentCatalogCategoryBinding::inflate) {
    override val viewModel: CatalogCategoryViewModel by viewModels()

    private val catalogAdapter = CatalogCategoriesAdapter()

    override fun initView() = with(binding){
        recyclerView.adapter = catalogAdapter

        swipeRefresh.setOnRefreshListener {
            viewModel.updateCatalogCategories()
        }
    }

    override fun initObservers() = with(binding) {
        viewModel.catalogCategoriesState.observe {
            when(it){
                is CatalogCategoriesState.Error -> {}
                CatalogCategoriesState.Loading -> {
                    isRefreshingTrue(swipeRefresh)
                    recyclerView.hide()
                }
                is CatalogCategoriesState.NoInternet -> {}
                is CatalogCategoriesState.Success -> {
                    isRefreshingFalse(swipeRefresh)
                    recyclerView.show()
                    updateUi(it.data)
                }
            }
        }
    }

    private fun updateUi(data: GetCatalogCategoriesDomain) {
        catalogAdapter.submitList(data.listElements)
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