package com.example.userpostapp.data.datasource.local.db

import com.example.userpostapp.data.datasource.local.LocalDataSourceImpl
import com.example.userpostapp.data.datasource.local.db.dao.UserDao
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class LocalDataSourceImplTest {

    @MockK
    lateinit var userDao: UserDao

    @InjectMockKs
    lateinit var localDataSource: LocalDataSourceImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getProducts() should call getAll()`() =
        runBlockingTest {
            // Given
            val expectedResultProductEntity = mockk<List<UserEntity>>()

            coEvery { userDao.getAll() } returns expectedResultProductEntity

            // When
            localDataSource.getUsers()

            // Then
            coVerify { userDao.getAll() }
        }

    @Test
    fun `saveProducts() should call insert()`() =
        runBlockingTest {
            // Given
            val expectedResult = mockk<List<UserEntity>>()

            coEvery { userDao.insert(expectedResult) } just Runs

            // When
            localDataSource.saveUsers(expectedResult)

            // Then
            coVerify { userDao.insert(expectedResult) }
        }

}
