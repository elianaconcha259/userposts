package com.example.userpostapp.util.common.error

import com.example.userpostapp.util.common.Errors

interface ErrorMessage {
    fun getMessage(error: Errors?): String
}
