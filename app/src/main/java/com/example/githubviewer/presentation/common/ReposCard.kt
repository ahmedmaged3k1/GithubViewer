package com.example.githubviewer.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.util.Dimens.extraSmallPadding
import com.example.githubviewer.util.Dimens.reposCardSize


@Composable
fun RepoCard(
    modifier: Modifier = Modifier,
    repo: RepoDetailsResponse,
    onclick: () -> Unit
) {


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
                .fillMaxWidth()
        ) {
            Text(
                text = repo.name,
                style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                color = colorResource(id = R.color.body),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(extraSmallPadding))


            // Description Text
            Text(
                text = repo.description,
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

