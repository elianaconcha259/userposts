package com.example.userpostapp.data.datasource.local.db.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.userpostapp.data.datasource.local.db.AppDatabase
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    companion object{
        private const val FIRST_POSITION = 0
    }

    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    private lateinit var userDao: UserDao
    private lateinit var db: AppDatabase

    @Before
    fun `create data base`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()
        userDao = db.userDao()
    }

    @Test
    @Throws(Exception::class)
    fun `verify insert and getAll using a product`() = runBlockingTest {
        val userList = listOf(UserEntity(1, "name", "email@mail.com","12345"))
        userDao.insert(userList)
        val userListFromDB = userDao.getAll()
        assertEquals(userListFromDB[FIRST_POSITION], userList[FIRST_POSITION])
    }

    @After
    @Throws(IOException::class)
    fun `close data base`() {
        db.close()
    }
}