package com.ahinfo.ahteam.ui.screens.parser.testScreen

import android.annotation.SuppressLint
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.databinding.FragmentTestBinding
import com.ahinfo.ahteam.domain.parser.test.entity.TestModelDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TestFragment :
    BaseFragment<FragmentTestBinding, TestViewModel>(FragmentTestBinding::inflate) {
    override val viewModel: TestViewModel by viewModels()

    override fun initView() = with(binding) {

        swipeRefresh.setOnRefreshListener {
            viewModel.fetchTestDataFromServer(viewModel.idProject.value!!)
        }
    }

    override fun initObservers() {
        viewModel.testState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is TestState.Error -> {
                    // TODO: обработать корректно ошибки
                    isRefreshingFalse()
                }
                TestState.Loading -> {
                    progressBar(true)
                    content(false)
                }
                is TestState.NoInternet -> {
                    isRefreshingFalse()
                }
                is TestState.Success -> {
                    isRefreshingFalse()
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