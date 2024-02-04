package com.example.githubviewer.domain.usecases.repos

import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposRemoteRepository

class GetReposList(private val reposRemoteRepository: ReposRemoteRepository) {
    suspend operator fun invoke(): List<RepoDetailsResponse> {

        return reposRemoteRepository.getReposList()
    }
}