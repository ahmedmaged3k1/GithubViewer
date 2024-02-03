package com.example.githubviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Reactions(
    val url: String = "No URL available",
    val total_count: Int = -1,
    @SerializedName("+1") val positive1: Int = -1,
    @SerializedName("-1") val negative1: Int = -1,
    val laugh: Int = -1,
    val hooray: Int = -1,
    val confused: Int = -1,
    val heart: Int = -1,
    val rocket: Int = -1,
    val eyes: Int = -1
)