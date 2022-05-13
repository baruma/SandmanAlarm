package com.example.sandmanalarm.data.data_alarm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.sandmanalarm.data.data_alarm.entities.AlarmEntity

@Database(entities = [AlarmEntity :: class], version = 2, exportSchema = false)
@TypeConverters(Mapper::class)
abstract class AlarmDatabase: RoomDatabase() {

    abstract val alarmDAO: AlarmDAO

    // allows clients to access the methods without having to instantiate the class
    companion object {
        @Volatile  // ensures value of instance is always up to date and will not be cached.
        // Changes in one thread will be present to all other threads.  all writes/reads will be done from main
        private var INSTANCE: AlarmDatabase? = null  // Instance keeps a reference to the database

        fun getDatabase(context: Context): AlarmDatabase { // This is a DB builder
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext,
                        AlarmDatabase::class.java,
                        "alarm_database")
                        .fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}