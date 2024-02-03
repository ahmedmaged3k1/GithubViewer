package com.example.githubviewer.presentation.nvgraph

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubviewer.presentation.common.EmptyScreen
import com.example.githubviewer.presentation.details.DetailsScreen
import com.example.githubviewer.presentation.details.DetailsViewModel
import com.example.githubviewer.presentation.home.HomeScreen
import com.example.githubviewer.presentation.home.HomeViewModel
import com.example.githubviewer.presentation.issues.IssueDetails
import com.example.githubviewer.presentation.issues.IssuesViewModel
import kotlinx.coroutines.runBlocking
import kotlin.math.log

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    startDestination: String,
    context: Context // Add the application context to check internet connectivity
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.ReposNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                if (isNetworkAvailable(context)) {
                    Log.d("TAG", "NavGraph:  Home Nav")
                    val viewModel: HomeViewModel = hiltViewModel()
                    val repos = viewModel.repos.collectAsLazyPagingItems()
                    Log.d("TAG", "NavGraph:  ${repos.itemCount}")
                    HomeScreen(repos = repos) { owner, repoName ->
                        navController.navigate("${Route.DetailsScreen.route}/$owner/$repoName")
                    }
                } else {

                    // Handle no internet connectivity
                    // You can show an error message or navigate to an offline screen
                    Log.d("TAG", "NavGraph:  Erorr Home Nav")

                    EmptyScreen(null)
                }
            }
            composable(route = Route.DetailsScreen.route + "/{owner}/{repoName}") { backStackEntry ->
                if (isNetworkAvailable(context)) {
                    val owner = backStackEntry.arguments?.getString("owner")
                    val repoName = backStackEntry.arguments?.getString("repoName")
                    val viewModel: DetailsViewModel = hiltViewModel()
                    runBlocking {
                        viewModel.getRepoDetails(owner, repoName)
                    }

                    val viewModelHome: HomeViewModel = hiltViewModel()
                    val repos = viewModelHome.repos.collectAsLazyPagingItems()
                    viewModel.loadedRepoDetails?.let {
                        DetailsScreen(
                            navController = navController,
                            repoDetailsResponse = it,
                            repos = repos
                        )
                    }
                } else {
                    // Handle no internet connectivity
                    // You can show an error message or navigate to an offline screen
                   EmptyScreen(null)

                }
            }

            composable(route = Route.IssuesScreen.route + "/{owner}/{repoName}") { backStackEntry ->
                if (isNetworkAvailable(context)) {
                    val owner = backStackEntry.arguments?.getString("owner")
                    val repoName = backStackEntry.arguments?.getString("repoName")
                    val viewModel: IssuesViewModel = hiltViewModel()
                    runBlocking {
                        viewModel.getRepoIssues(owner, repoName)
                    }
                    val viewModelHome: HomeViewModel = hiltViewModel()
                    val repos = viewModelHome.repos.collectAsLazyPagingItems()
                    viewModel.loadedRepoIssues?.let { IssueDetails(issue = it, navController, repos) }
                } else {
                    // Handle no internet connectivity
                    // You can show an error message or navigate to an offline screen
                    EmptyScreen(null)

                }
            }
        }
    }
}

// Helper function to check network connectivity

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkInfo = connectivityManager.activeNetworkInfo

    return networkInfo?.isConnected == true
}
