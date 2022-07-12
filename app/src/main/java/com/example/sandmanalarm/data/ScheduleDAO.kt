package com.example.sandmanalarm.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.sandmanalarm.data.schedule.ScheduleEntity

@Dao
interface ScheduleDAO  {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(schedule: ScheduleEntity)

  @Delete
  suspend fun delete(schedule: ScheduleEntity)
}