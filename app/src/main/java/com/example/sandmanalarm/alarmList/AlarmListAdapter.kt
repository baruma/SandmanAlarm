package com.example.sandmanalarm.alarmList

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.sandmanalarm.R
import com.example.sandmanalarm.data.Alarm


class AlarmListAdapter(private val dataSet: MutableLiveData<Alarm>) : RecyclerView.Adapter<AlarmListAdapter.AlarmItemViewHolder>() {
    private lateinit var alarmList: ArrayList<Alarm>

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlarmItemViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.alarm_item_viewholder, viewGroup, false)

        return AlarmItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlarmItemViewHolder, position: Int) {
        val movie: Alarm = alarmList.get(position)
        // Set movie data
        holder.bind(movie)
        holder.itemView.setOnClickListener { v ->
            val expanded: Boolean = movie.isExpanded
            // Change the state
            movie.isExpanded = (!expanded)
            // Notify the adapter that item has changed
            notifyItemChanged(position)
        }
    }

    override fun getItemCount() = 10

    // Viewholders aren't views.  You are redirecting the view being passed in to register the onClick
    // the viewholder will always have a view since it's declared with a constructor that has a view
    // you can route the onclicklistener from the view to the viewholder in the init.

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

}
