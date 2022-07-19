package com.ahinfo.ahteam.ui.screens.project.splash

import android.view.View
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.constants.Constants
import com.ahinfo.ahteam.core.extension.playSingleSet
import com.ahinfo.ahteam.core.navigation.DestinationsParser
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
        navigateTo(DestinationsParser.SPLASH_TO_SECTIONS.id)
    }

    override fun initObservers() {
        // TODO: тут нужно будет определить зареганный пользователь или нет
        // TODO: после получения информации нужно отправить пользователя или на авторизацию или на главную
    }

    // На splash экране не будет toolbar , поэтому делаем заглушки для title и кнопки назад
    override fun title() = Unit
    override fun navigationArrowBack() = Unit
}