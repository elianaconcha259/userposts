package com.example.userpostapp.domain.usecase.impl

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.repository.UserRepository
import com.example.userpostapp.domain.usecase.GetUserByIdUseCase
import com.example.userpostapp.util.common.AsyncResult
import javax.inject.Inject

class GetUserByIdUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetUserByIdUseCase {

    override suspend fun invoke(userId: Int): AsyncResult<UserModel> =
        userRepository.getUserById(userId)

}
