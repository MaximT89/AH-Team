package com.ahinfo.ahteam.ui.screens.authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentAuthorizationBinding
import com.ahinfo.ahteam.ui.screens.splash.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment :
    BaseFragment<FragmentAuthorizationBinding, AuthorizationViewModel>(FragmentAuthorizationBinding::inflate) {

    override val viewModel: AuthorizationViewModel by viewModels()

    override fun initObservers() {

        viewModel.authState.observe(viewLifecycleOwner) { state ->

            when (state) {
                is AuthState.Error -> {}
                AuthState.Loading -> {}
                is AuthState.NoInternet -> {}
                is AuthState.Success -> {
                    binding.testTask.text = state.data.task
                }
            }
        }
    }

    override fun initView() {
        requireActivity().onBackPressedDispatcher

    }


}