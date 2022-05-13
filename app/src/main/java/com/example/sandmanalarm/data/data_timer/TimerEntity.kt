package com.example.sandmanalarm.data.data_timer

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timer_table")
data class TimerEntity (
    @PrimaryKey
    val id: Long,
    val time: Long,
    val running: Boolean,
//    val alertMethod: AlertMethod,
    val timeRemaining: Long
)