package com.example.githubviewer.di

import android.app.Application
import com.example.githubviewer.data.manager.LocalUserManagerImpl
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.repository.ReposRepositoryImpl
import com.example.githubviewer.domain.manager.LocalUserManager
import com.example.githubviewer.domain.repository.ReposRepository
import com.example.githubviewer.domain.usecases.app_entry.AppEntryUseCases
import com.example.githubviewer.domain.usecases.app_entry.ReadAppEntry
import com.example.githubviewer.domain.usecases.app_entry.SaveAppEntry
import com.example.githubviewer.domain.usecases.repos.GetRepos
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import com.example.githubviewer.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ):LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun providesReposApi(): ReposApi{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReposApi::class.java)
    }
    @Provides
    @Singleton
    fun provideReposRepository(reposApi: ReposApi) : ReposRepository = ReposRepositoryImpl(reposApi )
    @Provides
    @Singleton
    fun provideReposUseCases(reposRepository: ReposRepository):ReposUseCases{
        return ReposUseCases(
            getRepos = GetRepos(reposRepository)
        )
    }

}