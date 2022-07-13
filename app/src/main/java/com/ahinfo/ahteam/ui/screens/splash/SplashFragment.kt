package com.ahinfo.ahteam.ui.screens.splash

import android.view.View
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.constants.Constants
import com.ahinfo.ahteam.core.extension.playSingleSet
import com.ahinfo.ahteam.core.navigation.Destinations
import com.ahinfo.ahteam.databinding.FragmentSplashBinding
import com.ahinfo.ahteam.ui.animations.rotateAnimations
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {
    override val viewModel: SplashViewModel by viewModels()

    override fun initView() = with(binding){
        logo.rotateAnimations(Constants.SPEED_ANIMATION, View.ROTATION_Y).playSingleSet()

        imgBackInLogo.rotateAnimations(Constants.SPEED_ANIMATION, View.ROTATION).playSingleSet {
            navigateAfterCheckAuth()
        }

    }

    private fun navigateAfterCheckAuth() {
//        navigateTo(Destinations.SPLASH_TO_AUTH.id)
        navigateTo(Destinations.SPLASH_TO_TEST.id)
    }

    override fun initObservers() {
        // TODO: тут нужно будет определить зареганный пользователь или нет
        // TODO: после получения информации нужно отправить пользователя или на авторизацию или на главную
    }


}