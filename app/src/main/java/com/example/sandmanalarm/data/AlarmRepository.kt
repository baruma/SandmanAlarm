package com.example.sandmanalarm.data

import com.example.sandmanalarm.data.entities.AlarmEntity

class AlarmRepository (private val database: AlarmDatabase) {
    /*  The repository is kind of like a catch-all.  It implements all the parts of the database that
    were just declared.  It has access to the database which houses the DAOs which houses the means of
    manipulating data.

    The ViewModel can implement this Repository has access to the DAO functions and can load
    data into our view as needed.

    This repository isn't in use because the database implements the DAO.

    Repositories are better used if there are multiple databases / entity types.
    */

    suspend fun loadAlarms(): List<AlarmEntity> {
        return database.alarmDAO.getAll()
    }

    suspend fun saveAlarm(alarmToSave: AlarmEntity) {
        return database.alarmDAO.insert(alarmToSave)
    }

    suspend fun deleteAlarm(alarm: AlarmEntity) {
        return database.alarmDAO.delete(alarm)
    }

}