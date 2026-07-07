package com.jesusruiz.quiniela.data.local.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "leagues")
data class LeagueEntity(
    @PrimaryKey  val id: String = "",
    val name: String = "",
    val description: String = "",
    val ownerId: String = "",
    val apiId: String = "",
    val apiLeagueName: String = "",
    val startDate: Long = 0L,
    val isPrivate: Boolean = false
)


@Entity(
    tableName = "league_members",
    primaryKeys = ["leagueId", "userId"],
    foreignKeys = [
        ForeignKey(
            entity = LeagueEntity::class,
            parentColumns = ["id"],
            childColumns = ["leagueId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class LeagueMemberEntity(
    val leagueId: String = "",
    val userId: String = "",
    val joinedDate: Long = 0L
)




