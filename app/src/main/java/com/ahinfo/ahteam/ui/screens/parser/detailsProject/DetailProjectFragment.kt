package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentDetailProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailProjectFragment :
    BaseFragment<FragmentDetailProjectBinding, DetailProjectViewModel>(FragmentDetailProjectBinding::inflate) {
    override val viewModel: DetailProjectViewModel by viewModels()

    override fun initView() {

    }

    override fun initObservers() {

    }


}