package com.example.githubviewer.presentation.issues

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubviewer.data.remote.dto.RepoIssuesResponse
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.jupiter.api.Assertions.*
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner

@ExperimentalCoroutinesApi
@RunWith(RobolectricTestRunner::class)
class IssuesViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var repoUseCases: ReposUseCases

    @InjectMocks
    private lateinit var issuesViewModel: IssuesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun test_GetRepoIssues_EmptyList() = runBlockingTest {
        val owner = "exampleOwner"
        val repoName = "exampleRepo"
        val result = issuesViewModel.getRepoIssues(owner, repoName)
        delay(1000)
        Assert.assertEquals(RepoIssuesResponse(), result)
    }

    @Test
    fun test_GetRepoIssues_Error() = runBlockingTest {
        val owner = "exampleOwner"
        val repoName = "exampleRepo"
        val result = issuesViewModel.getRepoIssues(owner, repoName)
        delay(1000)
        Assert.assertEquals(RepoIssuesResponse(), result)
    }
}