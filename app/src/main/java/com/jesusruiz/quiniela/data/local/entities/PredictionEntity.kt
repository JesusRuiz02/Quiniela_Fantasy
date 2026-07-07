package com.jesusruiz.quiniela.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "predictions",
    foreignKeys = [
        ForeignKey(
            entity = GameEntity::class,
            parentColumns = ["id"],
            childColumns = ["gameId"],
            onDelete = ForeignKey.CASCADE
        )
    ])

data class PredictionEntity(
    @PrimaryKey
    val id: String = "",
    val gameId: String = "",
    val userId: String = "",
    val homePrediction: Int = 0,
    val awayPrediction: Int = 0,
    val pointsEarned: Int = 0
)