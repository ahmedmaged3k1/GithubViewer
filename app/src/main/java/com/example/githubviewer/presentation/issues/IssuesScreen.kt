package com.example.githubviewer.presentation.issues

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.githubviewer.R
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.presentation.common.CoilImage
import com.example.githubviewer.presentation.common.NetworkUtils
import com.example.githubviewer.presentation.details.components.DetailsTopBar


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun IssueDetails(
    issue: RepoIssuesResponse?,
    navController: NavHostController = rememberNavController(),

    ) {
    // In your composable
    val networkUtils = NetworkUtils(context = LocalContext.current)

// Check network status
    val isNetworkAvailable = networkUtils.isNetworkAvailable()

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

        if (!isNetworkAvailable) {
            // Show network error icon
            Image(
                painter = painterResource(id = R.drawable.ic_network_error),
                contentDescription = "Network Error",
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(16.dp)
            )
            Text(
                text = "Network Error",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.text_title)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        } else if (issue == null) {
            // Show data not available message
            Text(
                text = "Data not available",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.text_title)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        } else {
            // Display issue details
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    ReactionRow("Eyes", R.drawable.baseline_remove_red_eye_24, issue.reactions.eyes)
                    ReactionRow("Heart", R.drawable.baseline_favorite_24, issue.reactions.heart)
                    ReactionRow(
                        "Rocket",
                        R.drawable.baseline_rocket_launch_24,
                        issue.reactions.rocket
                    )
                    ReactionRow("Laugh", R.drawable.baseline_tag_faces_24, issue.reactions.laugh)
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

