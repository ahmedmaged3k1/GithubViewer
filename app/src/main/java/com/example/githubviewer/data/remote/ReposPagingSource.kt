package com.example.githubviewer.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubviewer.data.remote.dto.ReposResponse

class ReposPagingSource(private val reposApi: ReposApi) : PagingSource<Int, ReposResponse> (){
    override fun getRefreshKey(state: PagingState<Int, ReposResponse>): Int? {


        TODO("Not yet implemented")





    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReposResponse> {
        return try{
            val reposResponse = reposApi.getRepos()
            val owner = reposResponse.owner
            
        }
        catch (e:Exception){
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

}