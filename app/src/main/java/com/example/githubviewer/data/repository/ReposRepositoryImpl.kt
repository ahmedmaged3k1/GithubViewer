package com.example.githubviewer.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.remote.ReposPagingSource
import com.example.githubviewer.data.remote.dto.ReposResponse
import com.example.githubviewer.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow

class ReposRepositoryImpl(private val reposApi: ReposApi)
    :ReposRepository {
    override fun getRepos(): Flow<PagingData<ReposResponse>> {
        return  Pager(
            config =PagingConfig(pageSize = 10),
            pagingSourceFactory={
                ReposPagingSource(
                    reposApi = reposApi,
                )
            }
        ).flow
    }
}