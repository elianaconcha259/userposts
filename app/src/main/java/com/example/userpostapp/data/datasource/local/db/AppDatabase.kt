package com.example.userpostapp.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userpostapp.data.datasource.local.db.dao.PostDao
import com.example.userpostapp.data.datasource.local.db.dao.UserDao
import com.example.userpostapp.data.datasource.local.db.entity.PostEntity
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity

@Database(version = 1, entities = [UserEntity::class, PostEntity::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    abstract fun postDao(): PostDao

}
