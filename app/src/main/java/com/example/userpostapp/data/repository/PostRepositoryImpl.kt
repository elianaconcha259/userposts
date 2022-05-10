package com.example.userpostapp.data.repository

import com.example.userpostapp.data.datasource.local.LocalDataSource
import com.example.userpostapp.data.datasource.remote.RemoteDataSource
import com.example.userpostapp.data.mapper.fromListPostDTOToListPostEntity
import com.example.userpostapp.data.mapper.fromListPostEntityToListPostModel
import com.example.userpostapp.data.mapper.fromListUserDTOToListUserEntity
import com.example.userpostapp.data.mapper.fromListUserEntityToListUserModel
import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.domain.repository.PostRepository
import com.example.userpostapp.util.common.AsyncResult
import com.example.userpostapp.util.common.Errors
import java.io.IOException

import java.util.logging.Logger
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : PostRepository {

    private val logger = Logger.getLogger(PostRepositoryImpl::class.simpleName)

    override suspend fun getPostsByIdUsers(userId: Int): AsyncResult<List<PostModel>> = try {
        var postsFromLocal = localDataSource.getPostsByIdUser(userId).fromListPostEntityToListPostModel()
        if (postsFromLocal.isNullOrEmpty()){
            val resultFromApi = remoteDataSource.getPostsByIdUser(userId).fromListPostDTOToListPostEntity()
            localDataSource.savePosts(resultFromApi)
            postsFromLocal = localDataSource.getPostsByIdUser(userId).fromListPostEntityToListPostModel()
        }
        AsyncResult.success(postsFromLocal)
    } catch (e: IOException) {
        logger.warning(e.message)
        AsyncResult.error(Errors.NetworkError)
    } catch (e: Exception) {
        logger.warning(e.message)
        AsyncResult.error(Errors.UnknownError)
    }


}
