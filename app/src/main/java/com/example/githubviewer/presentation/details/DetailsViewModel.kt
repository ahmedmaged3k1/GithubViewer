package com.example.githubviewer.presentation.details

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val reposUseCases: ReposUseCases): ViewModel() {

     var loadedRepoDetails: RepoDetailsResponse? = null
    var owner : String? =null
    var repoName : String? =null

    suspend fun getRepoDetails(owner : String?, repo : String?): RepoDetailsResponse? {

            if (repo != null&&owner!=null) {
                loadedRepoDetails = reposUseCases.getReposDetails(owner, repo)
            }

        return loadedRepoDetails

    }





}