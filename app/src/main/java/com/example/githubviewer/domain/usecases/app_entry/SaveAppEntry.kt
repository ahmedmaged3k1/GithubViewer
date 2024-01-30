package com.example.githubviewer.domain.usecases.app_entry

import com.example.githubviewer.domain.manager.LocalUserManager

class SaveAppEntry   (private val localUserManager : LocalUserManager) {
    suspend operator fun  invoke (){
        localUserManager.saveAppEntry()
    }
}