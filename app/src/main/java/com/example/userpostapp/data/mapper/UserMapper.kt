package com.example.userpostapp.data.mapper

import com.example.userpostapp.data.datasource.local.db.entity.UserEntity
import com.example.userpostapp.data.datasource.remote.network.dto.UserDTO
import com.example.userpostapp.domain.model.UserModel

fun UserDTO.toUserModel() = UserModel(
    this.id ?: 0,
    this.name ?: "",
    this.email ?: "",
    this.phone ?: ""
)

fun List<UserDTO>.fromListUserDTOToListUserModel(): List<UserModel> {
    return this.map { it.toUserModel() }
}

fun UserEntity.toUserModel() = UserModel(
    this.id,
    this.name,
    this.email,
    this.phone
)

fun List<UserEntity>.fromListUserEntityToListUserModel(): List<UserModel> {
    return this.map { it.toUserModel() }
}

fun UserDTO.toUserEntity() = UserEntity(
    this.id ?: 0,
    this.name ?: "",
    this.email ?: "",
    this.phone ?: ""
)

fun List<UserDTO>.fromListUserDTOToListUserEntity(): List<UserEntity> {
    return this.map { it.toUserEntity() }
}

