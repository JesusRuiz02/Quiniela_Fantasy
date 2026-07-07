package com.jesusruiz.quiniela.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String = "",
    val username: String = "",
    val email: String = "",
)
