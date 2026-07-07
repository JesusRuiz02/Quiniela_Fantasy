package com.jesusruiz.quiniela.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "journeys",
    foreignKeys = [
        ForeignKey(
            entity = LeagueEntity::class,
            parentColumns = ["id"],
            childColumns = ["leagueId"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class JourneyEntity(
    @PrimaryKey  val id: String = "",
    val name: String = "",
    val leagueId: String = "",
    val roundNumber: Int = 0,
    val startDate: Long = 0L,
    val endDate: Long = 0L,
    val state: String = ""
)
