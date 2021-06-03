package com.itis.weather.domain.common

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

// First extend the MutableLiveData class
// https://android.jlelse.eu/android-arch-handling-clicks-and-single-actions-in-your-view-model-with-livedata-ab93d54bc9dc
class ActionLiveData<T>(private val stopObservingOn: ((T) -> Boolean)? = null) :
    MutableLiveData<T>() {

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {

        // Being strict about the observer numbers is up to you
        // I thought it made sense to only allow one to handle the event
        if (hasObservers()) {
            throw Throwable("Only one observer at a time may subscribe to a ActionLiveData")
        }

        super.observe(owner, object : Observer<T> {
            override fun onChanged(data: T) {
                if (data == null) return
                observer.onChanged(data)
                value = null

                stopObservingOn?.let {
                    if (it.invoke(data)) removeObserver(this)
                }
            }
        })
    }

    @MainThread
    fun sendAction(data: T) {
        value = data
    }
}