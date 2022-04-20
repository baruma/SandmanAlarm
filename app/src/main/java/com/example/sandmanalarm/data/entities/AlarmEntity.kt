package com.example.sandmanalarm.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.sandmanalarm.data.domainModels.DaysOfWeek
import com.example.sandmanalarm.data.domainModels.WakeUpMethod
import com.example.sandmanalarm.data.domainModels.WakeUpTime

@Entity (tableName = "alarm_table")
data class AlarmEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val wakeUpTime: WakeUpTime,
    val hoursOfSleep: Int,
    val daysActive: DaysOfWeek,
    val wakeUpMethod: WakeUpMethod
    )

// Left off here and making the Repository