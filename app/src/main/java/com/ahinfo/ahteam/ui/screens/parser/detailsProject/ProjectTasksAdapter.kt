package com.ahinfo.ahteam.ui.screens.parser.detailsProject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ahinfo.ahteam.R
import com.ahinfo.ahteam.core.common.ResourceProvider
import com.ahinfo.ahteam.core.extension.convertToDate
import com.ahinfo.ahteam.data.parser.detailsProject.remote.dto.ElementsItemTask
import com.ahinfo.ahteam.databinding.HolderProjectTaskBinding

class ProjectTasksAdapter(private val resourceProvider: ResourceProvider) :
    ListAdapter<ElementsItemTask, ProjectTasksAdapter.ProjectTaskHolder>(ItemComparator()) {

    var callBackUpdateTask: ((item: ElementsItemTask) -> Unit)? = null
    var callBackDeleteTask: ((id: Int?) -> Unit)? = null
    var callBackNavigateForTask: ((item: ElementsItemTask) -> Unit)? = null

    private class ItemComparator : DiffUtil.ItemCallback<ElementsItemTask>() {
        override fun areItemsTheSame(oldItem: ElementsItemTask, newItem: ElementsItemTask): Boolean {
            return oldItem.parsingId == newItem.parsingId
        }

        override fun areContentsTheSame(oldItem: ElementsItemTask, newItem: ElementsItemTask): Boolean {
            return oldItem == newItem
        }
    }

    inner class ProjectTaskHolder(private val binding: HolderProjectTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ElementsItemTask) = with(binding) {

            nameProject.text = item.name ?: ""
            descriptionProject.text = resourceProvider.string(R.string.description_task, item.description)

            idProject.text = resourceProvider.string(R.string.id_project_adapter, item.projectId.toString())
            idTask.text = resourceProvider.string(R.string.id_task_adapter, item.parsingId.toString())
            statusProject.text = resourceProvider.string(R.string.status_task_adapter, item.status)
            dateCreateProject.text = resourceProvider.string(R.string.date_task_adapter, item.unixTime?.convertToDate())

            imgPencil.setOnClickListener { callBackUpdateTask?.invoke(item) }
            imgTrash.setOnClickListener { callBackDeleteTask?.invoke(item.parsingId) }
            body.setOnClickListener { callBackNavigateForTask?.invoke(item) }
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