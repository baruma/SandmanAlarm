package com.example.sandmanalarm.data

import com.example.sandmanalarm.data.entities.AlarmEntity

class AlarmRepository (private val database: AlarmDatabase, private val alarm: AlarmEntity) {
    /*  The repository is kind of like a catch-all.  It implements all the parts of the database that
    were just declared.  It has access to the database which houses the DAOs which houses the means of
    manipulating data.

    The ViewModel that will be implementing this Repository has access to the DAO functions and can load
    data into our view as needed.
    */

    suspend fun loadAlarms(): List<AlarmEntity> {
        return database.alarmDAO.getAll()
    }

    suspend fun loadAlarm() {
        return database.alarmDAO.getSingle(alarm)
    }

    suspend fun saveAlarm(alarmToSave: AlarmEntity) {
        return database.alarmDAO.insert(alarmToSave)
    }

}