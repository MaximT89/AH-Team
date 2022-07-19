package com.ahinfo.ahteam.ui.screens.project.sections

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.hide
import com.ahinfo.ahteam.core.navigation.Destinations
import com.ahinfo.ahteam.databinding.FragmentSectionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionsFragment :
    BaseFragment<FragmentSectionsBinding, SectionsViewModel>(FragmentSectionsBinding::inflate) {
    override val viewModel: SectionsViewModel by viewModels()

    override fun initView() = with(binding) {
        btnSectionParsing.setOnClickListener { navigateTo(Destinations.SECTIONS_TO_LIST_PROJECTS.id) }
    }

    override fun initObservers() {

    }

    override fun title() = with(binding) {
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding) {
        titleField.arrowBack.hide()
    }
}