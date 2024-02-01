package com.example.githubviewer.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse

class ReposPagingSource(private val reposApi: ReposApi) : PagingSource<Int, RepoDetailsResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RepoDetailsResponse> {
        return try {
            val reposList = reposApi.getRepos()

            Log.d("TAG", "load:  ${reposList.toString()}")
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

    override fun getRefreshKey(state: PagingState<Int, RepoDetailsResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
