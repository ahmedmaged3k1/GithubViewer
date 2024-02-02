package com.example.githubviewer.presentation.issues

import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class IssuesViewModel @Inject constructor(private val reposUseCases: ReposUseCases): ViewModel(){
    var loadedRepoIssues: RepoIssuesResponse? = null

    suspend fun getRepoIssues(owner : String?, repo : String?): RepoIssuesResponse? {

        if (repo != null&&owner!=null) {
            loadedRepoIssues = reposUseCases.getReposIssues(owner, repo)[0]
        }

        return loadedRepoIssues

    }

}