package com.example.sandmanalarm.alarm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sandmanalarm.data.data_alarm.AlarmRepository
import com.example.sandmanalarm.data.data_alarm.asDatabaseModel
import com.example.sandmanalarm.data.data_alarm.asDomainModel
import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class AlarmListViewModel(
    private val repository: AlarmRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    /* You need a live data to denote the entire data payload, and the most recently made object
     to add to that payload.

     Livedata is strictly used between viewModel and View.
     */
    var alarmLiveDataList = MutableLiveData<List<Alarm>>()
    var newAlarmLiveData = MutableLiveData<Alarm>()

    var alarmList =
        mutableListOf<Alarm>()  // This is the source of truth since it updates the database by being added to the LiveData list.

    var minute = MutableLiveData<Int>()
    var hour = MutableLiveData<Int>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Alarm List"
    }
    val text: LiveData<String> = _text

    fun saveAlarm(alarm: Alarm) {
        viewModelScope.launch(dispatcher) {
            // inserts object into Database
//            database.alarmDAO.insert(alarm.asDatabaseModel())
            repository.saveAlarm(alarm.asDatabaseModel())
            // adds new object into viewModel's object array to update view with.
            alarmList.add(alarm)
            // LEARNING NOTE - Having two postValue's below will cause multiple additions to your recycler
//            alarmLiveDataList.postValue(alarmList)
            newAlarmLiveData.postValue(alarm)
        }
    }

    fun editAlarm(alarm: Alarm) {
        viewModelScope.launch(dispatcher) {
            repository.saveAlarm(alarm.asDatabaseModel())
            var index = 0
            for(indexedAlarm in alarmList) {
                if(indexedAlarm.id == alarm.id) {
                    break
                }
                index++
            }

            alarmList[index] = alarm
            alarmLiveDataList.postValue(alarmList)
        }
    }

    fun deleteAlarm(alarm: Alarm) {
        viewModelScope.launch(dispatcher) {
//            database.alarmDAO.delete(alarm.asDatabaseModel())
            repository.deleteAlarm(alarm.asDatabaseModel())
            alarmList.remove(alarm)
            alarmLiveDataList.postValue(alarmList)
        }
    }

    fun loadAlarms() {
        viewModelScope.launch(dispatcher) {
            alarmList.clear()
            alarmList.addAll(repository.loadAlarms().map { it.asDomainModel() })
            alarmLiveDataList.postValue(alarmList)
        }
    }
}