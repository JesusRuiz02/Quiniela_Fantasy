package com.jesusruiz.quiniela.di

import com.jesusruiz.quiniela.data.datasource.PredictionsRepository
import com.jesusruiz.quiniela.data.datasource.ResultsRepository
import com.jesusruiz.quiniela.data.datasource.UserLeaguesRepository
import com.jesusruiz.quiniela.data.datasource.UserRepository
import com.jesusruiz.quiniela.data.local.QuinielaDatabase
import com.jesusruiz.quiniela.data.repository.MockCalculationPointsImp
import com.jesusruiz.quiniela.data.repository.MockResultsRepositoryImplementation
import com.jesusruiz.quiniela.data.repository.MockUserLeagueImplementation
import com.jesusruiz.quiniela.data.repository.MockUserRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindResultsRepository(implementation: MockResultsRepositoryImplementation): ResultsRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(implementation: MockUserLeagueImplementation): UserLeaguesRepository

    @Binds
    @Singleton
    abstract fun bindCalculationPointsRepository(implementation: MockCalculationPointsImp): PredictionsRepository

    @Binds
    @Singleton
    abstract fun bindUserLeaguesRepository(implementation: MockUserRepository): UserRepository

}
