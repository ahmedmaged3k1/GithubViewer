package com.example.githubviewer.data.remote

import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReposApi {
    @GET("repositories")
    suspend fun getRepos(@Query("page") page: Int): List<RepoDetailsResponse>

    @GET("repos/{owner}/{repo}")
    suspend fun getRepoDetails(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): RepoDetailsResponse
    @GET("repos/{owner}/{repo}/issues")
    suspend fun getRepoIssues(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): List<RepoIssuesResponse>
}