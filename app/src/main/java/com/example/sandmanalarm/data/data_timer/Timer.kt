import com.example.sandmanalarm.data.data_timer.AlertMethod

data class Timer(
    val id: Long,
    val time: Long,
    val running: Boolean,
//    val alertMethod: AlertMethod,
    val timeRemaining: Long
)

//val id: Long,
//// should probably have a sleepTime
//val wakeUpTime: Float,
////    val wakeUpMethod: WakeUpMethod,
//val hoursOfSleep: Int,
//val days: Day,
//var isExpanded: Boolean,
//val vibration: Boolean,
//val sound: Boolean