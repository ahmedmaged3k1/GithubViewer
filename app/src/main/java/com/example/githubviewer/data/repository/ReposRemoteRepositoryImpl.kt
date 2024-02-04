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
        Log.d("TAG", "getReposList: imp ")

        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                ReposPagingSource(
                    reposApi = reposApi,
                )
            }
        ).flow

    }

    override suspend fun getReposList(): List<RepoDetailsResponse> {
        return reposApi.getRepos(1)

    }

    override suspend fun getRepoDetails(owner: String, repo: String): RepoDetailsLocal {

        val repoDetails = reposApi.getRepoDetails(owner, repo)
        if (repoDetails == RepoDetailsResponse()) return RepoDetailsLocal()
        val localRepoDetails = RepoDetailsLocal()
        val url = repoDetails.url
        if (url.isEmpty()) return RepoDetailsLocal()
        return if (reposLocalRepository.hasRepoDetails(url)) {
            reposLocalRepository.getRepoDetails(url)
        } else {
            localRepoDetails.description = repoDetails.description
            localRepoDetails.name = repoDetails.owner?.login
            localRepoDetails.repoName = repoDetails.name
            localRepoDetails.ownerName = repoDetails.owner?.login
            localRepoDetails.starsCount = repoDetails.stargazers_count
            localRepoDetails.subscribersCount = repoDetails.subscribers_count
            localRepoDetails.url = repoDetails.url
            localRepoDetails.watchersCount = repoDetails.watchers_count
            reposLocalRepository.insertRepoDetails(localRepoDetails)
            localRepoDetails
        }
    }

    override suspend fun getRepoIssues(owner: String, repo: String): RepoIssuesLocal {

        val repoIssues = reposApi.getRepoIssues(owner, repo)
        Log.d("TAG", "getRepoIssues: ${reposApi.getRepoIssues(owner, repo).toString()} ")

        val localRepoIssuesLocal = RepoIssuesLocal()

        if (reposLocalRepository.hasRepoIssues(reposApi.getRepoIssues(owner,repo)[0].user.login)) {
            return reposLocalRepository.getRepoIssues(reposApi.getRepoIssues(owner,repo)[0].user.login)

        } else {
            localRepoIssuesLocal.issueNumber = repoIssues[0].number
            localRepoIssuesLocal.laugh = repoIssues[0].reactions.laugh
            localRepoIssuesLocal.heart = repoIssues[0].reactions.heart
            localRepoIssuesLocal.rocket = repoIssues[0].reactions.rocket
            localRepoIssuesLocal.state = repoIssues[0].state
            localRepoIssuesLocal.body = repoIssues[0].body
            localRepoIssuesLocal.userName = repoIssues[0].user.login
            localRepoIssuesLocal.date = repoIssues[0].created_at
            localRepoIssuesLocal.url = repoIssues[0].user.avatar_url
            localRepoIssuesLocal.title = repoIssues[0].title
            reposLocalRepository.insertRepoIssues(localRepoIssuesLocal)

            return localRepoIssuesLocal
        }

    }


    companion object {
        private const val PAGE_SIZE = 10
    }
}