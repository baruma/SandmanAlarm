package com.example.sandmanalarm.alarmList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandmanalarm.data.AlarmDatabase
import com.example.sandmanalarm.data.asDatabaseModel
import com.example.sandmanalarm.data.domainModels.Alarm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AlarmListViewModel(private val database: AlarmDatabase, private val dispatcher: CoroutineDispatcher)
    : ViewModel() {

    /* You need a live data to denote the entire data payload, and the most recently made object
     to add to that payload.

     Livedata is strictly used between viewModel and View.
     */
    var alarms = MutableLiveData<List<Alarm>>()
    var newAlarm = MutableLiveData<Alarm>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Alarm List"
    }
    val text: LiveData<String> = _text

    fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch(dispatcher) {
            // inserts object into Database
            database.alarmDAO.insert(alarm.asDatabaseModel())
            // adds new object into viewModel's object array to update view with.
            newAlarm.postValue(alarm)
        }
    }

    fun deleteAlarm(alarm: Alarm) {
        viewModelScope.launch(dispatcher) {
            database.alarmDAO.delete(alarm.asDatabaseModel())
        }
    }
}