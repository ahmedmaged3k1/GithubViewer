package com.example.githubviewer.domain.repository

import androidx.paging.PagingData
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.ReposResponse
import kotlinx.coroutines.flow.Flow

interface ReposRepository {
    fun getRepos(): Flow<PagingData<RepoDetailsResponse>>
}