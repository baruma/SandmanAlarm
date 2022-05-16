package com.example.sandmanalarm

import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

object EventBus {

    val eventSubject = PublishSubject.create<Event>()

    fun postEvent(event: Event) {
        eventSubject.onNext(event)
    }

    fun <T : Event> observeEvent(event: Class<T>): Observable<T> {
        return eventSubject.ofType(event)
    }
}

// Normally using event busses are great, however, be wary of using them as global objects like this.
// Most devs would disapprove of this.  I need to return later and update this code, but it'll do for now.

interface Event

object MinuteTickEvent: Event
data class EditAlertEvent(val alarm: Alarm): Event