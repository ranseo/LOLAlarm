package com.ranseo.lolalarm.data

import androidx.room.Embedded
import androidx.room.Relation

data class TargetPlayerAndGameInfo(
    @Embedded val targetPlayer:TargetPlayer,
    @Relation(
        parentColumn = "targetId",
        entityColumn = "playerId"
    )
    val gameInfoLists: List<GameInfo>
)