package com.example.githubviewer.domain.usecases.repos

import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow

class GetRepoDetails (private val reposRepository: ReposRepository){
    operator fun invoke () : Flow<List<RepoDetailsResponse>> {
        return reposRepository.getAllDetailedRepos()
    }
}