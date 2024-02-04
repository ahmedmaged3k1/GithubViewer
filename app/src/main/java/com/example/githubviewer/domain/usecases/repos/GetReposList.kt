package com.example.githubviewer.domain.usecases.repos

import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposRepository

class GetReposList(private val reposRepository: ReposRepository) {
    suspend operator fun invoke(): List<RepoDetailsResponse> {

        return reposRepository.getReposList()
    }
}