package com.example.hextechgreen.model

import com.google.gson.annotations.SerializedName


data class Pin(
    @SerializedName("p1")
    val isLightPinOne: Boolean,
    @SerializedName("p2")
    val isLightPinTwo: Boolean,
    @SerializedName("p3")
    val isLightPinThree: Boolean,
    @SerializedName("p4")
    val isLightPinFour: Boolean
    )