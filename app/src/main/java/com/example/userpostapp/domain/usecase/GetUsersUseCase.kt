package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.util.common.AsyncResult

interface GetUsersUseCase {
    suspend operator fun invoke(): AsyncResult<List<UserModel>>
}