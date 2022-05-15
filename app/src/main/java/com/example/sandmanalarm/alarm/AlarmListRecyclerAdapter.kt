package com.example.sandmanalarm.alarm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sandmanalarm.R
import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import com.example.sandmanalarm.databinding.AlarmItemViewholderBinding


// TODO: Continue off of this tutorial for collapsible cell:
//  https://medium.com/@nikola.jakshic/how-to-expand-collapse-items-in-recyclerview-49a648a403a6

class AlarmListRecyclerAdapter(val onSwiped : () -> Unit) : ItemTouchHelperAdapter, RecyclerView.Adapter<AlarmItemViewHolder>() {

    // Viewholders aren't views.  You are redirecting the view being passed in to register the onClick
    // the viewholder will always have a view since it's declared with a constructor that has a view
    // you can route the onclicklistener from the view to the viewholder in the init.

    var alarms: MutableList<Alarm> = mutableListOf()
    val alarmRemovedLiveData = MutableLiveData<Alarm>()


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmItemViewHolder {
//        val view = LayoutInflater.from(viewGroup.context)
//            .inflate(R.layout.alarm_item_viewholder, viewGroup, false)


        val binding = AlarmItemViewholderBinding.inflate(LayoutInflater.from(viewGroup.context))

        return AlarmItemViewHolder(binding)
    }

    /* To update the recycler and add a new alarm, this is the code that will be called again by the
    "notifyItemInserted" to go through adapter code again and make change as necessary.
     */
    override fun onBindViewHolder(holder: AlarmItemViewHolder, position: Int) {
        val alarm = alarms.get(position)
        holder.bind(alarm)
    }

    fun addNewAlarm(alarm: Alarm) {
        alarms.add(alarm)
        this.notifyItemInserted(alarms.size - 1)
    }

    fun updateAlarms(newAlarms: List<Alarm>) {
        alarms.clear()
        alarms.addAll(newAlarms)
        notifyDataSetChanged()
    }

    override fun getItemCount() = alarms.count()

    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
        return false
    }

    override fun onItemDismiss(position: Int) {
        alarmRemovedLiveData.postValue(alarms[position])
        alarms.removeAt(position)
        notifyItemRemoved(position)
        this.onSwiped()
    }
}


