package com.example.sandmanalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.util.Log
import android.widget.Toast
import java.util.*


class AlarmScheduler(val context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    private lateinit var alarmIntent: PendingIntent
    val mediaPlayer = MediaPlayer()

    // This might have to go to the AlarmBroadcastReceiver
    init {
        val receiver = ComponentName(context, AlarmBroadcastReceiver::class.java)

        context.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    fun scheduleExact5SFromNow() {
        // Set the alarm to start at 8:30 a.m.
        val calendar: Calendar = Calendar.getInstance().apply {
            add(Calendar.SECOND, 5)
        }

        val alarmMillis = calendar.timeInMillis

        val alarmIntent = Intent(context, AlarmBroadcastReceiver::class.java).let { intent ->
            PendingIntent.getBroadcast(context, 54321, intent, PendingIntent.FLAG_MUTABLE)}

        alarmManager?.set(AlarmManager.RTC_WAKEUP, alarmMillis, alarmIntent)
        Log.d("screaming", "Scheduled alarm")
        Toast.makeText(context, "I AM Schedule TOAST", Toast.LENGTH_LONG).show()

    }

//// setRepeating() lets you specify a precise custom interval--in this case,
//// 20 minutes.
//    fun setRepeatingAlarm() {
//    alarmManager?.setRepeating(
//        AlarmManager.RTC_WAKEUP,
//        calendar.timeInMillis,
//        (1000 * 60 * 20).toLong(),
//        alarmIntent
//    )
//    }
}

interface AlarmSoundManager {
    fun playAlarm()

    fun stopAlarm()
}

//class MediaPlayerAlarmSoundManager(private val context: Context) : AlarmSoundManager {
//
////    var alarmPendingIntent = PendingIntent.getBroadcast(context, alarmId, intent, 0)
//
//    val mMediaPlayer = MediaPlayer()
//
//    override fun playAlarm() {
//        val alert: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
//        mMediaPlayer.setDataSource(context, alert)
//        mMediaPlayer.prepare()
//        mMediaPlayer.start()
//        mMediaPlayer.isLooping = true
//    }
//
//    override fun stopAlarm() {
//        mMediaPlayer.stop()
//    }
//
//}

// Need an Alarm Manager that shoots out Broadcasts and a Broadcast Receiver that intercepts and does something with that signal.
//


// You can use repositories to sub servers and provide dummy data when you're coding somewhere without wifi.
// You can also use the repository pattern to test different ways of coding a problem but are unsure of how to do it, but want to be quick about it.
//data class Person(val name: String)
//
//interface PersonRepository {
//
//    fun getAllPersons(): List<Person>
//
//    fun addPerson(person: Person)
//
//}
//
//class FakePersonRepository : PersonRepository {
//
//    val people = mutableListOf<Person>()
//
//    init {
//        people.add(Person("John"))
//        people.add(Person("Liana"))
//        // ...
//
//    }
//    override fun getAllPersons(): List<Person> {
//        return people
//    }
//
//    override fun addPerson(person: Person) {
//        people.add(person)
//    }
//
//}

//class PersonDBRepository: PersonRepository {
//    override fun getAllPersons(): List<Person> {
//        //return db.persondao.getAll()
//    }
//
//    override fun addPerson(person: Person) {
//        //return db.persondao.insert(person)
//    }
//
//}
//
//class PersonFromServerRepository: PersonRepository {
//    override fun getAllPersons(): List<Person> {
//        // talk to server, map from json, return list of person
//    }
//
//    override fun addPerson(person: Person) {
//        // talk to server, save person to server
//    }
//
//}