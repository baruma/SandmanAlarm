package com.example.sandmanalarm.alarmList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sandmanalarm.data.domainModels.Alarm
import com.example.sandmanalarm.data.domainModels.Day
import com.example.sandmanalarm.databinding.AlarmListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmListFragment : Fragment() {

    private val alarmListViewModel: AlarmListViewModel by viewModel()
    private var _binding: AlarmListFragmentBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: AlarmListAdapter

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

        // Adapter Hook-Up
        adapter = AlarmListAdapter()
        binding.alertsRecyclerView.adapter = adapter

        // Observers
        val newAlarmObserver = Observer<Alarm> { alarm ->
            // Called to update the viewholder. Viewholder code is handled by the Adapter.
            adapter.addNewAlarm(alarm)
        }
        // Observer Connections to ViewModel
        viewModel.newAlarm.observe(viewLifecycleOwner, newAlarmObserver)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addAlarmButton.setOnClickListener() {
            addAlarm()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun addAlarm() {
        viewModel.saveAlarm(Alarm(0.0f, 3, Day.MONDAY, false, false, false))
    }

    private fun deleteAlarm(alarm: Alarm) {
        viewModel.deleteAlarm()
    }
}