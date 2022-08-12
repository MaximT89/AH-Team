package com.ahinfo.ahteam.data.parser.detailsProject.remote.dto

import com.google.gson.annotations.SerializedName

data class RequestDeleteProjectTask(

	@field:SerializedName("ids")
	val ids: List<Int?>? = null
)
