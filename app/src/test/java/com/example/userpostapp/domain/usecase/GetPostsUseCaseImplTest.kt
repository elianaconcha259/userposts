package com.example.userpostapp.domain.usecase

import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.domain.repository.PostRepository
import com.example.userpostapp.domain.usecase.impl.GetPostsUseCaseImpl
import com.example.userpostapp.util.common.AsyncResult
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPostsUseCaseImplTest {

    @MockK
    lateinit var postRepository: PostRepository

    @InjectMockKs
    lateinit var getPostsUseCase: GetPostsUseCaseImpl


    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `gtPostsUseCase() should call getPostsByIdUsers()`() =
        runBlockingTest {
            // Given
            val data = mockk<List<PostModel>>()
            val expectedResult = AsyncResult.success(data)

            coEvery { postRepository.getPostsByIdUsers(1) } returns expectedResult

            // When
            getPostsUseCase(1)

            // Then
            coVerify { postRepository.getPostsByIdUsers(1) }
        }

}
