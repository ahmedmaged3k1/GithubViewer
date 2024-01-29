package com.example.githubviewer.data.remote

import com.example.githubviewer.data.remote.dto.ReposResponse
import retrofit2.http.GET

interface ReposApi {
    @GET("repositories")
    suspend fun getRepos(): ReposResponse
}