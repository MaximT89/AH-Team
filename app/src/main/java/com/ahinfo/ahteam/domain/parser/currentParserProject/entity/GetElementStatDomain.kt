package com.ahinfo.ahteam.domain.parser.currentParserProject.entity

data class GetElementStatDomain(
    val countElements: Int,
    val countOffers: Int,
    val countStore: Int,
    val liveThread: List<String?>?
)