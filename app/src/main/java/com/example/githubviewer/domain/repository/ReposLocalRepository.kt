package com.example.githubviewer.domain.repository

import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal

interface ReposLocalRepository {
    suspend fun insertRepoDetails(repoDetails: RepoDetailsLocal)
    suspend fun getRepoDetails(url: String): RepoDetailsLocal
    suspend fun insertRepoIssues(repoIssues: RepoIssuesLocal)

    suspend fun getRepoIssues(userName: String): RepoIssuesLocal
    suspend fun hasRepoDetails(url: String): Boolean
    suspend fun hasRepoIssues(userName: String): Boolean
}