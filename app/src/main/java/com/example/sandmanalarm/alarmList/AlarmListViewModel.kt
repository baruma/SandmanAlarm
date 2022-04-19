package com.example.sandmanalarm.alarmList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sandmanalarm.data.Alarm

class AlarmListViewModel : ViewModel() {

    var alarms = MutableLiveData<Alarm>()

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Alarm List"
    }
    val text: LiveData<String> = _text
}