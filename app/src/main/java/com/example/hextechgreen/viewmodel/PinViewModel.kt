package com.example.hextechgreen.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hextechgreen.model.Pin
import com.example.hextechgreen.service.PinAPIService
import kotlinx.coroutines.*


class PinViewModel : ViewModel() {
    private val pinAPIService = PinAPIService()
    var mutablePin = MutableLiveData<Pin>()


    fun getDataFromAPI() {
        Log.d("alp","viewmodel içinde")
        pinAPIService.getData()
        Log.d("alp","murablePin = ${mutablePin.value }")
    }
    
}