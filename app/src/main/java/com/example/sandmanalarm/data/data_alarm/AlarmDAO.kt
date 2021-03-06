package com.example.sandmanalarm.data.data_alarm

import androidx.room.*
import com.example.sandmanalarm.data.data_alarm.entities.AlarmEntity


@Dao
interface AlarmDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alarm: AlarmEntity)

    @Query("SELECT * FROM alarm_table ORDER BY id DESC")
    suspend fun getAll(): List<AlarmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getSingle(alarm: AlarmEntity)

    @Delete
    suspend fun delete(alarm: AlarmEntity)
}