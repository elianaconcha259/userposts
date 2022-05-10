package com.example.userpostapp.presentation.ui.product.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.usecase.GetUserByIdUseCase
import com.example.userpostapp.presentation.di.IoDispatcher
import com.example.userpostapp.util.common.AsyncResult
import com.example.userpostapp.util.common.AsyncResultStatus
import com.example.userpostapp.util.common.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserByIdUseCase: GetUserByIdUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var viewState: MutableLiveData<UserDetailViewState> = MutableLiveData()

    fun getProduct(id: Int) = viewModelScope.launch(dispatcher) {
        val response = getUserByIdUseCase(id)
        val result = processResponse(response)
        viewState.postValue(result)
    }

    private fun processResponse(response: AsyncResult<UserModel>): UserDetailViewState {
        return when (response.status) {
            AsyncResultStatus.SUCCESS -> response.data?.let { ShowUser(it) }?: ShowError(Errors.DatabaseError)
            AsyncResultStatus.ERROR -> ShowError(response.error ?: Errors.DatabaseError)
        }
    }

}
