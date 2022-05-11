package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.repository.UserRepository
import com.example.userpostapp.domain.usecase.impl.GetUsersByQueryUseCaseImpl
import com.example.userpostapp.util.common.AsyncResult
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetUsersByQueryUseCaseImplTest {

    @MockK
    lateinit var userRepository: UserRepository

    @InjectMockKs
    lateinit var getUsersByQueryUseCase: GetUsersByQueryUseCaseImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `getUsersByQueryUseCase() should call getUsersByQuery()`() =
        runBlockingTest {
            // Given
            val data = mockk<List<UserModel>>()
            val expectedResult = AsyncResult.success(data)

            coEvery { userRepository.getUsersByQuery("test") } returns expectedResult

            // When
            getUsersByQueryUseCase("test")

            // Then
            coVerify { userRepository.getUsersByQuery("test") }
        }

}
