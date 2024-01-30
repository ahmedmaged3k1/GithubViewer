package com.example.githubviewer.presentation.common

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.Owner
import com.example.githubviewer.data.remote.dto.ReposResponse
import com.example.githubviewer.ui.theme.GithubViewerTheme
import com.example.githubviewer.util.Dimens.extraSmallPadding
import com.example.githubviewer.util.Dimens.reposCardSize


@Composable
fun repoCard(
    modifier: Modifier = Modifier,
    repo: ReposResponse,
    onclick: () -> Unit
) {
    val context = LocalContext.current

    Row(
        modifier = modifier
            .background(color = colorResource(id = R.color.input_background))
            .clickable { onclick() }
            .padding(vertical = extraSmallPadding)

    ) {
        Image(
            modifier = Modifier
                .size(reposCardSize)
                .clip(MaterialTheme.shapes.medium),
            painter = painterResource(id = R.drawable.github_icon),
            contentDescription = "ReposIcon"
        )
        Spacer(modifier = Modifier.width(extraSmallPadding))
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(reposCardSize)
        ) {
            Text(
                text = repo.name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.body),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(extraSmallPadding))
            Text(
                text = repo.description ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)

@Composable
fun repoCardPreview() {
    val fakeRepo = ReposResponse(
        id = 1,
        node_id = "MDEwOlJlcG9zaXRvcnkx",
        name = "grit",
        full_name = "mojombo/grit",
        private = false,
        owner = Owner(
            login = "mojombo",
            id = 1,
            node_id = "MDQ6VXNlcjE=",
            avatar_url = "https://avatars.githubusercontent.com/u/1?v=4",
            gravatar_id = "",
            url = "https://api.github.com/users/mojombo",
            html_url = "https://github.com/mojombo",
            followers_url = "https://api.github.com/users/mojombo/followers",
            following_url = "https://api.github.com/users/mojombo/following{/other_user}",
            gists_url = "https://api.github.com/users/mojombo/gists{/gist_id}",
            starred_url = "https://api.github.com/users/mojombo/starred{/owner}{/repo}",
            subscriptions_url = "https://api.github.com/users/mojombo/subscriptions",
            organizations_url = "https://api.github.com/users/mojombo/orgs",
            repos_url = "https://api.github.com/users/mojombo/repos",
            events_url = "https://api.github.com/users/mojombo/events{/privacy}",
            received_events_url = "https://api.github.com/users/mojombo/received_events",
            type = "User",
            site_admin = false
        ),
        html_url = "https://github.com/mojombo/grit",
        description = "**Grit is no longer maintained. Check out libgit2/rugged.** Grit gives you object oriented read/write access to Git repositories via Ruby.",
        fork = false,
        url = "https://api.github.com/repos/mojombo/grit",
        forks_url = "https://api.github.com/repos/mojombo/grit/forks",
        keys_url = "https://api.github.com/repos/mojombo/grit/keys{/key_id}",
        collaborators_url = "https://api.github.com/repos/mojombo/grit/collaborators{/collaborator}",
        teams_url = "https://api.github.com/repos/mojombo/grit/teams",
        hooks_url = "https://api.github.com/repos/mojombo/grit/hooks",
        issue_events_url = "https://api.github.com/repos/mojombo/grit/issues/events{/number}",
        events_url = "https://api.github.com/repos/mojombo/grit/events",
        assignees_url = "https://api.github.com/repos/mojombo/grit/assignees{/user}",
        branches_url = "https://api.github.com/repos/mojombo/grit/branches{/branch}",
        tags_url = "https://api.github.com/repos/mojombo/grit/tags",
        blobs_url = "https://api.github.com/repos/mojombo/grit/git/blobs{/sha}",
        git_tags_url = "https://api.github.com/repos/mojombo/grit/git/tags{/sha}",
        git_refs_url = "https://api.github.com/repos/mojombo/grit/git/refs{/sha}",
        trees_url = "https://api.github.com/repos/mojombo/grit/git/trees{/sha}",
        statuses_url = "https://api.github.com/repos/mojombo/grit/statuses/{sha}",
        languages_url = "https://api.github.com/repos/mojombo/grit/languages",
        stargazers_url = "https://api.github.com/repos/mojombo/grit/stargazers",
        contributors_url = "https://api.github.com/repos/mojombo/grit/contributors",
        subscribers_url = "https://api.github.com/repos/mojombo/grit/subscribers",
        subscription_url = "https://api.github.com/repos/mojombo/grit/subscription",
        commits_url = "https://api.github.com/repos/mojombo/grit/commits{/sha}",
        git_commits_url = "https://api.github.com/repos/mojombo/grit/git/commits{/sha}",
        comments_url = "https://api.github.com/repos/mojombo/grit/comments{/number}",
        issue_comment_url = "https://api.github.com/repos/mojombo/grit/issues/comments{/number}",
        contents_url = "https://api.github.com/repos/mojombo/grit/contents/{+path}",
        compare_url = "https://api.github.com/repos/mojombo/grit/compare/{base}...{head}",
        merges_url = "https://api.github.com/repos/mojombo/grit/merges",
        archive_url = "https://api.github.com/repos/mojombo/grit/{archive_format}{/ref}",
        downloads_url = "https://api.github.com/repos/mojombo/grit/downloads",
        issues_url = "https://api.github.com/repos/mojombo/grit/issues{/number}",
        pulls_url = "https://api.github.com/repos/mojombo/grit/pulls{/number}",
        milestones_url = "https://api.github.com/repos/mojombo/grit/milestones{/number}",
        notifications_url = "https://api.github.com/repos/mojombo/grit/notifications{?since,all,participating}",
        labels_url = "https://api.github.com/repos/mojombo/grit/labels{/name}",
        releases_url = "https://api.github.com/repos/mojombo/grit/releases{/id}",
        deployments_url = "https://api.github.com/repos/mojombo/grit/deployments"
    )
    GithubViewerTheme {
        repoCard(repo = fakeRepo) {

        }
    }
}