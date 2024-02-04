package com.example.githubviewer.presentation.details


import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.presentation.common.EmptyScreen
import com.example.githubviewer.presentation.details.components.DetailsTopBar
import com.example.githubviewer.presentation.nvgraph.Route


@Composable
fun DetailsScreen(
    repoDetailsResponse: RepoDetailsResponse?,
    navController: NavHostController,
    repos: LazyPagingItems<RepoDetailsResponse>

) {
    if (handleDetailsResult(repos = repos)) {
        // Display repository details
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.input_background))
        ) {
            DetailsTopBar(
                onBackClick = {
                    navController.popBackStack(Route.HomeScreen.route, inclusive = false)
                }
            )

            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
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
                    text = repoDetailsResponse?.name ?: "No name available",
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
                    text = repoDetailsResponse?.description ?: "No description available",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.body)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                val starIcon = painterResource(id = R.drawable.baseline_star_24)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        painter = starIcon,
                        contentDescription = null,
                        tint = colorResource(id = R.color.text_title)
                    )
                    Text(
                        text = repoDetailsResponse?.stargazers_count.toString(),
                        color = colorResource(id = R.color.body)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                val watchersIcon = painterResource(id = R.drawable.baseline_remove_red_eye_24)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = watchersIcon,
                        contentDescription = null,
                        tint = colorResource(id = R.color.text_title)
                    )
                    Text(
                        text = repoDetailsResponse?.watchers_count.toString(),
                        color = colorResource(id = R.color.body)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))

                val subscribersIcon = painterResource(id = R.drawable.baseline_doorbell_24)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        painter = subscribersIcon,
                        contentDescription = null,
                        tint = colorResource(id = R.color.text_title)
                    )
                    Text(
                        text = repoDetailsResponse?.subscribers_count.toString(),
                        color = colorResource(id = R.color.body)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Owner: ${repoDetailsResponse?.owner?.login ?: "Unknown"}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_title)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "Repository: ${repoDetailsResponse?.name ?: "Unknown"}",
                    style = TextStyle(
                        fontSize = 16.sp,
                        color = colorResource(id = R.color.text_title)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                Button(
                    onClick = {
                        navController.navigate("${Route.IssuesScreen.route}/${repoDetailsResponse?.owner?.login}/${repoDetailsResponse?.name}")
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("View Issues")
                }
            }
        }
    }


}

@Composable
fun handleDetailsResult(
    repos: LazyPagingItems<RepoDetailsResponse>,

    ): Boolean {
    val loadState = repos.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> (loadState.refresh as LoadState.Error).error
        loadState.prepend is LoadState.Error -> (loadState.prepend as LoadState.Error).error
        loadState.append is LoadState.Error -> (loadState.append as LoadState.Error).error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            ShimmerEffectDetailsPlaceholder()
            false
        }

        error != null -> {
            EmptyScreen()
            false
        }

        else -> {
            true
        }


    }


}

@Composable
fun ShimmerEffectDetailsPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 90.dp)
            .padding(20.dp)
            .animateContentSize()
    ) {
        // Content inside the shimmering Box
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // GitHub Icon
            Icon(
                painter = painterResource(id = R.drawable.github_icon),
                contentDescription = null,
                tint = Color.Gray.copy(alpha = 0.4f),
                modifier = Modifier
                    .size(72.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Loading...",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray.copy(alpha = 0.4f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Text(
                text = "Loading...",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray.copy(alpha = 0.4f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Stars count with star icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_star_24),
                    contentDescription = null,
                    tint = Color.Gray.copy(alpha = 0.4f)
                )
                Text(
                    text = "0",
                    color = Color.Gray.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Watchers count with eye icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_remove_red_eye_24),
                    contentDescription = null,
                    tint = Color.Gray.copy(alpha = 0.4f)
                )
                Text(
                    text = "0",
                    color = Color.Gray.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Subscribers count with bell icon
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_doorbell_24),
                    contentDescription = null,
                    tint = Color.Gray.copy(alpha = 0.4f)
                )
                Text(
                    text = "0",
                    color = Color.Gray.copy(alpha = 0.4f)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Owner and Repo names placeholders
            Text(
                text = "Owner: Loading...",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray.copy(alpha = 0.4f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Repository: Loading...",
                style = TextStyle(
                    fontSize = 16.sp,
                    color = Color.Gray.copy(alpha = 0.4f)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            // Button to navigate to the issues screen
            Button(
                onClick = {},
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("View Issues")
            }
        }
    }
}

