package com.example.sandmanalarm.data.data_timer

import Timer

// This is an extension function
fun Timer.asDatabaseModel(): TimerEntity {
    //return AlarmEntity(id, wakeUpMethod, hoursOfSleep, days, vibration, sound )
    return TimerEntity(
        id, time, running, timeRemaining
    )
}

fun TimerEntity.asDomainModel(): Timer {
    return Timer(
        id = this.id,
        time,
        running,
        timeRemaining
    )
}
