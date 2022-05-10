package com.example.userpostapp.data.datasource.local

import com.example.userpostapp.data.datasource.local.db.dao.PostDao
import com.example.userpostapp.data.datasource.local.db.dao.UserDao
import com.example.userpostapp.data.datasource.local.db.entity.PostEntity
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val userDao: UserDao, private val postDao: PostDao) :
    LocalDataSource {

    override suspend fun getUsers(): List<UserEntity> =
        userDao.getAll()

    override suspend fun saveUsers(products: List<UserEntity>) =
        userDao.insert(products)

    override suspend fun getUserById(id: Int): UserEntity =
        userDao.getUserById(id)

    override suspend fun getUserByQuery(query: String): List<UserEntity> =
        userDao.getUserByQuery(query)

    override suspend fun savePosts(posts: List<PostEntity>) {
        postDao.insert(posts)
    }

    override suspend fun getPostsByIdUser(idUser: Int): List<PostEntity> =
        postDao.getPostsByIdUser(idUser)

}
