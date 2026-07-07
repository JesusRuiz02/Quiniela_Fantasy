package com.jesusruiz.quiniela.utils

import com.jesusruiz.quiniela.data.local.entities.LeagueEntity
import com.jesusruiz.quiniela.models.League

fun LeagueEntity.toLeague(): League {
    return League(
        id = this.id,
        leagueName = this.name,
        description = this.description,
        ownerId = this.ownerId,
        apiID = this.apiId,
        apiLeagueName = this.apiLeagueName,
        startDate = this.startDate
    )
}

fun League.toLeagueEntity(): LeagueEntity {
    return LeagueEntity(
        id = this.id,
        name = this.leagueName,
        description = this.description,
        ownerId = this.ownerId,
        apiId = this.apiID,
        apiLeagueName = this.apiLeagueName,
        startDate = this.startDate
    )
}