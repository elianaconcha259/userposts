package com.example.userpostapp.presentation.ui.userpost

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.usecase.GetUsersUseCase
import com.example.userpostapp.util.common.AsyncResult
import com.example.userpostapp.util.common.Errors
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var testCoroutineDispatcher = TestCoroutineDispatcher()

    @MockK
    lateinit var getUsersUseCase: GetUsersUseCase

    @InjectMockKs
    private lateinit var viewModel: UserViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsers should call getUsersUseCase()`() =
        runBlockingTest {
            // Given
            val data = listOf(mockk<UserModel>())
            val expectedResult = AsyncResult.success(data)

            coEvery { getUsersUseCase() } returns expectedResult

            // When
            viewModel.getUsers()

            // Then
            coVerify { getUsersUseCase() }
        }

    @Test
    fun `getUsers should emit ShowUsers with data status through a liveData when getUsersUseCase returns success`() =
        runBlockingTest {
            // Given
            val data = listOf(mockk<UserModel>())
            val expectedResult = AsyncResult.success(data)

            coEvery { getUsersUseCase() } returns expectedResult

            // When
            viewModel.getUsers()
            val result = viewModel.viewState.value

            // Then
            assertEquals(ShowUsers(data), result)
        }

    @Test
    fun `getUsers should emit ShowError with data status through a liveData when getUsersUseCase returns success but data is null`() =
        runBlockingTest {
            // Given
            val data = null
            val expectedResult = AsyncResult.success(data)

            coEvery { getUsersUseCase() } returns expectedResult

            // When
            viewModel.getUsers()
            val result = viewModel.viewState.value

            // Then
            assertEquals(ShowError(Errors.UnknownError), result)
        }

    @Test
    fun `getUsers should emit ShowError with data status through a liveData when getUsersUseCase returns error`() =
        runBlockingTest {
            // Given
            val error = Errors.NetworkError
            val expectedResult: AsyncResult<List<UserModel>> = AsyncResult.error(error)

            coEvery { getUsersUseCase() } returns expectedResult

            // When
            viewModel.getUsers()
            val result = viewModel.viewState.value

            // Then
            assertEquals(ShowError(error), result)
        }

}
