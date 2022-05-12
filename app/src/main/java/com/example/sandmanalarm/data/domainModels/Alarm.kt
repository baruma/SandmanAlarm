package com.example.sandmanalarm.data.domainModels

data class Alarm(
    val id: Long,
    val wakeUpTime: Float,
//    val wakeUpMethod: WakeUpMethod,
    val hoursOfSleep: Int,
    val days: Day,
    var isExpanded: Boolean,
    val vibration: Boolean,
    val sound: Boolean
)
