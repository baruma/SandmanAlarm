package com.example.sandmanalarm.data

import android.content.Context
import androidx.room.Room
import com.example.sandmanalarm.data.data_alarm.AlarmDatabase

/**
 * Singleton class that is used to create a reminder db
 * Better to have a single database so that you can keep all your DAOs within it so you don't have to
 * recreate a DAO instance for new tables
 */
object LocalDB {
    /**
     * static method that creates a reminder class and returns the DAO of the reminder
     */
    fun createAlarmDAO(context: Context): AlarmDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AlarmDatabase::class.java, "alarm_database"
        ).build()
    }
}