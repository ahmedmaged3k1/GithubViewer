package com.example.githubviewer.presentation.details

import androidx.lifecycle.ViewModel
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import javax.inject.Inject

class DetailsViewModel @Inject constructor(private val reposUseCases: ReposUseCases): ViewModel() {

    suspend fun getRepoDetails(owner : String, repo : String){
        val repos = reposUseCases.getReposDetails(owner , repo )

    }



}