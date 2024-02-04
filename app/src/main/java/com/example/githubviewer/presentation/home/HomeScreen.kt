package com.example.githubviewer.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.presentation.common.ReposList
import com.example.githubviewer.util.Dimens


@Composable
fun HomeScreen(repos: LazyPagingItems<RepoDetailsResponse>,
               navigate:  (owner: String, repoName: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.mediumPadding1)
            .statusBarsPadding()
    ) {
        Image(
            painter = painterResource(id = R.drawable.github_icon),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(30.dp)
                .padding(horizontal = Dimens.mediumPadding1)
        )
        Spacer(modifier = Modifier.height(Dimens.mediumPadding1))

        ReposList(
            modifier = Modifier.padding(horizontal = Dimens.mediumPadding1),
            repos = repos,
            onClick = { repoDetails ->
                navigate(repoDetails.owner.login, repoDetails.name)

            }
        )

    }

}
