package com.example.githubviewer.presentation.issues

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class IssuesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var reposUseCases: ReposUseCases

    private lateinit var viewModel: IssuesViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        viewModel = IssuesViewModel(reposUseCases)
    }

    @Test
    fun `getRepoIssues should return default RepoIssuesLocal when owner or repo is null`() =
        runTest {
            val owner: String? = null
            val repo: String? = null
            val result = viewModel.getRepoIssues(owner, repo)
            assertEquals(RepoIssuesLocal(), result)
            assertEquals(RepoIssuesLocal(), viewModel.loadedRepoIssues)
        }

    @Test
    fun `getRepoIssues should return default RepoIssuesLocal when ReposUseCases returns null`() =
        runTest {
            val owner = "owner"
            val repo = "repo"
            `when`(reposUseCases.getReposIssues?.let { it(owner, repo) }).thenReturn(
                null
            )
            val result = viewModel.getRepoIssues(owner, repo)
            assertEquals(RepoIssuesLocal(), result)
            assertEquals(RepoIssuesLocal(), viewModel.loadedRepoIssues)
        }


}
