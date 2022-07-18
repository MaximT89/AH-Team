package com.ahinfo.ahteam.ui.screens.parser.updateProject

import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.ahinfo.ahteam.core.bases.BaseFragment
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItem
import com.ahinfo.ahteam.databinding.FragmentUpdateProjectBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateProjectFragment :
    BaseFragment<FragmentUpdateProjectBinding, UpdateProjectViewModel>(FragmentUpdateProjectBinding::inflate) {
    override val viewModel: UpdateProjectViewModel by viewModels()

    override fun initView() {
        setFragmentResultListener("upgrade_parser_project") { _, bundle ->
            val parserItem = bundle.getParcelable<ElementsItem>("parser_project")
            viewModel.updateElementsItem(parserItem!!)
        }


    }

    override fun initObservers() {
        viewModel.elementsItem.observe(viewLifecycleOwner) { elementItem ->
            updateUi(elementItem)
        }
    }

    private fun updateUi(parserItem: ElementsItem?) = with(binding) {
        editNameProject.setText(parserItem?.name ?: "")
        editTextDescription.setText(parserItem?.description ?: "")
    }
}