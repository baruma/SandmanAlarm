package com.example.sandmanalarm.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sandmanalarm.data.domainModels.Alarm
import com.example.sandmanalarm.data.entities.AlarmEntity

@Dao
interface AlarmDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(alarm: AlarmEntity)

    @Query("SELECT * FROM alarm_table ORDER BY id DESC")
    suspend fun getAll(): List<AlarmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getSingle(alarm: AlarmEntity)
}