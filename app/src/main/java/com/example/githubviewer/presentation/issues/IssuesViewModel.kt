package com.example.githubviewer.presentation.issues

import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(private val reposUseCases: ReposUseCases) : ViewModel() {
    var loadedRepoIssues: RepoIssuesResponse = RepoIssuesResponse() // Provide a default object

    suspend fun getRepoIssues(owner: String?, repo: String?): RepoIssuesResponse {
        if (repo != null && owner != null) {
            try {
                val issuesList = reposUseCases.getReposIssues(owner, repo)
                if (issuesList.isNotEmpty()) {
                    loadedRepoIssues = issuesList[0]
                }
            } catch (e: Exception) {
                loadedRepoIssues = RepoIssuesResponse()
            }
        }

        return loadedRepoIssues
    }


}