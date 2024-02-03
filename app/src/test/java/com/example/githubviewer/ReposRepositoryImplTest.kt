package com.example.githubviewer
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.data.repository.ReposRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ReposRepositoryImplTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val reposApi: ReposApi = mockk()
    private val repoDao: RepoDao = mockk()

    private val reposRepository = ReposRepositoryImpl(reposApi, repoDao)





    @Test
    fun `getRepoDetails calls reposApi and updates _allDetailedRepos`() {
        runBlockingTest {
            // Mock reposApi.getRepoDetails
            val owner = "testOwner"
            val repo = "testRepo"
            val repoDetailsResponse = RepoDetailsResponse()

            coEvery { reposApi.getRepoDetails(owner, repo) } returns repoDetailsResponse

            // Call the function
            val result = reposRepository.getRepoDetails(owner, repo)

            // Verify the result
            assertEquals(repoDetailsResponse, result)

            // Verify that _allDetailedRepos is updated
            assertEquals(listOf(repoDetailsResponse), reposRepository.getRepos().first())
        }
    }



    @Test
    fun `getRepoIssues calls reposApi`() {
        runBlockingTest {
            // Mock reposApi.getRepoIssues
            val owner = "testOwner"
            val repo = "testRepo"
            val repoIssuesResponse = listOf(RepoIssuesResponse())

            coEvery { reposApi.getRepoIssues(owner, repo) } returns repoIssuesResponse

            // Call the function
            val result = reposRepository.getRepoIssues(owner, repo)

            // Verify the result
            assertEquals(repoIssuesResponse, result)
        }
    }

}
