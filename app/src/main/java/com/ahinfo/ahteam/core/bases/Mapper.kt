package com.ahinfo.ahteam.core.bases

interface Mapper<T, R> {

    fun map(data : T) : R
}