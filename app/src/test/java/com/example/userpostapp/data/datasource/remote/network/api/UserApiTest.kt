package com.example.userpostapp.data.datasource.remote.network.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.test.runners.AndroidJUnit4
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
class UserApiTest {

    @get:Rule
    var instantTask = InstantTaskExecutorRule()

    private lateinit var api: UserApi

    @Before
    fun `create product api`() {
        api = ApiProvider().create(UserApi::class.java)
    }

    @Test
    @Throws(Exception::class)
    fun `verify response get users is not null`() = runBlocking {
        val response = api.getUsers()
        assertFalse(response.isNullOrEmpty())
    }

    @Test
    @Throws(Exception::class)
    fun `verify response getPostsByUser is not null`() = runBlocking {
        val response = api.getPostsByUser(1)
        assertFalse(response.isNullOrEmpty())
    }

}