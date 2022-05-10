package com.example.userpostapp.presentation.ui.userpost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.domain.usecase.GetUsersByQueryUseCase
import com.example.userpostapp.domain.usecase.GetUsersUseCase
import com.example.userpostapp.presentation.di.IoDispatcher
import com.example.userpostapp.util.common.AsyncResult
import com.example.userpostapp.util.common.AsyncResultStatus
import com.example.userpostapp.util.common.Errors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val getUsersByQueryUseCase: GetUsersByQueryUseCase,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    var viewState: MutableLiveData<UserViewState> = MutableLiveData()

    fun getUsers() = viewModelScope.launch(dispatcher) {
        viewState.postValue(ShowLoading)
        var response = getUsersUseCase()
        val result = processResponse(response)
        viewState.postValue(result)
    }

    fun getUsersByQuery(query: String) = viewModelScope.launch(dispatcher) {
        viewState.postValue(ShowLoading)
        var response = getUsersByQueryUseCase(query)
        val result = processResponse(response)
        viewState.postValue(result)
    }

    private fun processResponse(response: AsyncResult<List<UserModel>>): UserViewState {
        return when (response.status) {
            AsyncResultStatus.SUCCESS -> response.data?.let { ShowUsers(it) }
                ?: ShowError(Errors.UnknownError)
            AsyncResultStatus.ERROR -> ShowError(response.error ?: Errors.UnknownError)
        }
    }

}