package com.example.sandmanalarm.data

data class Alarm(
    val wakeUpTime: WakeUpTime,
    val wakeUpMethod: WakeUpMethod,
    val hoursOfSleep: Int,
    var isExpanded: Boolean)
