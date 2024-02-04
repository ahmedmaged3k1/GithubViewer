package com.example.githubviewer.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal

@Dao
interface RepoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepoDetails(repoDetails: RepoDetailsLocal)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRepoIssues(repoIssues: RepoIssuesLocal)

    @Query("SELECT * FROM repo_issues WHERE userName = :userName")

    suspend fun getRepoIssues(userName: String): RepoIssuesLocal

    @Query("SELECT * FROM repo_details WHERE url = :url AND url IS NOT NULL AND url != 'No URL available' LIMIT 1")
    suspend fun getRepoDetails(url: String): RepoDetailsLocal

    @Query("SELECT EXISTS (SELECT 1 FROM repo_issues WHERE userName = :userName LIMIT 1)")
    suspend fun hasRepoIssues(userName: String): Boolean

    @Query("SELECT EXISTS (SELECT 1 FROM repo_details WHERE url = :url LIMIT 1)")
    suspend fun hasRepoDetails(url: String): Boolean
}