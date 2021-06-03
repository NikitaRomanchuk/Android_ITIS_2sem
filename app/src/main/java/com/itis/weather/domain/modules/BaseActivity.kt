package com.itis.weather.domain.modules

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class BaseActivity : AppCompatActivity() {

	inline fun <T : ViewBinding> AppCompatActivity.viewBinding(
		crossinline bindingInflater: (LayoutInflater) -> T) =
		lazy(LazyThreadSafetyMode.NONE) {
			bindingInflater.invoke(layoutInflater)
		}

	fun <T : Any, L : LiveData<T>> LifecycleOwner.observe(liveData: L, body: (T?) -> Unit) {
        liveData.observe(this, Observer(body))
    }
}