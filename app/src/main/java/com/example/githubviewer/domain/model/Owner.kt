package com.example.githubviewer.domain.model

import androidx.room.Entity

@Entity

data class Owner(
    val avatar_url: String = "No avatar URL available",
    val events_url: String = "No events URL available",
    val followers_url: String = "No followers URL available",
    val following_url: String = "No following URL available",
    val gists_url: String = "No gists URL available",
    val gravatar_id: String = "No gravatar ID available",
    val html_url: String = "No HTML URL available",
    val id: Int = -1,
    val login: String = "No login available",
    val node_id: String = "No node ID available",
    val organizations_url: String = "No organizations URL available",
    val received_events_url: String = "No received events URL available",
    val repos_url: String = "No repos URL available",
    val site_admin: Boolean = false,
    val starred_url: String = "No starred URL available",
    val subscriptions_url: String = "No subscriptions URL available",
    val type: String = "No type available",
    val url: String = "No URL available"
)
