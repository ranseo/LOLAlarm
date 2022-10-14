package com.ranseo.lolalarm.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LOLMap(
    @field:Json(name="mapId")
    val mapId: Int,
    @field:Json(name="mapName")
    val mapName: String,
    @field:Json(name="notes")
    val notes: String
)
