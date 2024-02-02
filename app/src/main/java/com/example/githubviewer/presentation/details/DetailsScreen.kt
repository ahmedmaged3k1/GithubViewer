package com.example.githubviewer.presentation.details

import android.content.res.Configuration
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
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.githubviewer.R
import com.example.githubviewer.domain.model.License
import com.example.githubviewer.domain.model.Owner
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.presentation.details.components.DetailsTopBar
import com.example.githubviewer.presentation.nvgraph.Route
import com.example.githubviewer.ui.theme.GithubViewerTheme


@Composable
fun DetailsScreen(
    repoDetailsResponse: RepoDetailsResponse,
    navController: NavHostController,


) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.input_background))
    ) {
        DetailsTopBar(
            navController = navController,
            onShareClick = { /*TODO*/ },
            onBackClick = {    navController.popBackStack(Route.HomeScreen.route, inclusive = false)
            }
        )

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Display repository details
            repoDetailsResponse.let { details ->
                // GitHub Icon
                val githubIcon = painterResource(id = R.drawable.github_icon)
                Image(
                    painter = githubIcon,
                    contentDescription = null,
                    modifier = Modifier
                        .size(72.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )

                Text(
                    text = details.name,
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorResource(id = R.color.body)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Text(
                    text = details.description ?: "No description available",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.body)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))

                // Stars count with star icon
                val starIcon = painterResource(id = R.drawable.baseline_star_24)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(painter = starIcon, contentDescription = null, tint = colorResource(id = R.color.text_title))
                    Text(text = details.stargazers_count.toString(), color = colorResource(id = R.color.body))
                }
                Spacer(modifier = Modifier.height(10.dp))

                // Watchers count with eye icon
                val watchersIcon = painterResource(id = R.drawable.baseline_remove_red_eye_24)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(painter = watchersIcon, contentDescription = null, tint = colorResource(id = R.color.text_title))
                    Text(text = details.watchers_count.toString(), color = colorResource(id = R.color.body))
                }
                Spacer(modifier = Modifier.height(10.dp))

                // License name with corresponding icon
                val licenseIcon = painterResource(id = R.drawable.baseline_house_24)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(painter = licenseIcon, contentDescription = null, tint = colorResource(id = R.color.text_title))
                    Text(
                        text = details.license.name ?: "Unknown License",
                        color = colorResource(id = R.color.body)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                // Subscribers count with bell icon
                val subscribersIcon = painterResource(id = R.drawable.baseline_doorbell_24)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(painter = subscribersIcon, contentDescription = null, tint = colorResource(id = R.color.text_title))
                    Text(text = details.subscribers_count.toString(), color = colorResource(id = R.color.body))
                }
                Spacer(modifier = Modifier.height(10.dp))

                // Owner and Repo names
                Text(
                    text = "Owner: ${details.owner.login ?: "Unknown"}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_title)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "Repository: ${details.name ?: "Unknown"}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_title)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Button to navigate to the issues screen
                Button(
                    onClick = {
                        navController.navigate(Route.IssuesScreen.route)


                    }
                    ,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("View Issues")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DetailsScreenPreview() {
    GithubViewerTheme {
        val fakeRepoDetails = RepoDetailsResponse(
            allow_forking = true,
            archive_url = "https://api.github.com/repos/owner/repo/{archive_format}{/ref}",
            archived = false,
            assignees_url = "https://api.github.com/repos/owner/repo/assignees{/user}",
            blobs_url = "https://api.github.com/repos/owner/repo/git/blobs{/sha}",
            branches_url = "https://api.github.com/repos/owner/repo/branches{/branch}",
            clone_url = "https://github.com/owner/repo.git",
            collaborators_url = "https://api.github.com/repos/owner/repo/collaborators{/collaborator}",
            comments_url = "https://api.github.com/repos/owner/repo/comments{/number}",
            commits_url = "https://api.github.com/repos/owner/repo/commits{/sha}",
            compare_url = "https://api.github.com/repos/owner/repo/compare/{base}...{head}",
            contents_url = "https://api.github.com/repos/owner/repo/contents/{+path}",
            contributors_url = "https://api.github.com/repos/owner/repo/contributors",
            created_at = "2024-02-01T12:34:56Z",
            default_branch = "main",
            deployments_url = "https://api.github.com/repos/owner/repo/deployments",
            description = "This is a fake repository description.",
            disabled = false,
            downloads_url = "https://api.github.com/repos/owner/repo/downloads",
            events_url = "https://api.github.com/repos/owner/repo/events",
            fork = false,
            forks = 123,
            forks_count = 123,
            forks_url = "https://api.github.com/repos/owner/repo/forks",
            full_name = "owner/repo",
            git_commits_url = "https://api.github.com/repos/owner/repo/git/commits{/sha}",
            git_refs_url = "https://api.github.com/repos/owner/repo/git/refs{/sha}",
            git_tags_url = "https://api.github.com/repos/owner/repo/git/tags{/sha}",
            git_url = "git://github.com/owner/repo.git",
            has_discussions = false,
            has_downloads = true,
            has_issues = true,
            has_pages = false,
            has_projects = true,
            has_wiki = true,
            homepage = "https://github.com/owner/repo",
            hooks_url = "https://api.github.com/repos/owner/repo/hooks",
            html_url = "https://github.com/owner/repo",
            id = 123456,
            is_template = false,
            issue_comment_url = "https://api.github.com/repos/owner/repo/issues/comments{/number}",
            issue_events_url = "https://api.github.com/repos/owner/repo/issues/events{/number}",
            issues_url = "https://api.github.com/repos/owner/repo/issues{/number}",
            keys_url = "https://api.github.com/repos/owner/repo/keys{/key_id}",
            labels_url = "https://api.github.com/repos/owner/repo/labels{/name}",
            language = "Kotlin",
            languages_url = "https://api.github.com/repos/owner/repo/languages",
            license = License(
                key = "mit",
                name = "MIT License",
                spdx_id = "MIT",
                url = "https://api.github.com/licenses/mit",
                node_id = "MDc6TGljZW5zZTEz"
            ),
            merges_url = "https://api.github.com/repos/owner/repo/merges",
            milestones_url = "https://api.github.com/repos/owner/repo/milestones{/number}",
            mirror_url = null,
            name = "repo",
            network_count = 123,
            node_id = "MDEwOlJlcG9zaXRvcnkx",
            notifications_url = "https://api.github.com/repos/owner/repo/notifications{?since,all,participating}",
            open_issues = 12,
            open_issues_count = 12,
            owner = Owner(
                login = "owner",
                id = 789,
                node_id = "MDQ6VXNlcjc4OQ==",
                avatar_url = "https://avatars.githubusercontent.com/u/789?v=4",
                gravatar_id = "",
                url = "https://api.github.com/users/owner",
                html_url = "https://github.com/owner",
                followers_url = "https://api.github.com/users/owner/followers",
                following_url = "https://api.github.com/users/owner/following{/other_user}",
                gists_url = "https://api.github.com/users/owner/gists{/gist_id}",
                starred_url = "https://api.github.com/users/owner/starred{/owner}{/repo}",
                subscriptions_url = "https://api.github.com/users/owner/subscriptions",
                organizations_url = "https://api.github.com/users/owner/orgs",
                repos_url = "https://api.github.com/users/owner/repos",
                events_url = "https://api.github.com/users/owner/events{/privacy}",
                received_events_url = "https://api.github.com/users/owner/received_events",
                type = "User",
                site_admin = false
            ),
            private = false,
            pulls_url = "https://api.github.com/repos/owner/repo/pulls{/number}",
            pushed_at = "2024-01-30T16:44:13Z",
            releases_url = "https://api.github.com/repos/owner/repo/releases{/id}",
            size = 7890,
            ssh_url = "git@github.com:owner/repo.git",
            stargazers_count = 456,
            stargazers_url = "https://api.github.com/repos/owner/repo/stargazers",
            statuses_url = "https://api.github.com/repos/owner/repo/statuses/{sha}",
            subscribers_count = 789,
            subscribers_url = "https://api.github.com/repos/owner/repo/subscribers",
            subscription_url = "https://api.github.com/repos/owner/repo/subscription",
            svn_url = "https://github.com/owner/repo",
            tags_url = "https://api.github.com/repos/owner/repo/tags",
            teams_url = "https://api.github.com/repos/owner/repo/teams",
            temp_clone_token = null,
            topics = emptyList(),
            trees_url = "https://api.github.com/repos/owner/repo/git/trees{/sha}",
            updated_at = "2024-01-31T12:34:56Z",
            url = "https://api.github.com/repos/owner/repo",
            visibility = "public",
            watchers = 123,
            watchers_count = 123,
            web_commit_signoff_required = false
        )





    }
}