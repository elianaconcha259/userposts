package com.example.userpostapp.util.common.error

import android.content.Context
import com.example.userpostapp.R
import com.example.userpostapp.util.common.Errors
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ErrorMessageImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : ErrorMessage {

    override fun getMessage(error: Errors?): String = when (error) {
        Errors.NetworkError -> context.getString(R.string.error_msg_network)
        else -> context.getString(R.string.error_msg_database)
    }
}
