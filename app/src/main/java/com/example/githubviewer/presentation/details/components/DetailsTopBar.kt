package com.example.githubviewer.presentation.details.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.githubviewer.R
import com.example.githubviewer.presentation.nvgraph.Route
import com.example.githubviewer.ui.theme.GithubViewerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsTopBar(
    navController: NavHostController,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body),
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = {
                onBackClick()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {

        },
    )
}