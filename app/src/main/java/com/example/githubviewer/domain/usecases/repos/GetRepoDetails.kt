package com.example.githubviewer.domain.usecases.repos

import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposRepository
import kotlinx.coroutines.flow.Flow

class GetRepoDetails (private val reposRepository: ReposRepository){
    suspend operator fun invoke (owner : String, repo : String) : RepoDetailsResponse {
        return reposRepository.getRepoDetails(owner , repo)
    }
}