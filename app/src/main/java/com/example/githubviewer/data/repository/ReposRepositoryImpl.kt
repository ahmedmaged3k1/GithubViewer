package com.example.githubviewer.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.remote.ReposPagingSource
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.ReposResponse
import com.example.githubviewer.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ReposRepositoryImpl(private val reposApi: ReposApi)
    :ReposRepository {
    private val _allDetailedRepos = MutableStateFlow<List<RepoDetailsResponse>>(emptyList())
    override fun getAllDetailedRepos(): Flow<List<RepoDetailsResponse>> = _allDetailedRepos

    override fun getRepos(): Flow<PagingData<RepoDetailsResponse>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                ReposPagingSource(
                    reposApi = reposApi,
                )
            }
        ).flow

    }

    override suspend fun getRepoDetails(owner: String, repo: String): RepoDetailsResponse {
        val repoDetails = reposApi.getRepoDetails(owner, repo)

        // Update the list of detailed repos
        _allDetailedRepos.value = _allDetailedRepos.value + listOf(repoDetails)

        return repoDetails
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}