package com.example.githubviewer.presentation.nvgraph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.githubviewer.presentation.details.DetailsScreen
import com.example.githubviewer.presentation.details.DetailsViewModel
import com.example.githubviewer.presentation.home.HomeScreen
import com.example.githubviewer.presentation.home.HomeViewModel
import com.example.githubviewer.presentation.issues.IssueDetails
import kotlinx.coroutines.runBlocking

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

        navigation(
            route = Route.ReposNavigation.route,
            startDestination = Route.HomeScreen.route
        ) {
            composable(route = Route.HomeScreen.route) {
                val viewModel: HomeViewModel = hiltViewModel()
                val repos = viewModel.repos.collectAsLazyPagingItems()
                //Log.d("Test", "Test Data: ${repos.get(0).toString()}")
                HomeScreen(repos = repos) { owner, repoName ->
                    navController.navigate("${Route.DetailsScreen.route}/$owner/$repoName")
                }
        }
            composable(route = Route.DetailsScreen.route + "/{owner}/{repoName}") {backStackEntry ->
                val owner = backStackEntry.arguments?.getString("owner")
                val repoName = backStackEntry.arguments?.getString("repoName")
                val viewModel: DetailsViewModel = hiltViewModel()
                runBlocking {
                    viewModel.getRepoDetails(owner,repoName)
                    viewModel.getRepoIssues(owner,repoName)
                }
                // Pass these values to your DetailsScreen composable
                viewModel.loadedRepoDetails?.let {
                    DetailsScreen(
                        navController = navController,
                        repoDetailsResponse = it,
                        )
                }
            }

            composable(route = Route.IssuesScreen.route) {
                // Specify the content for IssuesScreen
                val viewModel: DetailsViewModel = hiltViewModel()

                viewModel.loadedRepoIssues?.let { it1 -> IssueDetails(it1,navController) }
            }




        }
    }
}

