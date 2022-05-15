package com.example.sandmanalarm.alarm

import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sandmanalarm.IdGenerator
import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import com.example.sandmanalarm.data.data_alarm.domainModels.Day
import com.example.sandmanalarm.databinding.AlarmListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmListFragment : Fragment() {

    private val alarmListViewModel: AlarmListViewModel by viewModel()
    private var _binding: AlarmListFragmentBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerAdapter: AlarmListRecyclerAdapter
    private var mItemTouchHelper: ItemTouchHelper? = null

    var hour = 0
    var minute = 0

    private val binding get() = _binding!!
    val viewModel: AlarmListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = AlarmListFragmentBinding.inflate(inflater, container, false)

        val root: View = binding.root

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.alertsRecyclerView.layoutManager = linearLayoutManager

        recyclerAdapter = AlarmListRecyclerAdapter {}  // Null
        binding.alertsRecyclerView.adapter = recyclerAdapter // No longer Null

        loadAlarms()
        setUpSwipeToDelete()

        // Observer
        val newAlarmObserver = Observer<Alarm> { alarm ->
            // Code to update the UI
            recyclerAdapter.addNewAlarm(alarm)
        }
        // Observe the LiveData, passing in this activity as the LifecycleOwner and the observer.
        viewModel.newAlarmLiveData.observe(viewLifecycleOwner, newAlarmObserver)

        val alarmRemovedListener = Observer<Alarm> { alarm ->
            deleteAlarm(alarm)
        }
        recyclerAdapter.alarmRemovedLiveData.observe(viewLifecycleOwner, alarmRemovedListener)

        val loadAlarmListener = Observer<List<Alarm>> { alarms ->
            recyclerAdapter.updateAlarms(alarms)
        }
        viewModel.alarmLiveDataList.observe(viewLifecycleOwner, loadAlarmListener)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addAlarmButton.setOnClickListener() {
            //addAlarm()
            popTimePicker(view)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addAlarm(targetHour: Int, targetMinute: Int) {
        viewModel.saveAlarm(Alarm(IdGenerator.create(), targetHour, targetMinute, Day.MONDAY, false, false, false))
    }

    private fun deleteAlarm(alarm: Alarm) {
        viewModel.deleteAlarm(alarm)
    }

    private fun setUpSwipeToDelete() {
        binding.alertsRecyclerView.adapter = recyclerAdapter
        val callback: ItemTouchHelper.Callback = SwipeHelperCallback(recyclerAdapter)
        mItemTouchHelper = ItemTouchHelper(callback)
        mItemTouchHelper?.attachToRecyclerView(binding.alertsRecyclerView)
    }

    private fun loadAlarms() {
        viewModel.loadAlarms()
    }

    private fun popTimePicker(view: View?) {
        val onTimeSetListener =
            OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                addAlarm(selectedHour, selectedMinute)
            }

        val timePickerDialog =
            TimePickerDialog(context,  /*style,*/onTimeSetListener, hour, minute, true)
        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }

}