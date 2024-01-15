package com.takehomechallenge.ahmadi.data.model
import com.google.gson.annotations.SerializedName


data class BaseResponse<T>(
    @SerializedName("info")
    val info: Info? = Info(),
    @SerializedName("results")
    val results: List<T> = listOf()
)

data class Info(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("next")
    val next: String? = "",
    @SerializedName("pages")
    val pages: Int? = 0,
    @SerializedName("prev")
    val prev: String? = ""
)