package com.example.userpostapp.presentation.ui.userpost

import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.util.common.Errors

sealed class UserViewState
data class ShowUsers(val data: List<UserModel>) : UserViewState()
object ShowLoading :  UserViewState()
data class ShowError(val error: Errors) : UserViewState()

