package com.ahinfo.ahteam.ui.screens.parser.catalogCategory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahinfo.ahteam.data.parser.catalogCategory.remote.dto.ElementsItem
import com.ahinfo.ahteam.databinding.HolderCatalogItemBinding

class CatalogCategoriesAdapter :
    ListAdapter<ElementsItem, CatalogCategoriesAdapter.CatalogCategoriesHolder>(ItemComparator()) {

    inner class CatalogCategoriesHolder(val binding: HolderCatalogItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ElementsItem) = with(binding) {
            textCatalogItem.text = item.name
        }
    }

    private class ItemComparator : DiffUtil.ItemCallback<ElementsItem>() {
        override fun areItemsTheSame(oldItem: ElementsItem, newItem: ElementsItem): Boolean {
            return oldItem.sectionId == newItem.sectionId
        }

        override fun areContentsTheSame(oldItem: ElementsItem, newItem: ElementsItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CatalogCategoriesHolder(
            HolderCatalogItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CatalogCategoriesHolder, position: Int) {
        holder.bind(getItem(position))
    }
}