package com.example.sandmanalarm.data.data_alarm

import androidx.room.TypeConverter
import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import com.example.sandmanalarm.data.data_alarm.domainModels.Day
import com.example.sandmanalarm.data.data_alarm.entities.AlarmEntity

// This is an extension function
fun Alarm.asDatabaseModel(): AlarmEntity {
    //return AlarmEntity(id, wakeUpMethod, hoursOfSleep, days, vibration, sound )
    return AlarmEntity(
        id, wakeUpTime, hoursOfSleep, days.dayOfWeek, true, true
    )
}

fun AlarmEntity.asDomainModel(): Alarm {
    return Alarm(
        id = this.id,
        wakeUpTime = this.wakeUpTime,
        hoursOfSleep = this.hoursOfSleep,
        days = com.example.sandmanalarm.data.data_alarm.Mapper.mapFromDay(0),
        isExpanded = true,
        vibration = true,
        sound = true
    )
}

object Mapper {
    fun map(dayOfWeek: Int): Day {
        return Day.values()[dayOfWeek]
    }

    // Hardcoded for now.
// TODO: Must amend later.
    @TypeConverter
    fun mapFromDay(dayOfWeek: Int): Day {
        return Day.MONDAY
    }

    @TypeConverter
    fun mapToDay(day: Day): Int {
        return 0
    }

}