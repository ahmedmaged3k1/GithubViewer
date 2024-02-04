package com.example.githubviewer.domain.usecases.repos

import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.domain.repository.ReposRemoteRepository

class GetRepoDetails(private val reposRemoteRepository: ReposRemoteRepository) {
    suspend operator fun invoke(owner: String, repo: String): RepoDetailsLocal {
        return reposRemoteRepository.getRepoDetails(owner, repo)
    }
}