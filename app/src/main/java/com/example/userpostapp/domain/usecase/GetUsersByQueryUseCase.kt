package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.util.common.AsyncResult


interface GetUsersByQueryUseCase {
    suspend operator fun invoke(query:String): AsyncResult<List<UserModel>>
}