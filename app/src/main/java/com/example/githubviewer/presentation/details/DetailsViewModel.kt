package com.example.githubviewer.presentation.details

import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val reposUseCases: ReposUseCases) : ViewModel() {

    var loadedRepoDetails: RepoDetailsLocal = RepoDetailsLocal()

    suspend fun getRepoDetails(owner: String?, repo: String?): RepoDetailsLocal {
        if (repo != null && owner != null) {
            try {
                loadedRepoDetails =
                    reposUseCases.getReposDetails?.let { it(owner, repo) }!!
                return loadedRepoDetails
            } catch (_: Exception) {
                loadedRepoDetails = RepoDetailsLocal()
                return RepoDetailsLocal()

            }

        }
        loadedRepoDetails = RepoDetailsLocal()
        return RepoDetailsLocal()

    }


}