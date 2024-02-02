package com.example.githubviewer.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.TypeConverters
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.model.ReposResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface RepoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(repo: ReposResponse)

    @Delete
    suspend fun delete(repo: ReposResponse)

    @Query("SELECT * FROM ReposResponse")
    @TypeConverters(RepoTypeConverter::class)
    fun getRepos(): Flow<List<ReposResponse>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @TypeConverters(RepoTypeConverter::class)
    suspend fun insertRepos(repos: List<ReposResponse>)


    @Query("DELETE FROM ReposResponse")
    suspend fun clearRepos()
}