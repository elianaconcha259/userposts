package com.example.userpostapp.data.di

import com.example.userpostapp.data.datasource.remote.RemoteDataSourceImpl
import com.example.userpostapp.data.datasource.local.LocalDataSource
import com.example.userpostapp.data.datasource.local.LocalDataSourceImpl
import com.example.userpostapp.data.datasource.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource
}
