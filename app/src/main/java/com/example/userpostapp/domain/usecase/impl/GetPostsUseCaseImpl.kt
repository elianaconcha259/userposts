package com.example.userpostapp.domain.usecase.impl

import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.domain.repository.PostRepository
import com.example.userpostapp.domain.usecase.GetPostsUseCase
import com.example.userpostapp.util.common.AsyncResult
import javax.inject.Inject

class GetPostsUseCaseImpl @Inject constructor(private val postRepository: PostRepository) :
    GetPostsUseCase {

    override suspend operator fun invoke(userId: Int): AsyncResult<List<PostModel>> =
        postRepository.getPostsByIdUsers(userId)

}
