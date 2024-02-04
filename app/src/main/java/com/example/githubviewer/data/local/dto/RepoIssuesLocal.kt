package com.example.githubviewer.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_issues")
data class RepoIssuesLocal (
    var title : String? = "No Title Available",
    var issueNumber : Int = 0,
    var url : String? = "No Pic Available",
    @PrimaryKey
    var userName : String = "No UserName Available",
    var body : String? = "No Body Available",
    var state : String? = "No State Available" ,
    var date : String? = "No Date Available",
    var eyes : Int? = 0,
    var heart : Int? = 0,
    var rocket : Int? = 0,
    var laugh : Int? = 0,







)