package com.example.githubviewer.presentation.details

import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val reposUseCases: ReposUseCases): ViewModel() {

     var loadedRepoDetails: RepoDetailsResponse? = null
    var loadedRepoIssues: RepoIssuesResponse? = null

    suspend fun getRepoDetails(owner : String?, repo : String?): RepoDetailsResponse? {

            if (repo != null&&owner!=null) {
                loadedRepoDetails = reposUseCases.getReposDetails(owner, repo)
            }

        return loadedRepoDetails

    }

    suspend fun getRepoIssues(owner : String?, repo : String?): RepoIssuesResponse? {

        if (repo != null&&owner!=null) {
            loadedRepoIssues = reposUseCases.getReposIssues(owner, repo)[0]

        }

        return loadedRepoIssues

    }



}