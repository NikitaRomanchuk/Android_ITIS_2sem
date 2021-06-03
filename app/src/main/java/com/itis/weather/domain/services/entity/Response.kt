package com.itis.weather.domain.services.entity

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("status") val status: String,
    @SerializedName("data") val results: T
)

data class ErrorData(
    val cod: String,
    val message: String
)