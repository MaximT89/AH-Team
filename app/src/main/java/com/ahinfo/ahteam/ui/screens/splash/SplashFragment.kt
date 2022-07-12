package com.ahinfo.ahteam.ui.screens.splash

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.util.Property
import android.view.View
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.core.extension.playSingleSet
import com.ahinfo.ahteam.core.navigation.Destinations
import com.ahinfo.ahteam.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(FragmentSplashBinding::inflate) {
    override val viewModel: SplashViewModel by viewModels()

    override fun initView() = with(binding){
        animateLogo(logo, 3500, View.ROTATION_Y).playSingleSet {

        }

        animateLogo(imgBackInLogo, 3500, View.ROTATION).playSingleSet {
            navigateTo(Destinations.SPLASH_TO_AUTH.id)
        }

    }

    override fun initObservers() {

    }

    @SuppressLint("Recycle")
    private fun animateLogo(view: View, durationTime: Long, rotation: Property<View, Float>) : AnimatorSet{
        val rotate = ObjectAnimator.ofFloat(view, rotation, 0f, 720f)
        return AnimatorSet().apply {
            duration = durationTime
            play(rotate)
        }
    }
}