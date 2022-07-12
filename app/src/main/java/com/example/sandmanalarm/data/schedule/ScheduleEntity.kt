package com.example.sandmanalarm.data.schedule

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule_table")
data class ScheduleEntity(
    @PrimaryKey
    val requestCode: Int,
    val flag: String,
    val alarmID: Long
)


//val alarmIntent = Intent(context, AlarmBroadcastReceiver::class.java).let { intent ->
//    PendingIntent.getBroadcast(context, 54321, intent, PendingIntent.FLAG_MUTABLE)}


