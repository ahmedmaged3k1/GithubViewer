package com.example.githubviewer.presentation.nvgraph

sealed class Route(val route: String) {

    object HomeScreen : Route(route = "homeScreen")

    object DetailsScreen : Route(route = "detailsScreen")
    object IssuesScreen : Route(route = "issuesScreen")
    object AppStartNavigation : Route(route = "appStartNavigation")
    object ReposNavigation : Route(route = "reposNavigation")

}
