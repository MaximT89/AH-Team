package com.ahinfo.ahteam.data.authorization.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseTodo(

	@field:SerializedName("task")
	val task: String? = null
)
