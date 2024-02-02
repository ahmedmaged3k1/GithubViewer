package com.example.githubviewer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githubviewer.data.remote.dto.RepoDetailsResponse
import com.example.githubviewer.domain.model.ReposResponse

@Database(entities = [ReposResponse::class], version = 2)
@TypeConverters(RepoTypeConverter::class)
abstract class ReposDatabase : RoomDatabase() {
    abstract val repoDao: RepoDao
}
