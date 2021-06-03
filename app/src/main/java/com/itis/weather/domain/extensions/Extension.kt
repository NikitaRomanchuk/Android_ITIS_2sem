package com.itis.weather.domain.extensions

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.util.*

fun Activity.checkLocationPermissionAPI28(locationRequestCode : Int) {
        val permList = arrayOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        requestPermissions(this, permList, locationRequestCode)
    }

fun Context.checkSinglePermission(permission: String) : Boolean {
    return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
}

fun Long.toDateFormat(): String {
    val date = Date(this * 1000L)
    val simpleDateFormat = SimpleDateFormat("HH:mm | dd MMM yyyy", Locale.getDefault())
    return simpleDateFormat.format(date)
}

fun Double.toCelsiusFormat(): String {
    val twoDForm = DecimalFormat("##")
    return "${java.lang.Double.valueOf(twoDForm.format(this)).toInt()} ℃"
}