package com.example.sandmanalarm.data.domainModels

data class Alarm(
    val wakeUpTime: WakeUpTime,
    val wakeUpMethod: WakeUpMethod,
    val hoursOfSleep: Int,
    var isExpanded: Boolean)
