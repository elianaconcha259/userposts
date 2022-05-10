package com.example.userpostapp.data.datasource.remote.network.api

import com.example.userpostapp.data.datasource.remote.network.dto.PostDTO
import com.example.userpostapp.data.datasource.remote.network.dto.UserDTO
import com.example.userpostapp.data.datasource.remote.network.url.URL
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET(URL.USERS)
    suspend fun getUsers(): List<UserDTO>

    @GET(URL.POSTS+"/{userId}")
    suspend fun getPostsByUser(@Path("userId") userId: Int?): List<PostDTO>
}
