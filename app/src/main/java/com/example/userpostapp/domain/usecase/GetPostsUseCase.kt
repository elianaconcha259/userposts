package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.util.common.AsyncResult

interface GetPostsUseCase {
    suspend operator fun invoke(userId: Int): AsyncResult<List<PostModel>>
}