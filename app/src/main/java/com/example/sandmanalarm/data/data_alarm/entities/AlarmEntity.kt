package com.example.sandmanalarm.data.data_alarm.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "alarm_table")
data class AlarmEntity (
    @PrimaryKey
    val id : Long = 0,
//    val wakeUpTime: Float,
    val targetHour: Int,
    val targetMinute: Int,
    val days: Int, // Map this back to the enum
    val vibration: Boolean,
    val sound: Boolean
)
