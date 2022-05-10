package com.example.userpostapp.data.repository

import com.example.userpostapp.data.datasource.local.LocalDataSource
import com.example.userpostapp.data.datasource.remote.RemoteDataSource
import com.example.userpostapp.data.mapper.fromListUserDTOToListUserEntity
import com.example.userpostapp.data.mapper.fromListUserEntityToListUserModel
import com.example.userpostapp.data.mapper.toUserModel
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.repository.UserRepository
import com.example.userpostapp.util.common.AsyncResult
import com.example.userpostapp.util.common.Errors
import java.io.IOException

import java.util.logging.Logger
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : UserRepository {

    private val logger = Logger.getLogger(UserRepositoryImpl::class.simpleName)

    override suspend fun getUserById(userId: Int): AsyncResult<UserModel> {
       return AsyncResult.success(localDataSource.getUserById(userId).toUserModel())
    }

    override suspend fun getUsers(): AsyncResult<List<UserModel>> = try {
        var usersFromLocal = localDataSource.getUsers().fromListUserEntityToListUserModel()
        if (usersFromLocal.isNullOrEmpty()){
            val resultFromApi = remoteDataSource.getUsers().fromListUserDTOToListUserEntity()
            localDataSource.saveUsers(resultFromApi)
            usersFromLocal = localDataSource.getUsers().fromListUserEntityToListUserModel()
        }
        AsyncResult.success(usersFromLocal)
    } catch (e: IOException) {
        logger.warning(e.message)
        AsyncResult.error(Errors.NetworkError)
    } catch (e: Exception) {
        logger.warning(e.message)
        AsyncResult.error(Errors.UnknownError)
    }

    override suspend fun getUsersByQuery(query: String): AsyncResult<List<UserModel>> = try{
        val usersFromLocal = localDataSource.getUserByQuery(query).fromListUserEntityToListUserModel()
        AsyncResult.success(usersFromLocal)
    }catch (e: Exception) {
        logger.warning(e.message)
        AsyncResult.error(Errors.UnknownError)
    }

}
