package com.example.sandmanalarm.alarm

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.sandmanalarm.EditAlertEvent
import com.example.sandmanalarm.EventBus
import com.example.sandmanalarm.MinuteTickEvent
import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import com.example.sandmanalarm.databinding.AlarmItemViewholderBinding
import java.text.SimpleDateFormat
import java.util.*

class AlarmItemViewHolder(private val binding: AlarmItemViewholderBinding) : RecyclerView.ViewHolder(binding.root) {

    var calendar = Calendar.getInstance()
    var currentHour = calendar.get(Calendar.HOUR_OF_DAY)
    var currentMinute = calendar.get(Calendar.MINUTE)

    val formatter = SimpleDateFormat("HH:mm")

    init {
        EventBus.observeEvent(MinuteTickEvent::class.java)
            .subscribe({
                calendar = Calendar.getInstance()
                currentHour = calendar.get(Calendar.HOUR_OF_DAY)
                currentMinute = calendar.get(Calendar.MINUTE)
                binding.sleepStartTime.text = "${currentHour} : ${currentMinute}"
            }, {
                Log.d("Error", "Error", it)
            })
    }

    fun bind(alarm: Alarm) {
        val expanded: Boolean = alarm.isExpanded
        calendar = Calendar.getInstance()

        val alarmStartDisplayTime = String.format("%02d : %02d", currentHour, currentMinute)
        binding.sleepStartTime.text = alarmStartDisplayTime

        val alarmEndDisplayTime = String.format("%02d : %02d", alarm.targetHour, alarm.targetMinute)
        binding.sleepEndTime.text = alarmEndDisplayTime
        // Old school way of doing things
//        view.findViewById<View>(R.id.week_linear_layout).visibility = (if (expanded) View.VISIBLE else View.GONE)

        binding.sleepEndTime.setOnClickListener{
            EventBus.postEvent(EditAlertEvent(alarm))
        }
    }
}

/*
    How string format works

    val intValue = 111
    val s = String.format("%05d", intValue)
    println(s)            // 00111
 */