package com.ahinfo.ahteam.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<B : ViewBinding, VM : ViewModel>(private val inflate: Inflate<B>) :
    Fragment(), Navigator {

    private var _viewBinding: B? = null
    protected val binding get() = checkNotNull(_viewBinding)
    protected abstract val viewModel: VM
    protected var toolbar : Toolbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    /**
     * Выносим логику навигации в базовый фрагмент, во фрагментах используем метод [navigateTo]
     */
    override fun navigateTo(resId: Int, args: Bundle?, navOptions: NavOptions?) =
        findNavController().navigate(resId, args, navOptions)

    override fun navigateTo(resId: Int) =
        findNavController().navigate(resId)

    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}