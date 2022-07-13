package com.ahinfo.ahteam.ui.screens.testScreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentTestBinding
import com.ahinfo.ahteam.domain.test.entity.TestModelDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment :
    BaseFragment<FragmentTestBinding, TestViewModel>(FragmentTestBinding::inflate) {
    override val viewModel: TestViewModel by viewModels()

    override fun initView() = with(binding) {

        swipeRefresh.setOnRefreshListener {
            viewModel.fetchTestDataFromServer()
        }
    }

    override fun initObservers() {
        viewModel.testState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TestState.Error -> {
                    isRefreshingFalse()
                }
                TestState.Loading -> {
                    progressBar(true)
                    content(false)
                    isRefreshingFalse()
                }
                is TestState.NoInternet -> {
                    isRefreshingFalse()
                }
                is TestState.Success -> {
                    progressBar(false)
                    content(true)
                    updateUi(state.data)
                }
            }
        }
    }

    private fun content(status: Boolean) = with(binding) {
        if (status) content.isVisible = true
        else content.isGone = true
    }

    private fun progressBar(status: Boolean) = with(binding) {
        if (status) progressBar.isVisible = true
        else progressBar.isGone = true

    }

    @SuppressLint("SetTextI18n")
    private fun updateUi(data: TestModelDomain) = with(binding) {
        isRefreshingFalse()
        itemsLeft.text = "items_left : ${data.itemsLeft}"
        totalItems.text = "total_items :  ${data.totalItems}"
    }

    private fun isRefreshingFalse() = with(binding) {
        swipeRefresh.isRefreshing = false
    }
}