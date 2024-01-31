package com.example.githubviewer.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.util.Dimens
import com.example.githubviewer.util.Dimens.extraSmallPadding2
import com.example.githubviewer.util.Dimens.mediumPadding1

@Composable
fun reposList(
    modifier: Modifier=Modifier,
    repos :LazyPagingItems<RepoDetailsResponse>,
    onClick :(RepoDetailsResponse)->Unit
) {

    val handlePagingResult = handlePagingResult(repos)


    if (handlePagingResult) {
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
fun handlePagingResult(
    repos :LazyPagingItems<RepoDetailsResponse>,

) : Boolean {
    val loadState = repos.loadState
    val error = when{
        loadState.refresh is LoadState.Error->  loadState.refresh as LoadState.Error
        loadState.prepend is LoadState.Error->  loadState.prepend as LoadState.Error
        loadState.append is LoadState.Error->  loadState.append as LoadState.Error
        else -> null

    }
    return when {
        loadState.refresh is  LoadState.Loading ->{
            ShimmerEffect()
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
    Column (verticalArrangement = Arrangement.spacedBy(Dimens.mediumPadding1)
    ){
        repeat(10){
            reposCardShimmerEffect(
                modifier = Modifier.padding(horizontal = Dimens.mediumPadding1)

            )

        }


    }
}