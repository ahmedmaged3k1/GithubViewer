package com.example.githubviewer.data.remote.dto

data class User(
    val login: String = "No Login",
    val id: Long = -1,
    val node_id: String = "No Node ID",
    val avatar_url: String = "No Avatar URL",
    val gravatar_id: String = "No Gravatar ID",
    val url: String = "No URL available",
    val html_url: String = "No HTML URL available",
    val followers_url: String = "No Followers URL available",
    val following_url: String = "No Following URL available",
    val gists_url: String = "No Gists URL available",
    val starred_url: String = "No Starred URL available",
    val subscriptions_url: String = "No Subscriptions URL available",
    val organizations_url: String = "No Organizations URL available",
    val repos_url: String = "No Repositories URL available",
    val events_url: String = "No Events URL available",
    val received_events_url: String = "No Received Events URL available",
    val type: String = "No Type",
    val site_admin: Boolean = false
)
