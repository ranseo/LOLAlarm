package com.ranseo.lolalarm.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.ranseo.lolalarm.data.GameInfo
import com.ranseo.lolalarm.data.Spectator
import com.ranseo.lolalarm.data.TargetPlayer

@Dao
interface AlarmDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTargetPlayer(targetPlayer: TargetPlayer)

    @Query("DELETE FROM target_player_table")
    suspend fun deleteAllPlayer()

    @Query("SELECT * FROM target_player_table ORDER BY targetId DESC")
    fun getAll() : LiveData<List<TargetPlayer>>

    //Spectator

    @Insert(onConflict = REPLACE)
    suspend fun insertGameInfo(gameInfo: GameInfo)

    @Query("SELECT * FROM game_info_table ORDER BY time_stamp DESC LIMIT 1")
    suspend fun getLatestGameInfo() : GameInfo

    @Query("SELECT * FROM game_info_table ORDER BY time_stamp DESC LIMIT 5")
    fun getAllGameInfo() : LiveData<List<GameInfo>>

}