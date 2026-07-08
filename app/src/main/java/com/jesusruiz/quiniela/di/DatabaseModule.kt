package com.jesusruiz.quiniela.di

import android.content.Context
import androidx.room.Room
import com.jesusruiz.quiniela.data.local.QuinielaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideQuinielaDatabase(
       @ApplicationContext context: Context
    ): QuinielaDatabase {
        return Room.databaseBuilder(
            context,
            QuinielaDatabase::class.java,
            "quiniela_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideGameDao(database: QuinielaDatabase) = database.gameDao()

    @Singleton
    @Provides
    fun providePredictionDao(database: QuinielaDatabase) = database.predictionDao()

    @Singleton
    @Provides
    fun provideUserDao(database: QuinielaDatabase) = database.userDao()

    @Singleton
    @Provides
    fun provideLeagueDao(database: QuinielaDatabase) = database.leagueDao()

    @Singleton
    @Provides
    fun provideLeagueMembersDao(database: QuinielaDatabase) = database.leagueMemberDao()
}