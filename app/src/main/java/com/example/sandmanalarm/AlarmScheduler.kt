package com.example.sandmanalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.sandmanalarm.data.data_alarm.AlarmRepository
import com.example.sandmanalarm.data.schedule.ScheduleEntity
import java.util.*

/*
    I'm going to write in the Schedule Save code in here, but it may have to go in the BroadcastReceiver.
 */

class AlarmScheduler(val context: Context, val repository: AlarmRepository) {

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
    private lateinit var alarmIntent: PendingIntent


//    val repository: AlarmRepository = AlarmRepository(database)

    init {
        val receiver = ComponentName(context, AlarmBroadcastReceiver::class.java)

        context.packageManager.setComponentEnabledSetting(
            receiver,
            PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
            PackageManager.DONT_KILL_APP
        )
    }

    suspend fun scheduleAlarm() {
        val calendar: Calendar = Calendar.getInstance().apply {
            add(Calendar.SECOND, 1)
        }

        val alarmInMillis = calendar.timeInMillis
        val requestCode: Int = Random().nextInt(9999)
        val alarmIntent = Intent(context, AlarmBroadcastReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, alarmIntent, PendingIntent.FLAG_MUTABLE)

        alarmManager?.set(AlarmManager.RTC_WAKEUP, alarmInMillis, pendingIntent)

        // TODO: Save Schedule to database
        // TODO: Retrieve alarm for newly created cases and for when user makes changes to current ones.
        val alarm = repository.loadAlarms()[8]
        repository.saveSchedule(ScheduleEntity(requestCode, alarmIntent.flags.toString(), alarm.id))
    }

    fun scheduleExact5SFromNow() {
        // Set the alarm to start at 8:30 a.m.
        val calendar: Calendar = Calendar.getInstance().apply {
            add(Calendar.SECOND, 1)
        }

        val alarmMillis = calendar.timeInMillis

        val alarmIntent = Intent(context, AlarmBroadcastReceiver::class.java)
        val alarmBundle = Bundle()
        alarmBundle.putInt("hour", 5)
        alarmBundle.putInt("minute", 30)
        val requestCode = 343
        alarmBundle.putInt("request_code", requestCode)

        alarmIntent.putExtras(alarmBundle)


        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, alarmIntent, PendingIntent.FLAG_MUTABLE)

        alarmManager?.set(AlarmManager.RTC_WAKEUP, alarmMillis, pendingIntent)
        Log.d("intent", alarmIntent.toString())

        Log.d("screaming", "Scheduled alarm")
        Toast.makeText(context, "I AM Schedule TOAST", Toast.LENGTH_SHORT).show()

//        repository.saveSchedule(ScheduleEntity(pendingIntent.))
//        repository.saveSchedule(ScheduleEntity(123, alarmIntent.flag, alarmIntent.creatorUid,))

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