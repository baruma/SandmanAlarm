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
import com.example.sandmanalarm.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var _alarmScheduler: AlarmScheduler

    class MinuteTickReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            EventBus.postEvent(MinuteTickEvent)
            Log.d("wtf", "Broadcast Hit")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerReceiver(MinuteTickReceiver(), IntentFilter(IntentFilter(Intent.ACTION_TIME_TICK)))

        _alarmScheduler = AlarmScheduler(this)

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

        _alarmScheduler.scheduleExact5SFromNow()

    }

    // Get AlarmManager instance
//    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

//    // Intent
//    val intent = Intent(this, AlarmBroadcastReceiver::class.java)
//    intent.action = "FOO_ACTION"
//    intent.putExtra("KEY_FOO_STRING", "Medium AlarmManager Demo")
//
//    val pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0)
//
//    // Alarm time
//    val ALARM_DELAY_IN_SECOND = 10
//    val alarmTimeAtUTC = System.currentTimeMillis() + ALARM_DELAY_IN_SECOND * 1_000L
//
//    // Set with system Alarm Service
//    // Other possible functions: setExact() / setRepeating() / setWindow(), etc
//    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, alarmTimeAtUTC, pendingIntent)
//
//
//    val pendingIntentRequestCode = 0
//    val flag = 0
//    val pendingIntent = PendingIntent.getBroadcast(this, pendingIntentRequestCode, intent, flag)

}