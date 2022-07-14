package com.ahinfo.ahteam.ui.screens.project.authorization

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentAuthorizationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthorizationFragment :
    BaseFragment<FragmentAuthorizationBinding, AuthorizationViewModel>(FragmentAuthorizationBinding::inflate) {

    override val viewModel: AuthorizationViewModel by viewModels()

    override fun initObservers() {

        viewModel.authState.observe(viewLifecycleOwner) { state ->

            // TODO: обработать как следует
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