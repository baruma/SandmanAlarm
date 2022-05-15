package com.example.sandmanalarm.data.data_alarm.domainModels

data class Alarm(
    val id: Long,
    // should probably have a sleepTime
//    val wakeUpTime: Float,
//    val wakeUpMethod: WakeUpMethod,
    val targetHour: Int,
    val targetMinute: Int,
    val days: Day,
    var isExpanded: Boolean,
    val vibration: Boolean,
    val sound: Boolean
)
