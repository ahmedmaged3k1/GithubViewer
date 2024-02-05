package com.example.githubviewer.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.repository.ReposLocalRepository
import com.example.githubviewer.domain.repository.ReposRemoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ReposRemoteRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var reposApi: ReposApi

    @Mock
    private lateinit var reposLocalRepository: ReposLocalRepository

    private lateinit var repository: ReposRemoteRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ReposRemoteRepositoryImpl(reposApi, reposLocalRepository)
    }


    @Test
    fun `getReposList should return List with expected RepoDetailsResponse`() = runTest {
        val expectedRepoDetailsList: List<RepoDetailsResponse> = emptyList()
        `when`(reposApi.getRepos(1)).thenReturn(expectedRepoDetailsList)

        val actualRepoDetailsList = repository.getReposList()

        assertEquals(expectedRepoDetailsList, actualRepoDetailsList)
    }


    @Test
    fun `getRepoIssues should return expected RepoIssuesLocal`() = runTest {
        val owner = "owner"
        val repo = "repo"
        val expectedRepoIssuesLocal =
            RepoIssuesLocal()
        val repoIssuesResponse: List<RepoIssuesResponse> =
            emptyList()
        `when`(reposApi.getRepoIssues(owner, repo)).thenReturn(repoIssuesResponse)
        `when`(reposLocalRepository.hasRepoIssues(expectedRepoIssuesLocal.userName)).thenReturn(true)
        `when`(reposLocalRepository.getRepoIssues(expectedRepoIssuesLocal.userName)).thenReturn(
            expectedRepoIssuesLocal
        )

        val actualRepoIssuesLocal = repository.getRepoIssues(owner, repo)

        assertEquals(expectedRepoIssuesLocal, actualRepoIssuesLocal)
    }
}
