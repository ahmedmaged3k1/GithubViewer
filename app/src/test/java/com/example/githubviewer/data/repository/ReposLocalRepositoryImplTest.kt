package com.example.githubviewer.data.repository


import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import com.example.githubviewer.domain.repository.ReposLocalRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ReposLocalRepositoryImplTest {

    @Mock
    private lateinit var repoDao: RepoDao

    private lateinit var repository: ReposLocalRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        repository = ReposLocalRepositoryImpl(repoDao)
    }


    @Test
    fun `getRepoDetails should return expected RepoDetailsLocal`() = runTest {
        val url = "https://example.com"
        val expectedRepoDetails = RepoDetailsLocal(/* Mock your expected data here */)
        `when`(repoDao.getRepoDetails(url)).thenReturn(expectedRepoDetails)

        val actualRepoDetails = repository.getRepoDetails(url)

        assertEquals(expectedRepoDetails, actualRepoDetails)
    }


    @Test
    fun `getRepoIssues should return expected RepoIssuesLocal`() = runTest {

        val userName = "john_doe"
        val expectedRepoIssues = RepoIssuesLocal(/* Mock your expected data here */)
        `when`(repoDao.getRepoIssues(userName)).thenReturn(expectedRepoIssues)

        val actualRepoIssues = repository.getRepoIssues(userName)

        assertEquals(expectedRepoIssues, actualRepoIssues)
    }

    @Test
    fun `hasRepoIssues should return true when issues exist`() = runTest {
        val userName = "john_doe"
        `when`(repoDao.hasRepoIssues(userName)).thenReturn(true)

        val result = repository.hasRepoIssues(userName)

        assertEquals(true, result)
    }

    @Test
    fun `hasRepoIssues should return false when issues do not exist`() = runTest {
        val userName = "john_doe"
        `when`(repoDao.hasRepoIssues(userName)).thenReturn(false)

        val result = repository.hasRepoIssues(userName)

        assertEquals(false, result)
    }

    @Test
    fun `hasRepoDetails should return true when details exist`() = runTest {
        val url = "https://example.com"
        `when`(repoDao.hasRepoDetails(url)).thenReturn(true)

        val result = repository.hasRepoDetails(url)

        assertEquals(true, result)
    }

    @Test
    fun `hasRepoDetails should return false when details do not exist`() = runTest {
        val url = "https://example.com"
        `when`(repoDao.hasRepoDetails(url)).thenReturn(false)

        val result = repository.hasRepoDetails(url)

        assertEquals(false, result)
    }
}
