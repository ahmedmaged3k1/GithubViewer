package com.example.githubviewer.presentation.home

import androidx.lifecycle.ViewModel
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val reposUseCases: ReposUseCases) : ViewModel() {
    val repos = reposUseCases.getRepos()

    private var loadedReposList: List<RepoDetailsResponse> = emptyList()
    suspend fun getReposList(): List<RepoDetailsResponse> {

        loadedReposList = try {
            val reposList = reposUseCases.getReposList()

            reposList

        } catch (e: Exception) {

            emptyList()
        }


        return loadedReposList
    }


}