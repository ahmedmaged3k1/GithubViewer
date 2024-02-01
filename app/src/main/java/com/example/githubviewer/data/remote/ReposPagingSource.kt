package com.example.githubviewer.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.ReposResponse

class ReposPagingSource(private val reposApi: ReposApi) : PagingSource<Int, RepoDetailsResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoDetailsResponse> {
        return try {
            val reposList = reposApi.getRepos()

            // Load details for each repository
            val detailedReposList = reposList.mapNotNull { repo ->
                try {
                    reposApi.getRepoDetails(repo.owner.toString(), repo.name)
                } catch (e: Exception) {
                    // Handle errors for individual repository details if needed
                    e.printStackTrace()
                    null
                }
            }

            LoadResult.Page(
                data = detailedReposList,
                prevKey = params.key?.minus(1),
                nextKey = params.key?.plus(1)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RepoDetailsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
