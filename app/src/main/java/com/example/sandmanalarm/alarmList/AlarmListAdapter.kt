package com.example.sandmanalarm.alarmList

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sandmanalarm.R
import com.example.sandmanalarm.data.domainModels.Alarm


// TODO: Continue off of this tutorial for collapsible cell:
//  https://medium.com/@nikola.jakshic/how-to-expand-collapse-items-in-recyclerview-49a648a403a6

class AlarmListAdapter : RecyclerView.Adapter<AlarmListAdapter.AlarmItemViewHolder>() {

    // Viewholders aren't views.  You are redirecting the view being passed in to register the onClick
    // the viewholder will always have a view since it's declared with a constructor that has a view
    // you can route the onclicklistener from the view to the viewholder in the init.

    private var alarms: MutableList<Alarm> = mutableListOf()

    class AlarmItemViewHolder(view: View) : RecyclerView.ViewHolder(view), OnClickListener {
        private var view: View = view
        val textView: TextView

        init {
            textView = view.findViewById(R.id.alarm_time)
            textView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

        }

        fun bind(alarm: Alarm) {
            val expanded: Boolean = alarm.isExpanded
            view.findViewById<View>(R.id.week_linear_layout).visibility = (if (expanded) View.VISIBLE else View.GONE)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.alarm_item_viewholder, viewGroup, false)

        return AlarmItemViewHolder(view)
    }

    /* To update the recycler and add a new alarm, this is the code that will be called again by the
    "notifyItemInserted" to go through adapter code again and make change as necessary.
     */
    override fun onBindViewHolder(holder: AlarmItemViewHolder, position: Int) {
        val alarm = getItemId(position)
    }

    fun addNewAlarm(alarm: Alarm) {
        alarms.add(alarm)
        this.notifyItemInserted(alarms.size - 1)
    }

    override fun getItemCount() = alarms.count()
}
