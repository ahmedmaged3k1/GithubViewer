package com.example.githubviewer.presentation.nvgraph

sealed class Route(val route : String ){

    object HomeScreen : Route(route = "homeScreen")
    object SearchScreen:Route(route = "SearchScreen")
    object DetailsScreen : Route(route = "detailsScreen")
    object IssuesScreen : Route(route="issuesScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object ReposNavigation : Route(route = "reposNavigation")
    object ReposNavigationScreen : Route(route = "reposNavigation")
}
