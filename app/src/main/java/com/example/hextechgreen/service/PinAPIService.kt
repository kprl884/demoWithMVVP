package com.example.hextechgreen.service

import android.util.Log
import com.example.hextechgreen.Constans.Companion.BASE_URL
import com.example.hextechgreen.model.Pin
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PinAPIService {
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service: PinAPI = retrofit.create(PinAPI::class.java)
    val call = service.getPins()

    fun getData(): Pin? {
        Log.d("alp","getdata içinde")
        var  pinFromAPIService: Pin? = Pin(false,false,false,false)

            call.enqueue(object : Callback<Pin> {
                override fun onResponse(call: Call<Pin>, response: Response<Pin>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            pinFromAPIService = Pin(it.isLightPinOne, it.isLightPinTwo, it.isLightPinThree, it.isLightPinFour)
                            Log.d("alp","pin = ${pinFromAPIService}")
                        }
                    }
                }
                override fun onFailure(call: Call<Pin>, t: Throwable) {
                    Log.d("alp","fail")
                    t.printStackTrace()
                }

            })

        Log.d("alp","return edilen değer retrofitten = ${pinFromAPIService}")
        return pinFromAPIService
    }
}


