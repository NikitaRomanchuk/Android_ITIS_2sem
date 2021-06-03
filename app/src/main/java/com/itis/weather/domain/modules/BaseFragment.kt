package com.itis.weather.domain.modules

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseFragment() : Fragment() {

    fun <T : Any, L : LiveData<T>> Fragment.observe(
        liveData: L,
        body: (T?) -> Unit
    ) {
        liveData.observe(viewLifecycleOwner, Observer(body))
    }

    inline fun <T : Any, L : LiveData<T>> Fragment.observeNonNull(
        liveData: L,
        crossinline body: (T) -> Unit
    ) {
        liveData.observe(viewLifecycleOwner, Observer { it?.let(body) })
    }
}