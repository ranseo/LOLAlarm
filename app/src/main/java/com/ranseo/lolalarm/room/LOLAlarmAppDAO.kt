package com.ranseo.lolalarm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ranseo.lolalarm.data.TargetPlayer

@Dao
interface AlarmDAO {
    @Query("SELECT * FROM target_player_table ORDER BY playerId DESC")
    fun getAll() : LiveData<List<TargetPlayer>>
}

@Dao
interface SearchDAO {
    @Insert
    suspend fun insertTargetPlayer(targetPlayer: TargetPlayer)
}