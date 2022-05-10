package com.example.userpostapp.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.userpostapp.data.datasource.local.db.entity.PostEntity
import com.example.userpostapp.data.datasource.local.db.entity.UserEntity

@Dao
interface PostDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: List<PostEntity>)

    @Query("SELECT * FROM post WHERE userId = :idUser")
    suspend fun getPostsByIdUser(idUser: Int): List<PostEntity>

}
