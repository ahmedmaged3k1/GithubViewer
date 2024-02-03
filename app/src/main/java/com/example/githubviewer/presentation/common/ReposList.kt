package com.example.githubviewer.presentation.common

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.util.Dimens
import com.example.githubviewer.util.Dimens.extraSmallPadding2
import com.example.githubviewer.util.Dimens.mediumPadding1
import java.net.SocketTimeoutException

@Composable
fun reposList(
    modifier: Modifier=Modifier,
    repos :LazyPagingItems<RepoDetailsResponse>,
    onClick :(RepoDetailsResponse)->Unit
) {

    val handlePagingResult = handlePagingResult(repos)
    Log.d("TAG", "reposList:  Handle $handlePagingResult")
    Log.d("TAG", "reposList details: ${repos.itemSnapshotList.items.toString()} ")
    if (handlePagingResult) {
        Log.d("TAG", "reposList:  Right")

        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(mediumPadding1),
            contentPadding = PaddingValues(all = extraSmallPadding2)
        ) {
            items(
                count = repos.itemCount,
            ) {
                repos[it]?.let { repos ->
                    repoCard(repo = repos, onclick = {onClick(repos)})
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(repos: LazyPagingItems<RepoDetailsResponse>): Boolean {
    val loadState = repos.loadState
    val error = when {
        loadState.refresh is LoadState.Error -> (loadState.refresh as LoadState.Error).error
        loadState.prepend is LoadState.Error -> (loadState.prepend as LoadState.Error).error
        loadState.append is LoadState.Error -> (loadState.append as LoadState.Error).error
        else -> null
    }

    // Log the load state for debugging
    Log.d("TAG", "Load State: ${loadState.refresh}, ${loadState.prepend}, ${loadState.append}")

    return when {
        loadState.refresh is LoadState.Loading -> {
            Log.d("TAG", "Loading State")
            ShimmerEffect()
            false
        }
        error != null -> {
            Log.d("TAG", "Error State: $error")
            if (error is SocketTimeoutException) {
                // Handle slow internet connection
                // You can show a specific message or UI for slow internet

                Toast.makeText(LocalContext.current, "Slow Internet Connection", Toast.LENGTH_SHORT).show()
                EmptyScreen()

            } else {
                // Handle other types of errors
                EmptyScreen()
                Toast.makeText(LocalContext.current, "No Internet Connection", Toast.LENGTH_SHORT).show()

            }
            false
        }
        else -> {
            Log.d("TAG", "Success State")
            true
        }
    }
}


@Composable
private fun ShimmerEffect() {
    Column (verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1)
    ){
        repeat(10){
            reposCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.mediumPadding1)

            )

        }


    }
}