package com.example.sandmanalarm.data

import androidx.room.TypeConverter
import com.example.sandmanalarm.data.domainModels.Alarm
import com.example.sandmanalarm.data.domainModels.Day
import com.example.sandmanalarm.data.entities.AlarmEntity

// This is an extension function
fun Alarm.asDatabaseModel(): AlarmEntity {
    //return AlarmEntity(id, wakeUpMethod, hoursOfSleep, days, vibration, sound )
    return AlarmEntity(wakeUpTime = this.wakeUpTime, hoursOfSleep =this.hoursOfSleep, id = 0, days = this.days.dayOfWeek, vibration = true, sound = true)
}

fun AlarmEntity.asDomainModel(): Alarm {
    return Alarm(wakeUpTime = this.wakeUpTime, hoursOfSleep = this.hoursOfSleep, days = Mapper.mapFromDay(0), isExpanded = true, vibration = true, sound = true)
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