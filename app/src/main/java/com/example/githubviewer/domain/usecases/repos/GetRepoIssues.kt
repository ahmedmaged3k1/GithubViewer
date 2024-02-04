package com.example.githubviewer.domain.usecases.repos

import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.domain.repository.ReposRemoteRepository

class GetRepoIssues(private val reposRemoteRepository: ReposRemoteRepository) {
    suspend operator fun invoke(owner: String, repo: String): RepoIssuesLocal {
        return reposRemoteRepository.getRepoIssues(owner, repo)
    }
}