package com.example.userpostapp.data.di

import com.example.userpostapp.data.repository.PostRepositoryImpl
import com.example.userpostapp.data.repository.UserRepositoryImpl
import com.example.userpostapp.domain.repository.PostRepository
import com.example.userpostapp.domain.repository.UserRepository
import dagger.Binds
import javax.inject.Singleton
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    @Singleton
    abstract fun bindPostRepository(postRepositoryImpl: PostRepositoryImpl): PostRepository
}
