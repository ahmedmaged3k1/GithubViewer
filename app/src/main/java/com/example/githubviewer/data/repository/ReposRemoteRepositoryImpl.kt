package com.example.githubviewer.data.repository

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.remote.ReposPagingSource
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.repository.ReposLocalRepository
import com.example.githubviewer.domain.repository.ReposRemoteRepository
import kotlinx.coroutines.flow.Flow

class ReposRemoteRepositoryImpl(
    private val reposApi: ReposApi,
    private val reposLocalRepository: ReposLocalRepository
) : ReposRemoteRepository {

    override fun getRepos(): Flow<PagingData<RepoDetailsResponse>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                ReposPagingSource(reposApi)
            }
        ).flow
    }

    override suspend fun getReposList(): List<RepoDetailsResponse> {
        return reposApi.getRepos(1)
    }

    override suspend fun getRepoDetails(owner: String, repo: String): RepoDetailsLocal {
        try {
            val repoDetails = reposApi.getRepoDetails(owner, repo)

            if (repoDetails == RepoDetailsResponse()) return RepoDetailsLocal()

            val localRepoDetails = RepoDetailsLocal()
            val url = repoDetails.url

            if (url.isEmpty()) return RepoDetailsLocal()

            return if (reposLocalRepository.hasRepoDetails(url)) {
                reposLocalRepository.getRepoDetails(url)
            } else {
                localRepoDetails.apply {
                    description = repoDetails.description
                    name = repoDetails.owner?.login
                    repoName = repoDetails.name
                    ownerName = repoDetails.owner?.login
                    starsCount = repoDetails.stargazers_count
                    subscribersCount = repoDetails.subscribers_count
                    this.url = repoDetails.url
                    watchersCount = repoDetails.watchers_count
                }

                reposLocalRepository.insertRepoDetails(localRepoDetails)
                localRepoDetails
            }
        } catch (_: Exception) {
            return RepoDetailsLocal()
        }
    }

    override suspend fun getRepoIssues(owner: String, repo: String): RepoIssuesLocal {
        try {
            val repoIssues = reposApi.getRepoIssues(owner, repo)
            val localRepoIssuesLocal = RepoIssuesLocal()

            if (reposLocalRepository.hasRepoIssues(repoIssues.firstOrNull()?.user?.login ?: "")) {
                return reposLocalRepository.getRepoIssues(repoIssues.firstOrNull()?.user?.login ?: "")
            } else {
                val firstIssue = repoIssues.firstOrNull()

                localRepoIssuesLocal.apply {
                    issueNumber = firstIssue?.number ?: 0
                    laugh = firstIssue?.reactions?.laugh ?: 0
                    heart = firstIssue?.reactions?.heart ?: 0
                    rocket = firstIssue?.reactions?.rocket ?: 0
                    state = firstIssue?.state ?: ""
                    body = firstIssue?.body ?: ""
                    userName = firstIssue?.user?.login ?: ""
                    date = firstIssue?.created_at ?: ""
                    url = firstIssue?.user?.avatar_url ?: ""
                    title = firstIssue?.title ?: ""
                }

                reposLocalRepository.insertRepoIssues(localRepoIssuesLocal)
                return localRepoIssuesLocal
            }
        } catch (_: Exception) {
            return RepoIssuesLocal()
        }
    }

    companion object {
        private const val PAGE_SIZE = 10

    }
}
