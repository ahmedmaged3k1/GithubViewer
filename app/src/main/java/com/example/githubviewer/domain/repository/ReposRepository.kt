package com.example.githubviewer.domain.repository

import androidx.paging.PagingData
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import kotlinx.coroutines.flow.Flow

interface ReposRepository {

    fun getRepos(): Flow<PagingData<RepoDetailsResponse>>
     suspend fun getRepoDetails(owner: String, repo: String): RepoDetailsResponse
    suspend fun getRepoIssues(owner: String, repo: String): List<RepoIssuesResponse>

}