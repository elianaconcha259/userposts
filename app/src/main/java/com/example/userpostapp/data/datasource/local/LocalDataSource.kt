package com.example.userpostapp.data.datasource.local

import com.example.userpostapp.data.datasource.local.db.entity.PostEntity
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity

interface LocalDataSource {

    suspend fun getUsers(): List<UserEntity>

    suspend fun saveUsers(users :List<UserEntity>)

    suspend fun getUserById(id: Int): UserEntity

    suspend fun getUserByQuery(query: String): List<UserEntity>

    suspend fun savePosts(posts :List<PostEntity>)

    suspend fun getPostsByIdUser(idUser: Int): List<PostEntity>

}
