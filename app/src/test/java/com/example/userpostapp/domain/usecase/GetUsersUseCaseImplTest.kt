package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.repository.UserRepository
import com.example.userpostapp.domain.usecase.impl.GetUsersUseCaseImpl
import com.example.userpostapp.util.common.AsyncResult
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersUseCaseImplTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var getUsersUseCase: GetUsersUseCaseImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsersUseCase() should call getUsers()`() =
        runBlockingTest {
            // Given
            val data = mockk<List<UserModel>>()
            val expectedResult = AsyncResult.success(data)

            coEvery { userRepository.getUsers() } returns expectedResult

            // When
            getUsersUseCase()

            // Then
            coVerify { userRepository.getUsers() }
        }

}
