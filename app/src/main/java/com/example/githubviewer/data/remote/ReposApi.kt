package com.example.githubviewer.data.remote

import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.ReposResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface ReposApi {
    @GET("repositories")
    suspend fun getRepos(): List<RepoDetailsResponse>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepoDetails(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepoDetailsResponse
}