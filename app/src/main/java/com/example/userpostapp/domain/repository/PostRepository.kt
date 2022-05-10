package com.example.userpostapp.domain.repository

import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.util.common.AsyncResult

interface PostRepository {

    suspend fun getPostsByIdUsers(userId: Int): AsyncResult<List<PostModel>>

}