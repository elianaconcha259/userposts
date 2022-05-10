package com.example.userpostapp.domain.repository

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.util.common.AsyncResult

interface UserRepository {

    suspend fun getUsers(): AsyncResult<List<UserModel>>

    suspend fun getUsersByQuery(query: String): AsyncResult<List<UserModel>>

    suspend fun getUserById(userId: Int): AsyncResult<UserModel>

}