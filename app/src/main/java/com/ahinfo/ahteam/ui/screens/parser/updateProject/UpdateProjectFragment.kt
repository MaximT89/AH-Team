package com.ahinfo.ahteam.ui.screens.parser.updateProject

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentUpdateProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProjectFragment :
    BaseFragment<FragmentUpdateProjectBinding, UpdateProjectViewModel>(FragmentUpdateProjectBinding::inflate) {
    override val viewModel: UpdateProjectViewModel by viewModels()

    override fun initView() {

    }

    override fun initObservers() {

    }
}