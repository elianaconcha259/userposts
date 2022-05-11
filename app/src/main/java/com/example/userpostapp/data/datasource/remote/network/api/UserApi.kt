package com.example.userpostapp.data.datasource.remote.network.api

import com.example.userpostapp.data.datasource.remote.network.dto.PostDTO
import com.example.userpostapp.data.datasource.remote.network.dto.UserDTO
import com.example.userpostapp.data.datasource.remote.network.url.URL
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApi {

    @GET(URL.USERS)
    suspend fun getUsers(): List<UserDTO>

    @GET(URL.POSTS)
    suspend fun getPostsByUser(@Query("userId") userId: Int?): List<PostDTO>
}
