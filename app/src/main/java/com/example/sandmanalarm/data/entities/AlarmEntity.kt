package com.example.sandmanalarm.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "alarm_table")
data class AlarmEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val wakeUpTime: Float,
    val hoursOfSleep: Int,
    val days: Int, // Map this back to the enum
    val vibration: Boolean,
    val sound: Boolean
)
