package com.example.githubviewer.domain.usecases.repos

import androidx.paging.PagingData
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow


class GetRepos(private val reposRepository: ReposRepository) {
    operator fun invoke(): Flow<PagingData<RepoDetailsResponse>> {
        return reposRepository.getRepos()
    }
}