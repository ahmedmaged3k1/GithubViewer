package com.example.githubviewer.presentation.nvgraph

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubviewer.presentation.home.HomeScreen
import com.example.githubviewer.presentation.home.HomeViewModel
import kotlin.math.log

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(
                route = Route.HomeScreen.route
            ) {
                Text(text = "Repos Home Screen")
            }
        }

        navigation(
            route = Route.ReposNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val repos = viewModel.repos.collectAsLazyPagingItems()
                //Log.d("Test", "Test Data: ${repos.get(0).toString()}")
                HomeScreen(repos = repos, navigate = {})
            }

            composable(route = Route.IssuesScreen.route) {
                // Specify the content for IssuesScreen
            }

            composable(route = Route.SearchScreen.route) {
                Text(text = "Search Screen")
            }

            composable(route = Route.DetailsScreen.route) {
                Text(text = "Details Screen")
            }
        }
    }
}
