package com.example.userpostapp.presentation.ui.userpost.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.domain.usecase.GetPostsUseCase
import com.example.userpostapp.presentation.di.IoDispatcher
import com.example.userpostapp.presentation.ui.product.detail.ShowError
import com.example.userpostapp.presentation.ui.product.detail.ShowLoading
import com.example.userpostapp.presentation.ui.product.detail.ShowPostUser
import com.example.userpostapp.presentation.ui.product.detail.UserDetailViewState
import com.example.userpostapp.util.common.AsyncResult
import com.example.userpostapp.util.common.AsyncResultStatus
import com.example.userpostapp.util.common.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var viewState: MutableLiveData<UserDetailViewState> = MutableLiveData()

    fun getPostByIdUser(userId: Int) = viewModelScope.launch(dispatcher) {
        viewState.postValue(ShowLoading)
        var response = getPostsUseCase(userId)
        val result = processResponse(response)
        viewState.postValue(result)
    }

    private fun processResponse(response: AsyncResult<List<PostModel>>): UserDetailViewState {
        return when (response.status) {
            AsyncResultStatus.SUCCESS -> response.data?.let { ShowPostUser(it) }
                ?: ShowError(Errors.UnknownError)
            AsyncResultStatus.ERROR -> ShowError(response.error ?: Errors.UnknownError)
        }
    }

}