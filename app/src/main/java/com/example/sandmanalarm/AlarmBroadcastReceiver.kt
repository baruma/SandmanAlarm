package com.example.sandmanalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.widget.Toast


/*
Can be registered dynamically, but for now, is registered statically in the AndroidManifest.

Is responsible for handling Alarms (sounds and vibrations) and Notifications.

The flow is UI -> AlarmScheduler -> AlarmBroadcastReceiver
*/

class AlarmBroadcastReceiver : BroadcastReceiver() {
    val mediaPlayer = MediaPlayer()

    override fun onReceive(context: Context, intent: Intent) {
        // Is triggered when alarm goes off, i.e. receiving a system broadcast
//        Log.d("screaming", "alarm broacast receiver")
//        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
////            val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
////            vibrator.vibrate(VibrationEffect.createPredefined(10))
//            val alert: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
//            mediaPlayer.setDataSource(context, alert)
//            mediaPlayer.prepare()
//            mediaPlayer.start()
//            mediaPlayer.isLooping = true
//        }

        Log.d("screaming", "Got an alarm")
        val hour = intent.getIntExtra("hour", 0)
        val minute = intent.getIntExtra("minute", 0)
        val requestCode = intent.getIntExtra("request_code", 0)

        Log.d("screaming", "Got an alarm for $hour $minute")
        Toast.makeText(context, "I AM Broadcast TOAST", Toast.LENGTH_SHORT).show()
    }

//    fun onPause() {
//        super.onPause()
//        unregisterReceiver(receiver)
//    }

}

//override fun playAlarm() {
////        alarmIntent = Intent(context, AlarmBroadcastReceiver::class.java).let { intent ->
////            PendingIntent.getBroadcast(context, 0, intent, 0)
////        }
//
//
////        val alert: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
////        mediaPlayer.setDataSource(context, alert)
////        mediaPlayer.prepare()
////        mediaPlayer.start()
////        mediaPlayer.isLooping = true
//}
//
//override fun stopAlarm() {
//    mediaPlayer.stop()
//}
