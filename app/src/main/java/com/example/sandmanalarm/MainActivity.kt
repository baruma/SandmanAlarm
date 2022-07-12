package com.example.sandmanalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.sandmanalarm.data.data_alarm.AlarmDatabase
import com.example.sandmanalarm.data.data_alarm.AlarmRepository
import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import com.example.sandmanalarm.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.android.ext.android.inject


class MainActivity() : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val scheduler: AlarmScheduler by inject()
    private val repository: AlarmRepository by inject()

    class MinuteTickReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            EventBus.publishEvent(MinuteTickEvent)
            Log.d("wtf", "Broadcast Hit")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(MinuteTickReceiver(), IntentFilter(IntentFilter(Intent.ACTION_TIME_TICK)))

//        alarmScheduler = AlarmScheduler(this, repository)

        val navView: BottomNavigationView = binding.navView

//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        val navController = navHostFragment.navController
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.alarm_list, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        callAlarmScheduler()
    }

    /*
    Originally I intended to compare alarmStart and alarmEnd strings to each other to determine whether or not
    to trigger an Alarm.  Instead, I have chosen to compare number to number instead.

    Comparing string to string may be more volatile.
     */

    fun callAlarmScheduler() {
        scheduler.scheduleExact5SFromNow()

    }
}