package com.jesusruiz.quiniela.data.datasource

import com.jesusruiz.quiniela.utils.Resource

interface UserRepository {
    suspend fun getUserId(): Resource<String>

    suspend fun getUserEmail(): Resource<String>

    suspend fun loginUser(email: String, password: String): Resource<Unit>

    suspend fun registerUser(email: String, password: String): Resource<Unit>

}