package com.example.githubviewer.presentation.issues

import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class IssuesViewModel @Inject constructor(private val reposUseCases: ReposUseCases) : ViewModel() {
    var loadedRepoIssues: RepoIssuesLocal = RepoIssuesLocal() // Provide a default object


    suspend fun getRepoIssues(owner: String?, repo: String?): RepoIssuesLocal {
        if (repo != null && owner != null) {
            return try {
                loadedRepoIssues = reposUseCases.getReposIssues?.let { it(owner, repo) }!!
                loadedRepoIssues
            } catch (_: Exception) {
                RepoIssuesLocal()

            }

        }
        return RepoIssuesLocal()

    }


}