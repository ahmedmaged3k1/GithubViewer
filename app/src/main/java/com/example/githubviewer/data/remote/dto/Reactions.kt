package com.example.githubviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class Reactions(
    val url: String,
    val total_count: Int,
    @SerializedName("+1") val positive1: Int,
    @SerializedName("-1") val negative1: Int,
    val laugh: Int,
    val hooray: Int,
    val confused: Int,
    val heart: Int,
    val rocket: Int,
    val eyes: Int
)