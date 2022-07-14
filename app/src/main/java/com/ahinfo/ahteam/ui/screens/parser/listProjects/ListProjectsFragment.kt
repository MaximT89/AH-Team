package com.ahinfo.ahteam.ui.screens.parser.listProjects

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentListProjectsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListProjectsFragment :
    BaseFragment<FragmentListProjectsBinding, ListProjectsViewModel>(FragmentListProjectsBinding::inflate) {
    override val viewModel: ListProjectsViewModel by viewModels()

    override fun initView() {
    }

    override fun initObservers() {
    }


}