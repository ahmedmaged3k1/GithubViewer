package com.example.githubviewer.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse

class ReposPagingSource(private val reposApi: ReposApi,
    private val repoDao: RepoDao
) : PagingSource<Int, RepoDetailsResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoDetailsResponse> {
        return try {
            val page = params.key ?: 1 // Default to the first page if key is null
            val reposList = reposApi.getRepos(page)

            LoadResult.Page(
                data = reposList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (reposList.isEmpty()) null else page + 1
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
