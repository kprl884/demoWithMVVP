package com.example.hextechgreen.service

import com.example.hextechgreen.utils.Constants.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class PinAPIService {

    companion object {
        private val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()

        val retrofitInstance: PinAPI by lazy {
            retrofit.create(PinAPI::class.java)
        }
    }
}


