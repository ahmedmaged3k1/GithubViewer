package com.example.githubviewer.presentation.nvgraph

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubviewer.presentation.common.EmptyScreen
import com.example.githubviewer.presentation.common.NetworkUtils
import com.example.githubviewer.presentation.details.DetailsScreen
import com.example.githubviewer.presentation.details.DetailsViewModel
import com.example.githubviewer.presentation.home.HomeScreen
import com.example.githubviewer.presentation.home.HomeViewModel
import com.example.githubviewer.presentation.issues.IssueDetails
import com.example.githubviewer.presentation.issues.IssuesViewModel
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    startDestination: String,
    context: Context
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.ReposNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                if (NetworkUtils.isNetworkAvailable(context)) {
                    val viewModel: HomeViewModel = hiltViewModel()
                    val repos = viewModel.repos.collectAsLazyPagingItems()
                    HomeScreen(repos = repos) { owner, repoName ->
                        navController.navigate("${Route.DetailsScreen.route}/$owner/$repoName")
                    }
                } else {
                    EmptyScreen(null)
                }
            }
            composable(route = Route.DetailsScreen.route + "/{owner}/{repoName}") { backStackEntry ->
                if (NetworkUtils.isNetworkAvailable(context)) {
                    val owner = backStackEntry.arguments?.getString("owner")
                    val repoName = backStackEntry.arguments?.getString("repoName")
                    val viewModel: DetailsViewModel = hiltViewModel()
                    runBlocking {
                        viewModel.getRepoDetails(owner, repoName)
                    }
                    val viewModelHome: HomeViewModel = hiltViewModel()
                    val repos = viewModelHome.repos.collectAsLazyPagingItems()
                    DetailsScreen(
                        navController = navController,
                        repoDetailsResponse = viewModel.loadedRepoDetails,
                        repos = repos
                    )
                } else {
                    EmptyScreen(null)

                }
            }

            composable(route = Route.IssuesScreen.route + "/{owner}/{repoName}") { backStackEntry ->
                if (NetworkUtils.isNetworkAvailable(context)) {
                    val owner = backStackEntry.arguments?.getString("owner")
                    val repoName = backStackEntry.arguments?.getString("repoName")
                    val viewModel: IssuesViewModel = hiltViewModel()
                    runBlocking {
                        viewModel.getRepoIssues(owner, repoName)
                    }
                    val viewModelHome: HomeViewModel = hiltViewModel()
                    val repos = viewModelHome.repos.collectAsLazyPagingItems()
                    IssueDetails(
                        issue = viewModel.loadedRepoIssues,
                        navController,
                        repos
                    )
                } else {
                    EmptyScreen(null)

                }
            }
        }
    }
}

