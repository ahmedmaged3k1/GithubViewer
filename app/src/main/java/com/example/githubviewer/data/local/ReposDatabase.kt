package com.example.githubviewer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubviewer.data.local.dto.RepoDetailsLocal
import com.example.githubviewer.data.local.dto.RepoIssuesLocal

@Database(
    entities = [RepoDetailsLocal::class, RepoIssuesLocal::class],
    version = 9,
    exportSchema = false
)
abstract class ReposDatabase : RoomDatabase() {
    abstract val repoDao: RepoDao
}
