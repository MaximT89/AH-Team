package com.ahinfo.ahteam.ui.screens.parser.addProject

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentAddProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddProjectFragment :
    BaseFragment<FragmentAddProjectBinding, AddProjectViewModel>(FragmentAddProjectBinding::inflate) {
    override val viewModel: AddProjectViewModel by viewModels()

    override fun initView() {

    }

    override fun initObservers() {

    }

}