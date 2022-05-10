package com.example.userpostapp.data.mapper

import com.example.userpostapp.data.datasource.local.db.entity.PostEntity
import com.example.userpostapp.data.datasource.remote.network.dto.PostDTO
import com.example.userpostapp.domain.model.PostModel

fun PostDTO.toPostModel() = PostModel(
    this.id ?: 0,
    this.userId ?: 0,
    this.title ?: "",
    this.body ?: ""
)

fun List<PostDTO>.fromListPostDTOToListPostModel(): List<PostModel> {
    return this.map { it.toPostModel() }
}

fun PostEntity.toPostModel() = PostModel(
    this.id,
    this.userId,
    this.title,
    this.body
)

fun List<PostEntity>.fromListPostEntityToListPostModel(): List<PostModel> {
    return this.map { it.toPostModel() }
}

fun PostDTO.toPostEntity() = PostEntity(
    this.id ?: 0,
    this.userId ?: 0,
    this.title ?: "",
    this.body ?: ""
)

fun List<PostDTO>.fromListPostDTOToListPostEntity(): List<PostEntity> {
    return this.map { it.toPostEntity() }
}

