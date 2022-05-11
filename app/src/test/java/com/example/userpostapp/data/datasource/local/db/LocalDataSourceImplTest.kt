package com.example.userpostapp.data.datasource.local.db

import com.example.userpostapp.data.datasource.local.LocalDataSourceImpl
import com.example.userpostapp.data.datasource.local.db.dao.PostDao
import com.example.userpostapp.data.datasource.local.db.dao.UserDao
import com.example.userpostapp.data.datasource.local.db.entity.PostEntity
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

    @MockK
    lateinit var postDao: PostDao

    @InjectMockKs
    lateinit var localDataSource: LocalDataSourceImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers() should call getAll()`() =
        runBlockingTest {
            // Given
            val expectedResultUserEntity = mockk<List<UserEntity>>()

            coEvery { userDao.getAll() } returns expectedResultUserEntity

            // When
            localDataSource.getUsers()

            // Then
            coVerify { userDao.getAll() }
        }

    @Test
    fun `saveUsers() should call insert()`() =
        runBlockingTest {
            // Given
            val expectedResult = mockk<List<UserEntity>>()

            coEvery { userDao.insert(expectedResult) } just Runs

            // When
            localDataSource.saveUsers(expectedResult)

            // Then
            coVerify { userDao.insert(expectedResult) }
        }

    @Test
    fun `getUserById() should call getAll()`() =
        runBlockingTest {
            // Given
            val expectedResultUserEntity = mockk<UserEntity>()

            coEvery { userDao.getUserById(1) } returns expectedResultUserEntity

            // When
            localDataSource.getUserById(1)

            // Then
            coVerify { userDao.getUserById(1) }
        }

    @Test
    fun `getPostsByIdUser() should call getAll()`() =
        runBlockingTest {
            // Given
            val expectedResultPostEntity = mockk<List<PostEntity>>()

            coEvery { postDao.getPostsByIdUser(1) } returns expectedResultPostEntity

            // When
            localDataSource.getPostsByIdUser(1)

            // Then
            coVerify { postDao.getPostsByIdUser(1) }
        }

    @Test
    fun `savePosts() should call insert()`() =
        runBlockingTest {
            // Given
            val expectedResult = mockk<List<PostEntity>>()

            coEvery { postDao.insert(expectedResult) } just Runs

            // When
            localDataSource.savePosts(expectedResult)

            // Then
            coVerify { postDao.insert(expectedResult) }
        }
}
