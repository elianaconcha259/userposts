package com.example.userpostapp.data.repository

import com.example.userpostapp.data.datasource.local.LocalDataSource
import com.example.userpostapp.data.datasource.local.db.entity.PostEntity
import com.example.userpostapp.data.datasource.remote.RemoteDataSource
import com.example.userpostapp.data.datasource.remote.network.dto.PostDTO
import com.example.userpostapp.data.mapper.fromListPostDTOToListPostEntity
import com.example.userpostapp.data.mapper.fromListPostEntityToListPostModel
import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.util.common.AsyncResult
import com.example.userpostapp.util.common.Errors
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import java.io.IOException
import java.lang.Exception

@ExperimentalCoroutinesApi
class PostRepositoryImplTest {

    @MockK
    lateinit var remoteDataSource: RemoteDataSource

    @MockK
    lateinit var localDataSource: LocalDataSource

    @InjectMockKs
    lateinit var postRepositoryImpl: PostRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getPostsByIdUsers() should call localDataSource getPostsByIdUser() remoteDataSource getPostsByIdUser() and localDataSource savePosts() and localDataSource getPostsByIdUsers()`() =
        runBlockingTest {
            // Given
            val dbResult = listOf<PostEntity>()
            val modelResult = listOf<PostModel>()

            coEvery { localDataSource.getPostsByIdUser(1).fromListPostEntityToListPostModel() } returns modelResult
            coEvery { remoteDataSource.getPostsByIdUser(1).fromListPostDTOToListPostEntity() } returns dbResult
            coEvery { localDataSource.savePosts(dbResult) } just Runs
            coEvery { localDataSource.getPostsByIdUser(1).fromListPostEntityToListPostModel() } returns modelResult

            // When
            postRepositoryImpl.getPostsByIdUsers(1)

            // Then
            coVerify { remoteDataSource.getPostsByIdUser(1) }
            coVerify { remoteDataSource.getPostsByIdUser(1) }
            coVerify {localDataSource.savePosts(dbResult)}
            coVerify { localDataSource.getPostsByIdUser(1) }
        }

    @Test
    fun `getPostsByIdUsers() should return success when is success`() =
        runBlockingTest {
            // Given
            val apiResult = listOf<PostDTO>()
            val resultFromDB = listOf<PostEntity>()
            val expectedResult = AsyncResult.success(resultFromDB)

            coEvery { localDataSource.getPostsByIdUser(1) } returns resultFromDB
            coEvery { remoteDataSource.getPostsByIdUser(1) } returns apiResult
            coEvery { localDataSource.savePosts(apiResult.fromListPostDTOToListPostEntity()) } just Runs
            coEvery { localDataSource.getPostsByIdUser(1) } returns resultFromDB

            // When
            val result = postRepositoryImpl.getPostsByIdUsers(1)

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getPostsByIdUsers() should return error with UnknownError when there is error`() =
        runBlockingTest {
            // Given
            val expectedResult: AsyncResult<List<PostDTO>> =
                AsyncResult.error(Errors.UnknownError)

            coEvery { remoteDataSource.getPostsByIdUser(1) }.throws(Exception())

            // When
            val result = postRepositoryImpl.getPostsByIdUsers(1)

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getPostsByIdUsers() should return error with NetworkError when there is error`() =
        runBlockingTest {
            // Given
            val expectedResult: AsyncResult<List<String>> = AsyncResult.error(Errors.NetworkError)

            coEvery { remoteDataSource.getPostsByIdUser(1) }.throws(IOException())

            // When
            val result = postRepositoryImpl.getPostsByIdUsers(1)

            // Then
            assertEquals(expectedResult, result)
        }

}
