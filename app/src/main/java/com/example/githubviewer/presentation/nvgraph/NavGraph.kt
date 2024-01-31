package com.example.githubviewer.presentation.nvgraph

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

@Composable
fun NavGraph(
    startDestination : String
){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination ){
        navigation(
            route=Route.AppStartNavigation.route,
            startDestination = Route.HomeScreen.route
        ){
            composable(
               route=Route.HomeScreen.route
            ){
                Text(text = "Repos Home Screen")
            }

        }
        navigation(
            route = Route.ReposNavigation.route,
            startDestination = Route.ReposNavigationScreen.route
        ){
            composable(route=Route.ReposNavigationScreen.route){
                Text(text = "Repos Navigator Screen")
            }
        }
        navigation(
            route = Route.IssuesScreen.route,
            startDestination = Route.ReposNavigationScreen.route
        ){
            composable(route=Route.ReposNavigationScreen.route){
                val viewModel : HomeViewModel = hiltViewModel()
                val repos = viewModel.repos.collectAsLazyPagingItems()
                HomeScreen(repos = repos, navigate ={} )
            }
        }
        navigation(
            route = Route.SearchScreen.route,
            startDestination = Route.ReposNavigationScreen.route
        ){
            composable(route=Route.ReposNavigationScreen.route){
                Text(text = "Search Screen")
            }
        }
        navigation(
            route = Route.DetailsScreen.route,
            startDestination = Route.ReposNavigationScreen.route
        ){
            composable(route=Route.ReposNavigationScreen.route){
                Text(text = "Details  Screen")
            }
        }
    }
}