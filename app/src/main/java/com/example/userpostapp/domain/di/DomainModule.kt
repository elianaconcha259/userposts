package com.example.userpostapp.domain.di

import com.example.userpostapp.domain.usecase.GetPostsUseCase
import com.example.userpostapp.domain.usecase.GetUserByIdUseCase
import com.example.userpostapp.domain.usecase.GetUsersByQueryUseCase
import com.example.userpostapp.domain.usecase.GetUsersUseCase
import com.example.userpostapp.domain.usecase.impl.GetPostsUseCaseImpl
import com.example.userpostapp.domain.usecase.impl.GetUserByIdUseCaseImpl
import com.example.userpostapp.domain.usecase.impl.GetUsersByQueryUseCaseImpl
import com.example.userpostapp.domain.usecase.impl.GetUsersUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule{

    @Binds
    @Singleton
    abstract fun bindGetUsersUseCase(getUsersUseCaseImpl: GetUsersUseCaseImpl): GetUsersUseCase

    @Binds
    @Singleton
    abstract fun bindGetUsersByQueryUseCase(getUsersByQueryUseCaseImpl: GetUsersByQueryUseCaseImpl): GetUsersByQueryUseCase

    @Binds
    @Singleton
    abstract fun bindGetUserByIDUseCase(getUserByIdUseCase: GetUserByIdUseCaseImpl): GetUserByIdUseCase

    @Binds
    @Singleton
    abstract fun bindPostDUseCase(getPostsUseCase: GetPostsUseCaseImpl): GetPostsUseCase
}