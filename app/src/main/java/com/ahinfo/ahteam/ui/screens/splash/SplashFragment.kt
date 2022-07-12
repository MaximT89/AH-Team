package com.ahinfo.ahteam.ui.screens.splash

import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {
    override val viewModel: SplashViewModel by viewModels()

    override fun initView() {
    }

    override fun initObservers() {
    }


}