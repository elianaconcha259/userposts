package com.example.userpostapp.data.datasource.local.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.userpostapp.data.datasource.local.db.AppDatabase
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runner.manipulation.Ordering
import java.io.IOException
import junit.framework.Assert.assertEquals
import android.content.Context
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userpostapp.data.datasource.local.db.entity.PostEntity

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class PostDaoTest {

    companion object{
        private const val FIRST_POSITION = 0
    }

    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    private lateinit var postDao: PostDao
    private lateinit var db: AppDatabase

    @Before
    fun `create data base`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java
        ).allowMainThreadQueries().build()
        postDao = db.postDao()
    }

    @Test
    @Throws(Exception::class)
    fun `verify insert and getPostsByIdUser using a post`() = runBlockingTest {
        val postList = listOf(PostEntity(1, 1, "title", "body"))
        postDao.insert(postList)
        val postsListFromDB = postDao.getPostsByIdUser(1)
        assertEquals(postsListFromDB[FIRST_POSITION], postList[FIRST_POSITION])
    }

    @After
    @Throws(IOException::class)
    fun `close data base`() {
        db.close()
    }
}