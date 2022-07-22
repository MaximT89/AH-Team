package com.ahinfo.ahteam.data.parser.detailsProject.remote.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ResponseGetProjectTasks(

	@field:SerializedName("summary")
	val summary: Int? = null,

	@field:SerializedName("elements")
	val elements: List<ElementsItemTask?>? = null,

	@field:SerializedName("page_count")
	val pageCount: Int? = null
)
@Parcelize
data class ElementsItemTask(

	@field:SerializedName("site")
	val site: String? = null,

	@field:SerializedName("project_id")
	val projectId: Int? = null,

	@field:SerializedName("parsing_id")
	val parsingId: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("unix_time")
	val unixTime: Long? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("status")
	val status: String? = null
) : Parcelable
