package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.util.common.AsyncResult

interface GetUserByIdUseCase {
    suspend operator fun invoke(productId:Int): AsyncResult<UserModel>
}