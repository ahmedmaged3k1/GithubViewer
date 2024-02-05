package com.example.githubviewer.data.repository

import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.repository.ReposLocalRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.anyString
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class ReposRemoteRepositoryImplTest {

    @Mock
    private lateinit var reposApi: ReposApi

    @Mock
    private lateinit var repoDao: RepoDao

    @Mock
    private lateinit var reposLocalRepository: ReposLocalRepository

    private lateinit var reposRemoteRepository: ReposRemoteRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        reposRemoteRepository = ReposRemoteRepositoryImpl(reposApi, reposLocalRepository)
    }

    @Test
    fun `getRepoDetails should return local RepoDetails if available`() = runBlocking {
        val fakeRepoDetailsResponse = RepoDetailsResponse()
        `when`(
            reposApi.getRepoDetails(
                anyString(),
                anyString()
            )
        ).thenReturn(fakeRepoDetailsResponse)

        val fakeLocalRepoDetails = RepoDetailsLocal()
        val url = ""
        `when`(reposLocalRepository.hasRepoDetails(url)).thenReturn(true)
        `when`(reposLocalRepository.getRepoDetails(url)).thenReturn(fakeLocalRepoDetails)

        val result = reposRemoteRepository.getRepoDetails("owner", "repo")

        verify(reposApi, never()).getRepoDetails(anyString(), anyString())

        verify(reposLocalRepository).hasRepoDetails(anyString())
        verify(reposLocalRepository).getRepoDetails(anyString())

        assertEquals(RepoDetailsResponse(), result)
    }

    @Test
    fun `getRepoDetails should return new local RepoDetails if not available locally`() =
        runBlocking {
            val fakeRepoDetailsResponse = RepoDetailsResponse()
            `when`(reposApi.getRepoDetails(anyString(), anyString())).thenReturn(
                fakeRepoDetailsResponse
            )
            `when`(reposLocalRepository.hasRepoDetails(anyString())).thenReturn(false)

            val result = reposRemoteRepository.getRepoDetails("owner", "repo")

            verify(reposApi).getRepoDetails(anyString(), anyString())

            verify(reposLocalRepository).hasRepoDetails(anyString())
            verify(reposLocalRepository).insertRepoDetails(any())

            assertEquals(fakeRepoDetailsResponse, result)
        }


}
