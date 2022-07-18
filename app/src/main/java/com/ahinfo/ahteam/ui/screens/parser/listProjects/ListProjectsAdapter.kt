package com.ahinfo.ahteam.ui.screens.parser.listProjects

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahinfo.ahteam.core.extension.convertToDate
import com.ahinfo.ahteam.data.parser.listProjects.remote.dto.ElementsItem
import com.ahinfo.ahteam.databinding.HolderListProjectBinding

class ListProjectsAdapter :
    ListAdapter<ElementsItem, ListProjectsAdapter.ProjectHolder>(ItemComparator()) {

    var callBackDeleteProject : ((id : Int) -> Unit)? = null
    var callBackUpgradeProject : ((id : Int) -> Unit)? = null

    class ItemComparator : DiffUtil.ItemCallback<ElementsItem>() {
        override fun areItemsTheSame(oldItem: ElementsItem, newItem: ElementsItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ElementsItem, newItem: ElementsItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ProjectHolder(private val binding: HolderListProjectBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ElementsItem) = with(binding) {

            nameProject.text = "Название: ${item.name ?: ""}"
            descriptionProject.text = "Описание: ${item.description ?: ""}"

            idProject.text = "Id проекта: ${item.id.toString()}"
            statusProject.text = "Статус: ${item.status}"
            dateCreateProject.text = "Дата создания: ${item.unixTime?.convertToDate() ?: ""}"

            imgTrash.setOnClickListener {
                callBackDeleteProject?.invoke(item.id!!)
            }

            imgPencil.setOnClickListener {
                callBackUpgradeProject?.invoke(item.id!!)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectHolder {
        return ProjectHolder(
            HolderListProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProjectHolder, position: Int) {
        holder.bind(getItem(position))
    }
}