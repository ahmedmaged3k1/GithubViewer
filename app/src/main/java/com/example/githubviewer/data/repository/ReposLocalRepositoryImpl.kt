package com.example.githubviewer.data.repository

import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.domain.repository.ReposLocalRepository

class ReposLocalRepositoryImpl(private val repoDao: RepoDao) :
    ReposLocalRepository {

    override suspend fun insertRepoDetails(repoDetails: RepoDetailsLocal) {
        repoDao.insertRepoDetails(repoDetails)
    }

    override suspend fun getRepoDetails(url: String): RepoDetailsLocal {
        return repoDao.getRepoDetails(url)
    }

    override suspend fun insertRepoIssues(repoIssues: RepoIssuesLocal) {
        return repoDao.insertRepoIssues(repoIssues)
    }

    override suspend fun getRepoIssues(userName: String): RepoIssuesLocal {
        return repoDao.getRepoIssues(userName)
    }

    override suspend fun hasRepoIssues(userName: String): Boolean {
        return repoDao.hasRepoIssues(userName = userName)
    }

    override suspend fun hasRepoDetails(url: String): Boolean {
        return repoDao.hasRepoDetails(url)
    }

}