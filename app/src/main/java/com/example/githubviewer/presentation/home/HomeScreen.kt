package com.example.githubviewer.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.presentation.common.SearchBar
import com.example.githubviewer.presentation.common.reposList
import com.example.githubviewer.util.Dimens


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(repos: LazyPagingItems<RepoDetailsResponse>,
               navigate:  (owner: String, repoName: String) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (repos.itemCount > 10) {
                repos.itemSnapshotList.items
                    .slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = "\uD83d\uDFE5") { it.name }

            } else {
                ""
            }

        }

    }
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

        SearchBar(
            modifier = Modifier
                .padding(horizontal = Dimens.mediumPadding1)
                .fillMaxWidth(),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {

            }
        )

        Spacer(modifier = Modifier.height(Dimens.mediumPadding1))



        Spacer(modifier = Modifier.height(Dimens.mediumPadding1))

        reposList(
            modifier = Modifier.padding(horizontal = Dimens.mediumPadding1),
            repos = repos,
            onClick = { repoDetails ->
                navigate(repoDetails.owner.login, repoDetails.name)

            }
        )

    }

}
