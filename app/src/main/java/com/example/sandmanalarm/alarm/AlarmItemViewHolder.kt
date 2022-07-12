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

    var alarmStartDisplayTime = String()
    var alarmEndDisplayTime = String()

    val formatter = SimpleDateFormat("HH:mm")

//    val alarmScheduler = AlarmScheduler(binding.root.context)

    init {
        EventBus.subscribeToEvent(MinuteTickEvent::class.java)
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

        alarmStartDisplayTime = String.format("%02d : %02d", currentHour, currentMinute)
        binding.sleepStartTime.text = alarmStartDisplayTime

        alarmEndDisplayTime = String.format("%02d : %02d", alarm.targetHour, alarm.targetMinute)
        binding.sleepEndTime.text = alarmEndDisplayTime
        // Old school way of doing things
//        view.findViewById<View>(R.id.week_linear_layout).visibility = (if (expanded) View.VISIBLE else View.GONE)

        binding.sleepEndTime.setOnClickListener{
            EventBus.publishEvent(EditAlertEvent(alarm))
        }
    }

    // Doing this is a bad idea - Not every viewholder needs an alarm scheduler
    fun callScheduler() {
        // Maybe do this in the EditAlertEvent instead
        if (alarmStartDisplayTime == alarmEndDisplayTime) {
//            alarmScheduler.scheduleExact5SFromNow()
        }
    }
}

/*
    How string format works

    val intValue = 111
    val s = String.format("%05d", intValue)
    println(s)            // 00111
 */