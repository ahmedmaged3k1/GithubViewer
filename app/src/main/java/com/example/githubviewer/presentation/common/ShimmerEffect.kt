package com.example.githubviewer.presentation.common

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.githubviewer.R
import com.example.githubviewer.util.Dimens


fun Modifier.shimmerEffect() = composed {
    val transition = rememberInfiniteTransition(label = "")
    val alpha = transition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.9f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000),
            repeatMode = RepeatMode.Reverse
        ), label = ""
    ).value
    background(color = colorResource(id = R.color.shimmer).copy(alpha = alpha))
}

@Composable
fun ReposCardShimmerEffect(
    modifier: Modifier = Modifier
) {

    Row(
        modifier = modifier
            .background(color = colorResource(id = R.color.input_background))

            .padding(vertical = Dimens.extraSmallPadding)

    ) {
        Box(
            modifier = Modifier
                .size(Dimens.reposCardSize)
                .clip(MaterialTheme.shapes.medium)
                .shimmerEffect(),
        )
        Spacer(modifier = Modifier.width(Dimens.extraSmallPadding))
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .height(Dimens.reposCardSize)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .padding(horizontal = Dimens.mediumPadding1)
                    .shimmerEffect(),
            )
            Spacer(modifier = Modifier.height(Dimens.extraSmallPadding))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = Dimens.mediumPadding1)
                    .shimmerEffect(),
            )
        }
    }
}

