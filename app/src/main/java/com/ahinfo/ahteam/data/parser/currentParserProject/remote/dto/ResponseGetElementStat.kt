package com.ahinfo.ahteam.data.parser.currentParserProject.remote.dto

import com.google.gson.annotations.SerializedName

data class ResponseGetElementStat(

	@field:SerializedName("stat")
	val stat: Statistic? = null,

	@field:SerializedName("workers")
	val workers: Workers? = null
)

data class Statistic(

	@field:SerializedName("count_elements")
	val countElements: Int? = null,

	@field:SerializedName("count_offers")
	val countOffers: Int? = null,

	@field:SerializedName("count_store")
	val countStore: Int? = null
)

data class Workers(

	@field:SerializedName("live_thread")
	val liveThread: List<String?>? = null
)
