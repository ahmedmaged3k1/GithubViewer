package com.example.githubviewer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.domain.usecases.AppEntryUseCases
import com.example.githubviewer.presentation.nvgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val appEntryUseCases: AppEntryUseCases) :
    ViewModel() {
    private val _splashCondition = mutableStateOf(true)
    var splashCondition by mutableStateOf(true)
        private set

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        runBlocking {
            appEntryUseCases.readAppEntry().onEach {
                startDestination = Route.ReposNavigation.route
                delay(300)
                splashCondition = false
            }.launchIn(viewModelScope)
        }

    }
}