package com.example.githubviewer.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,

    ) {
    val painter = rememberAsyncImagePainter(model = url)

    Image(
        modifier = modifier,
        painter = painter,
        contentDescription = contentDescription,

        contentScale = contentScale,
        colorFilter = if (painter.state is AsyncImagePainter.State.Error) {
            ColorFilter.tint(Color.Gray)
        } else {
            null
        }
    )
}


@Composable
fun CoilImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {

    NetworkImage(
        url = url,
        contentDescription = contentDescription,
        contentScale = contentScale,
        modifier = modifier
    )
}

