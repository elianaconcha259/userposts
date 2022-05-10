package com.example.userpostapp.data.datasource.remote

import com.example.userpostapp.data.datasource.remote.network.dto.PostDTO
import com.example.userpostapp.data.datasource.remote.network.dto.UserDTO

interface RemoteDataSource {

    suspend fun getUsers(): List<UserDTO>

    suspend fun getPostsByIdUser(idUser: Int): List<PostDTO>
}
