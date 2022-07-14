package com.ahinfo.ahteam.ui.screens.project.sections

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentSectionsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SectionsFragment :
    BaseFragment<FragmentSectionsBinding, SectionsViewModel>(FragmentSectionsBinding::inflate) {
    override val viewModel: SectionsViewModel by viewModels()

    override fun initView() {

    }

    override fun initObservers() {

    }
}