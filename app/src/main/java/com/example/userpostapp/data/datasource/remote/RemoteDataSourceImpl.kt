package com.example.userpostapp.data.datasource.remote

import com.example.userpostapp.data.datasource.remote.network.api.UserApi
import com.example.userpostapp.data.datasource.remote.network.dto.PostDTO
import com.example.userpostapp.data.datasource.remote.network.dto.UserDTO
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: UserApi) : RemoteDataSource {

    override suspend fun getUsers(): List<UserDTO> = api.getUsers()
    override suspend fun getPostsByIdUser(idUser: Int): List<PostDTO> = api.getPostsByUser(idUser)
}
