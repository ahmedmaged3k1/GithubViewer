package com.example.githubviewer.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.remote.ReposPagingSource
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class ReposRepositoryImpl(private val reposApi: ReposApi,
                          private val repoDao: RepoDao
)
    :ReposRepository {
    val _allDetailedRepos = MutableStateFlow<List<RepoDetailsResponse>>(emptyList())

    override fun getRepos(): Flow<PagingData<RepoDetailsResponse>> {
        Log.d("TAG", "getRepos: paging ")
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                ReposPagingSource(
                    reposApi = reposApi,
                    repoDao = repoDao
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

    override suspend fun getRepoIssues(owner: String, repo: String): List<RepoIssuesResponse> {

        return reposApi.getRepoIssues(owner, repo)
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}