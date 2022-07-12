package com.ahinfo.ahteam.ui.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.util.Property
import android.view.View

/**
 * [rotation] определяет вокруг какой оси будет происходить вращение
 * @View.ROTATION_Y - вращение вокруг оси Y
 * @View.ROTATION_X - вращение вокруг оси X
 * @View.ROTATION - вращение view вправо или влево в зависимости от знака второго value в аргументах ObjectAnimator.ofFloat
 */
fun View.rotateAnimations(durationTime: Long, rotation: Property<View, Float> ) : AnimatorSet{
    val rotate = ObjectAnimator.ofFloat(this, rotation, 0f, 720f)
    return AnimatorSet().apply {
        duration = durationTime
        play(rotate)
    }
}
