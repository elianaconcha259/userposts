package com.example.userpostapp.presentation.ui.product.detail

import com.example.userpostapp.domain.model.PostModel
import com.example.userpostapp.domain.model.UserModel
import com.example.userpostapp.presentation.ui.userpost.UserViewState
import com.example.userpostapp.util.common.Errors

sealed class UserDetailViewState
object ShowLoading :  UserDetailViewState()
data class ShowUser(val data: UserModel) : UserDetailViewState()
data class ShowPostUser(val data: List<PostModel>) : UserDetailViewState()
data class ShowError(val error: Errors) : UserDetailViewState()

