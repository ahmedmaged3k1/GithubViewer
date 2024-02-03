package com.example.githubviewer.presentation.issues

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.presentation.common.CoilImage
import com.example.githubviewer.presentation.common.EmptyScreen
import com.example.githubviewer.presentation.common.NetworkUtils
import com.example.githubviewer.presentation.common.reposCardShimmerEffect
import com.example.githubviewer.presentation.details.components.DetailsTopBar
import com.example.githubviewer.util.Dimens


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IssueDetails(
    issue: RepoIssuesResponse?,
    navController: NavHostController = rememberNavController(),
    repos: LazyPagingItems<RepoDetailsResponse>

    ) {
    val handlePagingResult = handleIssuesResult(repos)

    if(handlePagingResult){
        // In your composable

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


            // Display issue details
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                // Title
                Text(
                    text = issue?.title?:"No Title Available",
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
                    text = "Issue #${issue?.number}"?:"0",
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
                        url = issue?.user?.avatar_url?:"No Pic Available",
                        contentDescription = "User Picture",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = issue?.user?.login?:"No UserName Available",
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
                    text = issue?.body?:"No Body Available",
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
                    text = "State : ${issue?.state}"?:"Closed",
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
                    text = "Created At: ${issue?.created_at}",
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    ReactionRow("Eyes", R.drawable.baseline_remove_red_eye_24, issue?.reactions?.eyes?:0)
                    ReactionRow("Heart", R.drawable.baseline_favorite_24, issue?.reactions?.heart?:0)
                    ReactionRow(
                        "Rocket",
                        R.drawable.baseline_rocket_launch_24,
                        issue?.reactions?.rocket?:0
                    )
                    ReactionRow("Laugh", R.drawable.baseline_tag_faces_24, issue?.reactions?.laugh?:0)
                }
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
@Composable
fun handleIssuesResult(
    repos : LazyPagingItems<RepoDetailsResponse>,

    ) : Boolean {
    val loadState = repos.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> (loadState.refresh as LoadState.Error).error
        loadState.prepend is LoadState.Error -> (loadState.prepend as LoadState.Error).error
        loadState.append is LoadState.Error -> (loadState.append as LoadState.Error).error
        else -> null
    }

    return when {
        loadState.refresh is  LoadState.Loading ->{
            ShimmerEffectPlaceholder()
            false
        }
        error != null ->{
            EmptyScreen ()
            false
        }
        else->{
            true
        }


    }


}
@Composable
private fun ShimmerEffect() {
    val gradientWidth = 200.dp
    val gradientHeight = 20.dp

    // Create a gradient brush with repeating linear gradient
    val shimmerBrush = Brush.horizontalGradient(
        colors = listOf(
            Color.Gray.copy(alpha = 0.2f),
            Color.Gray.copy(alpha = 0.8f),
            Color.Gray.copy(alpha = 0.2f)
        ),
        startX = 0f,
        endX = gradientWidth.value,
        tileMode = TileMode.Repeated
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = shimmerBrush)
            .animateContentSize()
    ) {
        // Content inside the shimmering Box
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            // You can add your UI elements here
            Text(
                text = "Loading...",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
@Composable
fun ShimmerEffectPlaceholder() {

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
            // Title placeholder
            Spacer(
                modifier = Modifier
                    .height(20.dp)
                    .width(200.dp)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Issue Number placeholder
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .width(100.dp)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(8.dp))

            // User Avatar and Login placeholder
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color.Gray.copy(alpha = 0.4f))
                )
                Spacer(modifier = Modifier.width(8.dp))
                Spacer(
                    modifier = Modifier
                        .height(16.dp)
                        .width(150.dp)
                        .background(Color.Gray.copy(alpha = 0.4f))
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Body placeholder
            Spacer(
                modifier = Modifier
                    .height(100.dp)
                    .width(300.dp)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Comments count placeholder
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .width(150.dp)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Created At placeholder
            Spacer(
                modifier = Modifier
                    .height(16.dp)
                    .width(200.dp)
                    .background(Color.Gray.copy(alpha = 0.4f))
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Reactions placeholder
            repeat(4) {
                ReactionRowPlaceholder()
            }
        }
    }
}

@Composable
fun ReactionRowPlaceholder() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(24.dp)
                .background(Color.Gray.copy(alpha = 0.4f))
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .width(100.dp)
                .background(Color.Gray.copy(alpha = 0.4f))
        )
    }
}