package com.project.leverxtask.di

import android.app.Application
import android.content.Context
import com.project.leverxtask.database.AppDatabase
import com.project.leverxtask.database.NewsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Singleton
    @Provides
    fun getNewsDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.getNewsDao()
    }

    @Singleton
    @Provides
    fun getRoomDbInstance(): AppDatabase {
        return AppDatabase.getAppDatabaseInstance(provideAppContext())
    }

    @Singleton
    @Provides
    fun provideAppContext(): Context {
        return application.applicationContext
    }
}