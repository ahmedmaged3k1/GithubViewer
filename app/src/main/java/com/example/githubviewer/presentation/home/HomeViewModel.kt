package com.example.githubviewer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.githubviewer.domain.usecases.app_entry.AppEntryUseCases
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val reposUseCases: ReposUseCases):ViewModel() {
    val repos = reposUseCases.getRepos().cachedIn(viewModelScope)

}