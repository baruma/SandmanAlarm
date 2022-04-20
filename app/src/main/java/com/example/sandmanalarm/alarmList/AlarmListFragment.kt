package com.example.sandmanalarm.alarmList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.example.sandmanalarm.databinding.AlarmListFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlarmListFragment : Fragment() {

    private val alarmListViewModel: AlarmListViewModel by viewModel()
    private var _binding: AlarmListFragmentBinding? = null
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: AlarmListAdapter

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        alarmListViewModel=
//            ViewModelProvider(this)[AlarmListViewModel::class.java]

        _binding = AlarmListFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.alertsRecyclerView.layoutManager = linearLayoutManager

        // This is where the adapter hookup code is.
        adapter = AlarmListAdapter(alarmListViewModel.alarms)
        binding.alertsRecyclerView.adapter = adapter

        alarmListViewModel.text.observe(viewLifecycleOwner, Observer {
        })

        return root
    }

//    private fun setupRecyclerView() {
//        val adapter = RemindersListAdapter {
//            Log.d("EGGS",  it.title.toString())
//            // NAVIGATION CODE HERE
//        binding.reminderssRecyclerView.setup(adapter)
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}