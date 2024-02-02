package com.example.githubviewer.presentation.issues

import android.content.res.Configuration
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.Reactions
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.data.remote.dto.User
import com.example.githubviewer.presentation.common.CoilImage
import com.example.githubviewer.presentation.details.components.DetailsTopBar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IssueDetails(
    issue: RepoIssuesResponse,
    navController: NavHostController = rememberNavController()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.input_background))
    ) {

        DetailsTopBar(
            navController = navController,
            onShareClick = { /*TODO*/ },
            onBackClick = {
                navController.popBackStack()
            }
        )
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = issue.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.text_title)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Issue Number
            Text(
                text = "Issue #${issue.number}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.body)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))


            // User Avatar and Login
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                CoilImage(
                    url = issue.user.avatar_url,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = issue.user.login,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.body)
                    )
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            // Body
            Text(
                text = issue.body,
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.body)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Comments count
            Text(
                text = "Comments: ${issue.comments}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.body)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Created At
            Text(
                text = "Created At: ${issue.created_at}",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.text_title)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            // Reactions
            Column(
                modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
            ) {
                ReactionRow("Eyes", R.drawable.baseline_remove_red_eye_24, issue.reactions.eyes)
                ReactionRow("Heart", R.drawable.baseline_favorite_24, issue.reactions.heart)
                ReactionRow("Rocket", R.drawable.baseline_rocket_launch_24, issue.reactions.rocket)
                ReactionRow("Laugh", R.drawable.baseline_tag_faces_24, issue.reactions.laugh)
            }
        }
    }
}

@Composable
fun ReactionRow(label: String, iconResourceId: Int, count: Int) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val icon = painterResource(iconResourceId)
        Icon(painter = icon, contentDescription = label)
        Text(
            text = "$label: $count",
            style = TextStyle(fontSize = 16.sp, color = colorResource(id = R.color.text_title))
        )

    }
    Spacer(modifier = Modifier.height(10.dp))

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun IssueDetailsPreview() {
    val sampleIssue = RepoIssuesResponse(
        active_lock_reason = Any(),
        assignee = Any(),
        assignees = emptyList(),
        author_association = "NONE",
        body = "This is a sample issue body.",
        closed_at = "Any()",
        comments = 3,
        comments_url = "https://api.github.com/repos/octocat/Hello-World/issues/2974/comments",
        created_at = "2024-02-01T12:06:50Z",
        events_url = "https://api.github.com/repos/octocat/Hello-World/issues/2974/events",
        html_url = "https://github.com/octocat/Hello-World/issues/2974",
        id = 2974,
        labels = emptyList(),
        labels_url = "https://api.github.com/repos/octocat/Hello-World/issues/2974/labels{/name}",
        locked = false,
        milestone = Any(),
        node_id = "I_kwDOABPHjc596AYS",
        number = 2974,
        performed_via_github_app = Any(),
        reactions = Reactions(
            url = "https://api.github.com/repos/octocat/Hello-World/issues/2974/reactions",
            total_count = 1,
            positive1 = 1,
            negative1 = 0,
            laugh = 0,
            hooray = 0,
            confused = 0,
            heart = 0,
            rocket = 0,
            eyes = 0
        ),
        repository_url = "https://api.github.com/repos/octocat/Hello-World",
        state = "open",
        state_reason = Any(),
        timeline_url = "https://api.github.com/repos/octocat/Hello-World/issues/2974/timeline",
        title = "API Testing",
        updated_at = "2024-02-01T12:06:50Z",
        url = "https://api.github.com/repos/octocat/Hello-World/issues/2974",
        user = User(
            login = "eznavy",
            id = 103522073,
            node_id = "U_kgDOBiufGQ",
            avatar_url = "https://avatars.githubusercontent.com/u/103522073?v=4",
            gravatar_id = "",
            url = "https://api.github.com/users/eznavy",
            html_url = "https://github.com/eznavy",
            followers_url = "https://api.github.com/users/eznavy/followers",
            following_url = "https://api.github.com/users/eznavy/following{/other_user}",
            gists_url = "https://api.github.com/users/eznavy/gists{/gist_id}",
            starred_url = "https://api.github.com/users/eznavy/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/eznavy/subscriptions",
            organizations_url = "https://api.github.com/users/eznavy/orgs",
            repos_url = "https://api.github.com/users/eznavy/repos",
            events_url = "https://api.github.com/users/eznavy/events{/privacy}",
            received_events_url = "https://api.github.com/users/eznavy/received_events",
            type = "User",
            site_admin = false
        )
    )

    IssueDetails(issue = sampleIssue)
}


