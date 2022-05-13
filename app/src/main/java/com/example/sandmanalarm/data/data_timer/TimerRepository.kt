package com.example.sandmanalarm.data.data_timer

import com.example.sandmanalarm.data.data_alarm.AlarmDatabase

class TimerRepository(private val database: TimerDatabase) {
    suspend fun loadTimers(): List<TimerEntity> {
        return database.timerDAO.getAll()
    }

    suspend fun saveTimer(timerToSave: TimerEntity) {
        return database.timerDAO.insert(timerToSave)
    }

    suspend fun deleteAlarm(timer: TimerEntity) {
        return database.timerDAO.delete(timer)
    }
}