package com.example.githubviewer.domain.repository

import androidx.paging.PagingData
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import kotlinx.coroutines.flow.Flow

interface ReposRepository {

    fun getRepos(): Flow<PagingData<RepoDetailsResponse>>
     suspend fun getRepoDetails(owner: String, repo: String): RepoDetailsResponse
    fun getAllDetailedRepos(): Flow<List<RepoDetailsResponse>>
}