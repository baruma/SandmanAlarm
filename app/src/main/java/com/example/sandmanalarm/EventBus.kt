package com.example.sandmanalarm

import com.example.sandmanalarm.data.data_alarm.domainModels.Alarm
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject

object EventBus {
    val eventSubject = PublishSubject.create<Event>()

    fun publishEvent(event: Event) {
        eventSubject.onNext(event)
    }

    fun <T : Event> subscribeToEvent(event: Class<T>): Observable<T> {
        return eventSubject.ofType(event)
    }
}

// Normally using event busses are great, however, be wary of using them as global objects like this.
// Most devs would disapprove of this.  I need to return later and update this code, but it'll do for now.

interface Event

object MinuteTickEvent: Event
data class EditAlertEvent(val alarm: Alarm): Event
/*

About Event Busses

This is a subset of the Publish/Subscribe pattern.  It's a way of having something Publish a change (usually
in state) and have a Subscriber listen out for the change.  The two communicate using Events or messages.

This is a pattern that helps communicate between two distant pieces within your app that helps without
strongly coupling them.  You can communicate between Activities, Services, Kotlin objects, etc. Without mapping,
serializing, deserializing, etc. (these things would create coupling between components, and a more cluttered pipeline).

You can create your own Eventbus like here, or you can use RXJava or EventBus (a dependency).
 */