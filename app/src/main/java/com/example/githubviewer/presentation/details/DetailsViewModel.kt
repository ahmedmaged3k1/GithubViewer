package com.example.githubviewer.presentation.details

import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val reposUseCases: ReposUseCases) : ViewModel() {

    var loadedRepoDetails: RepoDetailsResponse = RepoDetailsResponse()
        suspend fun getRepoDetails(owner: String?, repo: String?): RepoDetailsResponse {
            if (repo != null && owner != null) {
                loadedRepoDetails = try {
                    reposUseCases.getReposDetails(owner, repo)
                } catch (e: Exception) {

                    RepoDetailsResponse()
                }
            }

            return loadedRepoDetails
        }



}