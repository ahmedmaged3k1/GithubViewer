package com.example.githubviewer.domain.model

import androidx.room.Dao
import androidx.room.Entity

@Entity
data class License(
    val key: String,
    val name: String,
    val node_id: String,
    val spdx_id: String,
    val url: String
)