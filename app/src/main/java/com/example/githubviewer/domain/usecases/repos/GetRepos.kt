package com.example.githubviewer.domain.usecases.repos

import android.util.Log
import androidx.paging.PagingData
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow


class GetRepos(private val reposRepository: ReposRepository) {
    operator fun invoke(): Flow<PagingData<RepoDetailsResponse>> {
        Log.d("TAG", "invoke: repos are ${reposRepository.getRepos()} ")
        return reposRepository.getRepos()
    }
}