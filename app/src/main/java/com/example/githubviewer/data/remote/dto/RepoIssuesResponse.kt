package com.example.githubviewer.data.remote.dto

data class RepoIssuesResponse(
    val url: String = "No URL available",
    val repository_url: String = "No repository URL available",
    val labels_url: String = "No labels URL available",
    val comments_url: String = "No comments URL available",
    val events_url: String = "No events URL available",
    val html_url: String = "No HTML URL available",
    val id: Long = -1,
    val node_id: String = "No node ID available",
    val number: Int = -1,
    val title: String = "No title available",
    val user: User = User(),  // Assuming User has default values defined
    val labels: List<Any> = emptyList(),
    val state: String = "No state available",
    val locked: Boolean = false,
    val assignee: Any? = null,
    val assignees: List<Any> = emptyList(),
    val milestone: Any? = null,
    val comments: Int = 0,
    val created_at: String = "No creation date available",
    val updated_at: String = "No update date available",
    val closed_at: String? = null,
    val author_association: String = "No author association available",
    val active_lock_reason: Any? = null,
    val body: String = "No body available",
    val reactions: Reactions = Reactions(),  // Assuming Reactions has default values defined
    val timeline_url: String = "No timeline URL available",
    val performed_via_github_app: Any? = null,
    val state_reason: Any? = null
)
