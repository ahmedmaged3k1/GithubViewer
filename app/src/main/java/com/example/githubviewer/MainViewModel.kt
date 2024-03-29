package com.example.githubviewer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubviewer.domain.usecases.app_entry.AppEntryUseCases
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
    var splashCondition by mutableStateOf(true)
        private set

    private var startDestination by mutableStateOf(Route.AppStartNavigation.route)

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