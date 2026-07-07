package com.jesusruiz.quiniela.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "games")
data class GameEntity(
    @PrimaryKey
    val id: String = "",
    val journeyId: String = "",
    val homeTeam: String = "",
    val awayTeam: String = "",
    val homeScore: Int = 0,
    val awayScore: Int = 0
)