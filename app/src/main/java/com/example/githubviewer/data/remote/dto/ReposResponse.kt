package com.example.githubviewer.data.remote.dto

data class ReposResponse(
    val full_name: String,
    val id: Int,
    val name: String,
    val node_id: String,
    val owner: Owner,
    val `private`: Boolean
)