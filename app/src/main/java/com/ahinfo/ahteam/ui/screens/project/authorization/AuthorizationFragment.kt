package com.ahinfo.ahteam.ui.screens.project.authorization

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.hide
import com.ahinfo.ahteam.databinding.FragmentAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment :
    BaseFragment<FragmentAuthorizationBinding, AuthorizationViewModel>(FragmentAuthorizationBinding::inflate) {

    override val viewModel: AuthorizationViewModel by viewModels()

    override fun initObservers() {

    }

    override fun initView() {

    }

    override fun title() = with(binding){
        titleField.title.text = viewModel.title()
    }

    override fun navigationArrowBack() = with(binding){
        titleField.arrowBack.hide()
    }
}