package com.example.githubviewer.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubviewer.data.remote.dto.ReposResponse

class ReposPagingSource(private val reposApi: ReposApi) : PagingSource<Int, ReposResponse>() {
    override fun getRefreshKey(state: PagingState<Int, ReposResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }

    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReposResponse> {
        return try {

            val reposResponse = reposApi.getRepos()
            val reposList = listOf(reposResponse)
            LoadResult.Page(
                data = reposList,
                prevKey = params.key?.minus(1),
                nextKey = params.key?.plus(1)
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}