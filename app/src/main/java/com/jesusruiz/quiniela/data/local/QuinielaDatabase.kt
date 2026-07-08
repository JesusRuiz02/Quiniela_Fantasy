package com.jesusruiz.quiniela.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jesusruiz.quiniela.data.local.daos.GameDao
import com.jesusruiz.quiniela.data.local.daos.JourneyDao
import com.jesusruiz.quiniela.data.local.daos.LeagueDao
import com.jesusruiz.quiniela.data.local.daos.LeagueMembersDao
import com.jesusruiz.quiniela.data.local.daos.PredictionDao
import com.jesusruiz.quiniela.data.local.daos.UserDao
import com.jesusruiz.quiniela.data.local.entities.GameEntity
import com.jesusruiz.quiniela.data.local.entities.JourneyEntity
import com.jesusruiz.quiniela.data.local.entities.LeagueEntity
import com.jesusruiz.quiniela.data.local.entities.LeagueMemberEntity
import com.jesusruiz.quiniela.data.local.entities.PredictionEntity
import com.jesusruiz.quiniela.data.local.entities.UserEntity

@Database(
    entities = [
        GameEntity::class,
        PredictionEntity::class,
        JourneyEntity::class,
        LeagueEntity::class,
        UserEntity::class,
        LeagueMemberEntity::class
    ],
    version = 1,
    exportSchema = false)
abstract class QuinielaDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao
    abstract fun journeyDao(): JourneyDao
    abstract fun userDao(): UserDao
    abstract fun leagueDao(): LeagueDao
    abstract fun predictionDao(): PredictionDao

    abstract fun leagueMemberDao(): LeagueMembersDao
}