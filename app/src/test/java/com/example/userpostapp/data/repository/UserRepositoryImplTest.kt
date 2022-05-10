package com.example.userpostapp.data.repository

import com.example.userpostapp.data.datasource.local.LocalDataSource
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity
import com.example.userpostapp.data.datasource.remote.RemoteDataSource
import com.example.userpostapp.data.datasource.remote.network.dto.UserDTO
import com.example.userpostapp.data.mapper.fromListUserDTOToListUserEntity
import com.example.userpostapp.data.mapper.fromListUserEntityToListUserModel
import com.example.userpostapp.domain.model.UserModel
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
class UserRepositoryImplTest {

    @MockK
    lateinit var remoteDataSource: RemoteDataSource

    @MockK
    lateinit var localDataSource: LocalDataSource

    @InjectMockKs
    lateinit var userRepositoryImpl: UserRepositoryImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers() should call remoteDataSource getUsers() and localDataSource saveUsers() and localDataSource getUsers()`() =
        runBlockingTest {
            // Given
            val dbResult = listOf<UserEntity>()
            val modelResult = listOf<UserModel>()

            coEvery { remoteDataSource.getUsers().fromListUserDTOToListUserEntity() } returns dbResult
            coEvery { localDataSource.saveUsers(dbResult) } just Runs
            coEvery { localDataSource.getUsers().fromListUserEntityToListUserModel() } returns modelResult

            // When
            userRepositoryImpl.getUsers()

            // Then
            coVerify { remoteDataSource.getUsers() }
            coVerify {localDataSource.saveUsers(dbResult)}
            coVerify { localDataSource.getUsers() }
        }

    @Test
    fun `getUsers() should return success when is success`() =
        runBlockingTest {
            // Given
            val apiResult = listOf<UserDTO>()
            val resultFromDB = listOf<UserEntity>()
            val expectedResult = AsyncResult.success(resultFromDB)

            coEvery { remoteDataSource.getUsers() } returns apiResult
            coEvery { localDataSource.saveUsers(apiResult.fromListUserDTOToListUserEntity()) } just Runs
            coEvery { localDataSource.getUsers() } returns resultFromDB

            // When
            val result = userRepositoryImpl.getUsers()

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getUsers() should return error with UnknownError when there is error`() =
        runBlockingTest {
            // Given
            val expectedResult: AsyncResult<List<UserDTO>> =
                AsyncResult.error(Errors.UnknownError)

            coEvery { remoteDataSource.getUsers() }.throws(Exception())

            // When
            val result = userRepositoryImpl.getUsers()

            // Then
            assertEquals(expectedResult, result)
        }

    @Test
    fun `getUsers() should return error with NetworkError when there is error`() =
        runBlockingTest {
            // Given
            val expectedResult: AsyncResult<List<String>> = AsyncResult.error(Errors.NetworkError)

            coEvery { remoteDataSource.getUsers() }.throws(IOException())

            // When
            val result = userRepositoryImpl.getUsers()

            // Then
            assertEquals(expectedResult, result)
        }

}
