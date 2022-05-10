package com.example.userpostapp.domain.usecase.impl

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.repository.UserRepository
import com.example.userpostapp.domain.usecase.GetUsersByQueryUseCase
import com.example.userpostapp.util.common.AsyncResult
import javax.inject.Inject

class GetUsersByQueryUseCaseImpl @Inject constructor(private val userRepository: UserRepository) :
    GetUsersByQueryUseCase {

    override suspend fun invoke(query: String): AsyncResult<List<UserModel>> =
        userRepository.getUsersByQuery(query)

}
