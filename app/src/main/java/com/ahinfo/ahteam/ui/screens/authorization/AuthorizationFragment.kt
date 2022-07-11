package com.ahinfo.ahteam.ui.screens.authorization

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment :
    BaseFragment<FragmentAuthorizationBinding, AuthorizationViewModel>(FragmentAuthorizationBinding::inflate) {
    override val viewModel: AuthorizationViewModel by viewModels()


}