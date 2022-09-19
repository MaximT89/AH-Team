package com.ahinfo.ahteam.ui.screens.parser.catalogElementsLinks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.databinding.HolderCatalogElementsLinksBinding
import com.ahinfo.ahteam.domain.parser.catalogElementsLinks.entity.ElementLinks
import com.bumptech.glide.Glide

class ElementsLinksAdapter :
    ListAdapter<ElementLinks, ElementsLinksAdapter.CatalogHolder>(ItemComparator()) {

    private class ItemComparator : DiffUtil.ItemCallback<ElementLinks>() {
        override fun areItemsTheSame(oldItem: ElementLinks, newItem: ElementLinks): Boolean {
            return oldItem.elementId == newItem.elementId
        }

        override fun areContentsTheSame(oldItem: ElementLinks, newItem: ElementLinks): Boolean {
            return oldItem == newItem
        }
    }

    inner class CatalogHolder(val binding: HolderCatalogElementsLinksBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ElementLinks) = with(binding){

            catalogItemName.text = item.name
            catalogItemArticle.text = item.article
            catalogItemPrice.text = "${item.price} ₽"
            if(item.oldPrice != null) catalogItemOldPrice.text = "${item.oldPrice} ₽"

            Glide.with(binding.root)
                .load(item.img)
                .placeholder(R.drawable.logo)
                .into(catalogImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogHolder {
        return CatalogHolder(HolderCatalogElementsLinksBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CatalogHolder, position: Int) {
        holder.bind(getItem(position))
    }
}