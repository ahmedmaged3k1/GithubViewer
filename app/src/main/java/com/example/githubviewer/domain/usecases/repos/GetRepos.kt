package com.example.githubviewer.domain.usecases.repos

import android.util.Log
import androidx.paging.PagingData
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposRemoteRepository
import kotlinx.coroutines.flow.Flow


class GetRepos(private val reposRemoteRepository: ReposRemoteRepository) {
    operator fun invoke(): Flow<PagingData<RepoDetailsResponse>> {
        Log.d("TAG", "reposList: entered ")
        return reposRemoteRepository.getRepos()
    }
}