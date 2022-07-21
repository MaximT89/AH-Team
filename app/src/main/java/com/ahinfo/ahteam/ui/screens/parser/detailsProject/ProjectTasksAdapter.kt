package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.extension.convertToDate
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItem
import com.ahinfo.ahteam.databinding.HolderProjectTaskBinding

class ProjectTasksAdapter(private val resourceProvider: ResourceProvider) :
    ListAdapter<ElementsItem, ProjectTasksAdapter.ProjectTaskHolder>(ItemComparator()) {


    var callBackUpdateTask: ((item: ElementsItem) -> Unit)? = null
    var callBackDeleteTask: ((id: Int?) -> Unit)? = null

    class ItemComparator : DiffUtil.ItemCallback<ElementsItem>() {
        override fun areItemsTheSame(oldItem: ElementsItem, newItem: ElementsItem): Boolean {
            return oldItem.parsingId == newItem.parsingId
        }

        override fun areContentsTheSame(oldItem: ElementsItem, newItem: ElementsItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class ProjectTaskHolder(private val binding: HolderProjectTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: ElementsItem) = with(binding) {

            // TODO: переписать на ресурсы
            nameProject.text = resourceProvider.string(R.string.name_task, item.name)
            descriptionProject.text = "Описание задачи: ${item.description}"
            idProject.text = "ID проекта: ${item.projectId}"
            idTask.text = "ID задачи: ${item.parsingId}"
            statusProject.text = "Статус: ${item.status}"
            dateCreateProject.text = "Дата: ${item.unixTime?.convertToDate()}"

            imgPencil.setOnClickListener {
                callBackUpdateTask?.invoke(item)
            }

            imgTrash.setOnClickListener {
                callBackDeleteTask?.invoke(item.parsingId)
            }

            root.setOnClickListener {
                // TODO: сделать переход в детализацию задачи
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectTaskHolder {
        return ProjectTaskHolder(
            HolderProjectTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProjectTaskHolder, position: Int) {
        holder.bind(getItem(position))
    }
}