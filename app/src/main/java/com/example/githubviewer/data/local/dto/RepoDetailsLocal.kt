package com.example.githubviewer.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repo_details")
data class RepoDetailsLocal (
    @PrimaryKey
    var url : String="No Url Available",
    var name: String="No Name Available",
    var description : String="No Description Available",
    var starsCount : Int= 0,
    var watchersCount :Int =0,
    var subscribersCount : Int = 0,
    var ownerName : String ="No Owner Available",
    var repoName:String="No Repo Name Available",
)