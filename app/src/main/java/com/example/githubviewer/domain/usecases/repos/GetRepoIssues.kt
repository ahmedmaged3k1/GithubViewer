package com.example.githubviewer.domain.usecases.repos

import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.repository.ReposRepository

class GetRepoIssues(private val reposRepository: ReposRepository) {
    suspend operator fun invoke(owner: String, repo: String): List<RepoIssuesResponse> {
        return reposRepository.getRepoIssues(owner, repo)
    }
}