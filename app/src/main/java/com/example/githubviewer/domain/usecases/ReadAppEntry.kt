package com.example.githubviewer.domain.usecases

import com.example.githubviewer.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow


class ReadAppEntry (private val localUserManager : LocalUserManager) {
    suspend operator fun invoke() : Flow<Boolean> {
       return  localUserManager.readAppEntry()
    }
}
