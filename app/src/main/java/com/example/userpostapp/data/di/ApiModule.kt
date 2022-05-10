package com.example.userpostapp.data.di

import com.example.userpostapp.data.datasource.remote.network.api.ApiProvider
import com.example.userpostapp.data.datasource.remote.network.api.UserApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideUserApi(provider: ApiProvider): UserApi =
        provider.create(UserApi::class.java)
}
