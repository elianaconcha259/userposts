package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.repository.UserRepository
import com.example.userpostapp.domain.usecase.impl.GetUserByIdUseCaseImpl
import com.example.userpostapp.util.common.AsyncResult
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUserByIdUseCaseImplTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var getUserByIdUseCase: GetUserByIdUseCaseImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUserByIdUseCase() should call getUser()`() =
        runBlockingTest {
            // Given

            val data = mockk<UserModel>()
            val expectedResult = AsyncResult.success(data)

            coEvery { userRepository.getUserById(1) } returns expectedResult

            // When
            getUserByIdUseCase(1)

            // Then
            coVerify { userRepository.getUserById(1) }
        }

}
