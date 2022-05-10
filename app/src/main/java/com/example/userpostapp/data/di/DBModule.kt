package com.example.userpostapp.data.di

import com.example.userpostapp.data.datasource.local.db.DBProvider
import com.example.userpostapp.data.datasource.local.db.dao.PostDao
import com.example.userpostapp.data.datasource.local.db.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Provides
    @Singleton
    fun provideUserDao(dbProvider: DBProvider): UserDao = dbProvider.database.userDao()

    @Provides
    @Singleton
    fun providePostDao(dbProvider: DBProvider): PostDao = dbProvider.database.postDao()
}
