package com.example.userpostapp.data.datasource.remote.network

import com.example.userpostapp.data.datasource.remote.RemoteDataSourceImpl
import com.example.userpostapp.data.datasource.remote.network.api.UserApi
import com.example.userpostapp.data.datasource.remote.network.dto.UserDTO
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceImplTest {

    @MockK
    lateinit var userApi: UserApi

    @InjectMockKs
    lateinit var remoteDataSource: RemoteDataSourceImpl

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers() should call getUsers()`() =
        runBlockingTest {
            // Given
            val listProductDto = listOf<UserDTO>()

            coEvery { userApi.getUsers() } returns listProductDto

            // When
            remoteDataSource.getUsers()

            // Then
            coVerify { userApi.getUsers() }
        }

}
