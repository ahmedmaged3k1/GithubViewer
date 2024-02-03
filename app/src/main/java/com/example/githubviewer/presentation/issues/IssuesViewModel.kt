package com.example.githubviewer.presentation.issues

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class IssuesViewModel @Inject constructor(private val reposUseCases: ReposUseCases): ViewModel(){
    var loadedRepoIssues: RepoIssuesResponse = RepoIssuesResponse() // Provide a default object

    suspend fun getRepoIssues(owner: String?, repo: String?): RepoIssuesResponse {
        if (repo != null && owner != null) {
            try {
                val issuesList = reposUseCases.getReposIssues(owner, repo)
                if (issuesList.isNotEmpty()) {
                    loadedRepoIssues = issuesList[0]
                } else {
                    // Handle the case where the issues list is empty
                    Log.w("IssuesViewModel", "Empty issues list for repo: $owner/$repo")
                }
            } catch (e: Exception) {
                // Handle network issues or any exception
                Log.e("IssuesViewModel", "Error fetching repo issues: ${e.message}")
                // Return a default or empty RepoIssuesResponse in case of an error
                loadedRepoIssues = RepoIssuesResponse()
            }
        }

        return loadedRepoIssues
    }


}