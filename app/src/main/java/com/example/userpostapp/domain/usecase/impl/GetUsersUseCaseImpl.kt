package com.example.userpostapp.domain.usecase.impl

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.repository.UserRepository
import com.example.userpostapp.domain.usecase.GetUsersUseCase
import com.example.userpostapp.util.common.AsyncResult
import javax.inject.Inject

class GetUsersUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetUsersUseCase {

    override suspend operator fun invoke(): AsyncResult<List<UserModel>> =
        userRepository.getUsers()

}
