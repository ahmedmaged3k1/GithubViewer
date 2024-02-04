package com.example.githubviewer.data.repository


import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)

class ReposLocalRepositoryImplTest {

    private lateinit var repoDao: RepoDao
    private lateinit var reposLocalRepositoryImpl: ReposLocalRepositoryImpl

    @org.junit.Before
    fun setUp() {
        repoDao = mockk()
        reposLocalRepositoryImpl = ReposLocalRepositoryImpl(repoDao)
    }

    @Test
    fun `insertRepoDetails should call dao method`() = runBlocking<Unit> {
        val repoDetails = RepoDetailsLocal()
        coEvery { repoDao.insertRepoDetails(repoDetails) } returns Unit
        reposLocalRepositoryImpl.insertRepoDetails(repoDetails)
        coEvery { repoDao.insertRepoDetails(repoDetails) }
    }


    @Test
    fun `getRepoDetails should return repo details from dao`() = runBlocking {
        val url = "https://example.com/repo"
        val expectedRepoDetails = RepoDetailsLocal()
        coEvery { repoDao.getRepoDetails(url) } returns expectedRepoDetails
        val result = reposLocalRepositoryImpl.getRepoDetails(url)
        coEvery { repoDao.getRepoDetails(url) }
        assertEquals(expectedRepoDetails, result)
    }

    @Test
    fun `insertRepoIssues should call dao method`() = runBlocking<Unit> {
        val repoIssues = RepoIssuesLocal()
        coEvery { repoDao.insertRepoIssues(repoIssues) } returns Unit
        reposLocalRepositoryImpl.insertRepoIssues(repoIssues)
        coEvery { repoDao.insertRepoIssues(repoIssues) }
    }

    @Test
    fun `getRepoIssues should return repo issues from dao`() = runBlocking {
        val userName = "john_doe"
        val expectedRepoIssues = RepoIssuesLocal()
        coEvery { repoDao.getRepoIssues(userName) } returns expectedRepoIssues
        val result = reposLocalRepositoryImpl.getRepoIssues(userName)
        coEvery { repoDao.getRepoIssues(userName) }
        assertEquals(expectedRepoIssues, result)
    }

    @Test
    fun `hasRepoIssues should return result from dao`() = runBlocking {
        val userName = "john_doe"
        val expectedBooleanResult = true
        coEvery { repoDao.hasRepoIssues(userName) } returns expectedBooleanResult
        val result = reposLocalRepositoryImpl.hasRepoIssues(userName)
        coEvery { repoDao.hasRepoIssues(userName) }
        assertEquals(expectedBooleanResult, result)
    }

    @Test
    fun `hasRepoDetails should return result from dao`() = runBlocking {
        val url = "https://example.com/repo"
        val expectedBooleanResult = true
        coEvery { repoDao.hasRepoDetails(url) } returns expectedBooleanResult
        val result = reposLocalRepositoryImpl.hasRepoDetails(url)
        coEvery { repoDao.hasRepoDetails(url) }
        assertEquals(expectedBooleanResult, result)
    }

}
