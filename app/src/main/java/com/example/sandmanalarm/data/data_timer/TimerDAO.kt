package com.example.sandmanalarm.data.data_timer

import androidx.room.*

@Dao
interface TimerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(timer: TimerEntity)

    @Query("SELECT * FROM timer_table ORDER BY id DESC")
    suspend fun getAll(): List<TimerEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun getSingle(timer: TimerEntity)

    @Delete
    suspend fun delete(timer: TimerEntity)
}