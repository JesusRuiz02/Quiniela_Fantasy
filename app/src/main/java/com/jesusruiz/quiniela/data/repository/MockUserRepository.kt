package com.jesusruiz.quiniela.data.repository

import com.jesusruiz.quiniela.data.datasource.UserRepository
import com.jesusruiz.quiniela.utils.Resource
import com.jesusruiz.quiniela.utils.generateLeagueId
import javax.inject.Inject

class MockUserRepository @Inject constructor(): UserRepository {
    override suspend fun getUserId(): Resource<String> {
        return Resource.Success(generateLeagueId())
    }

    override suspend fun getUserEmail(): Resource<String> {
        return Resource.Success("jesusit@outlook.com")
    }

    override suspend fun loginUser(
        email: String,
        password: String
    ): Resource<Unit> {
        return Resource.Success(Unit)
    }

    override suspend fun registerUser(
        email: String,
        password: String
    ): Resource<Unit> {
        return Resource.Success(Unit)
        }
    }