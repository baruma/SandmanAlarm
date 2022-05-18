package com.example.sandmanalarm.timer

import Timer
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sandmanalarm.alarm.ItemTouchHelperAdapter
import com.example.sandmanalarm.databinding.TimerItemViewholderBinding

class TimerListRecyclerAdapter(val onSwiped: () -> Unit): ItemTouchHelperAdapter, RecyclerView.Adapter<TimerItemViewHolder>() {
    var timers: MutableList<Timer> = mutableListOf()
    val timerRemovedLiveDAta = MutableLiveData<Timer>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): TimerItemViewHolder {
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.alarm_item_viewholder, viewGroup, false)

        val binding = TimerItemViewholderBinding.inflate(LayoutInflater.from(viewGroup.context))

        return TimerItemViewHolder(binding)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun onItemDismiss(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: TimerItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

}