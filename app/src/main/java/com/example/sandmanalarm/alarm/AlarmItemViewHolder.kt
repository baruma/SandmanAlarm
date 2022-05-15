package com.example.sandmanalarm.alarm

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import com.example.sandmanalarm.databinding.AlarmItemViewholderBinding
import java.util.*

class AlarmItemViewHolder(private val binding: AlarmItemViewholderBinding) : RecyclerView.ViewHolder(binding.root),
    View.OnClickListener {

    val calendar = Calendar.getInstance()

    val hour = calendar.get(Calendar.HOUR_OF_DAY)
    val minute = calendar.get(Calendar.MINUTE)

    init {
      //  binding.sleepStartTime.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

    }

    // to show current time
    // calendar class
    //SimpleDateFormat class
    fun bind(alarm: Alarm) {
        val expanded: Boolean = alarm.isExpanded
        binding.sleepStartTime.text = "${hour} : ${minute}"
        binding.sleepEndTime.text = "${alarm.targetHour} : ${alarm.targetMinute}"
        // Old school way of doing things
//        view.findViewById<View>(R.id.week_linear_layout).visibility = (if (expanded) View.VISIBLE else View.GONE)
    }


}