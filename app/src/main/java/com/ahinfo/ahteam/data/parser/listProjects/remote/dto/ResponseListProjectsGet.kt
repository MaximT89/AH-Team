package com.ahinfo.ahteam.data.parser.listProjects.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseListProjects(

	@field:SerializedName("summary")
	val summary: Int? = null,

	@field:SerializedName("elements")
	val elements: List<ElementsItemProject?>? = null,

	@field:SerializedName("page_count")
	val pageCount: Int? = null
)

@Parcelize
data class ElementsItemProject(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("unix_time")
	val unixTime: Long? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("project_id")
	val id: Int? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
