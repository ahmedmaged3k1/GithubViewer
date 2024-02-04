package com.example.githubviewer.di

import android.app.Application
import androidx.room.Room
import com.example.githubviewer.data.local.RepoDao
import com.example.githubviewer.data.local.ReposDatabase
import com.example.githubviewer.data.manager.LocalUserManagerImpl
import com.example.githubviewer.data.remote.ReposApi
import com.example.githubviewer.data.repository.ReposLocalRepositoryImpl
import com.example.githubviewer.data.repository.ReposRemoteRepositoryImpl
import com.example.githubviewer.domain.manager.LocalUserManager
import com.example.githubviewer.domain.repository.ReposLocalRepository
import com.example.githubviewer.domain.repository.ReposRemoteRepository
import com.example.githubviewer.domain.usecases.app_entry.AppEntryUseCases
import com.example.githubviewer.domain.usecases.app_entry.ReadAppEntry
import com.example.githubviewer.domain.usecases.app_entry.SaveAppEntry
import com.example.githubviewer.domain.usecases.repos.GetRepoDetails
import com.example.githubviewer.domain.usecases.repos.GetRepoIssues
import com.example.githubviewer.domain.usecases.repos.GetRepos
import com.example.githubviewer.domain.usecases.repos.GetReposList
import com.example.githubviewer.domain.usecases.repos.ReposUseCases
import com.example.githubviewer.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesReposApi(okHttpClient: OkHttpClient): ReposApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)  // Inject OkHttpClient here
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ReposApi::class.java)
    }

    @Provides
    @Singleton
    fun provideReposRepository(
        reposApi: ReposApi,
        reposLocalRepository: ReposLocalRepository
    ):
            ReposRemoteRepository = ReposRemoteRepositoryImpl(reposApi, reposLocalRepository)

    @Provides
    @Singleton
    fun provideReposUseCases(reposRemoteRepository: ReposRemoteRepository): ReposUseCases {
        return ReposUseCases(
            getRepos = GetRepos(reposRemoteRepository),
            getReposDetails = GetRepoDetails(reposRemoteRepository),
            getReposIssues = GetRepoIssues(reposRemoteRepository),
            getReposList = GetReposList(reposRemoteRepository)
        )
    }

    @Provides
    @Singleton
    fun provideReposDatabase(
        application: Application
    ): ReposDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = ReposDatabase::class.java,
            name = "repos_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideRepoDao(
        repoDatabase: ReposDatabase
    ): RepoDao = repoDatabase.repoDao

    @Provides
    @Singleton
    fun provideRepoDetailsLocalDataSource(repoDetailsDao: RepoDao): ReposLocalRepository =
        ReposLocalRepositoryImpl(repoDetailsDao)
}

