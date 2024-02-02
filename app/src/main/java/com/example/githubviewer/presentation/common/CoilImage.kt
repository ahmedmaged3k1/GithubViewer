package com.example.githubviewer.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.State
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImagePainter
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.githubviewer.R

@OptIn(ExperimentalCoilApi::class)
@Composable
fun NetworkImage(
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    val painter = rememberImagePainter(data = url)

    Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        colorFilter = if (painter.state is AsyncImagePainter.State.Error) {
            ColorFilter.tint(Color.Gray) // Handle error state with a gray tint
        } else {
            null
        }
    )
}
@OptIn(ExperimentalCoilApi::class)
@Composable
fun CoilImage(
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
    modifier: Modifier = Modifier
) {
    // You can use the NetworkImage composable from the previous example
    NetworkImage(
        url = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

