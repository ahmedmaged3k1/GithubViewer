package com.example.githubviewer.domain.usecases.repos

data class ReposUseCases (
    val getRepos: GetRepos,
    val getReposDetails : GetRepoDetails,
    val getReposIssues: GetRepoIssues,
    val getReposList: GetReposList

)