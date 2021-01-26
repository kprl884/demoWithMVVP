package com.example.hextechgreen.service

import com.example.hextechgreen.model.Pin
import retrofit2.http.GET

interface PinAPI {
    //GET
    //https://app.hextechgreen.com/mini/test
    //Base URL = https://app.hextechgreen.com/
    //Extension -> mini/test
    @GET("mini/test")
    suspend fun getPins(): Pin

}