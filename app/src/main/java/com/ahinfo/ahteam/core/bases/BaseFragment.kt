package com.ahinfo.ahteam.core.bases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.navigation.Navigator
import com.google.android.material.snackbar.Snackbar

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

/**
 * Базовый фрагмент, наследуемся от него у всех фрагментов, данная база уменьшает шаблонный код,
 * в данной базе мы сразу получаем биндинг и переопределяем метод на присваивание viewModel
 */
abstract class BaseFragment<B : ViewBinding, VM : ViewModel>(private val inflate: Inflate<B>) :
    Fragment(), Navigator {

    private var _viewBinding: B? = null
    protected val binding get() = checkNotNull(_viewBinding)
    protected abstract val viewModel: VM
    protected var toolbar: Toolbar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewBinding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initObservers()
    }

    abstract fun initView()
    abstract fun initObservers()

    fun showSnackbar(view: View, message: String) {
        Snackbar.make(requireActivity(), view, message, Snackbar.LENGTH_LONG).show()
    }

    fun string(@StringRes id: Int): String = requireActivity().getString(id)

    /**
     * Выносим логику навигации в базовый фрагмент, во фрагментах используем метод [navigateTo]
     */
    override fun navigateTo(resId: Int, args: Bundle?, navOptions: NavOptions?) =
        findNavController().navigate(resId, args, navOptions)

    override fun navigateTo(resId: Int) =
        findNavController().navigate(resId)

    /**
     * Очищаем биндинг во всех фрагментах
     */
    override fun onDestroy() {
        super.onDestroy()
        _viewBinding = null
    }
}