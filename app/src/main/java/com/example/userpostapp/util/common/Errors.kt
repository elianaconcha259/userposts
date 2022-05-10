package com.example.userpostapp.util.common

sealed class Errors {
    object NetworkError : Errors()
    object DatabaseError : Errors()
    object UnknownError : Errors()
}
