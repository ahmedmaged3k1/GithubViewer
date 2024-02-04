package com.example.githubviewer.domain.repository

import androidx.paging.PagingData
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import kotlinx.coroutines.flow.Flow

interface ReposRemoteRepository {

    fun getRepos(): Flow<PagingData<RepoDetailsResponse>>
    suspend fun getRepoDetails(owner: String, repo: String): RepoDetailsLocal
    suspend fun getRepoIssues(owner: String, repo: String): RepoIssuesLocal
    suspend fun getReposList(): List<RepoDetailsResponse>

}