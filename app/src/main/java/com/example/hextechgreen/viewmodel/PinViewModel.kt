package com.example.hextechgreen.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hextechgreen.model.Pin
import com.example.hextechgreen.service.PinAPIService
import com.example.hextechgreen.utils.Constants.Companion.TAG
import kotlinx.coroutines.launch

class PinViewModel : ViewModel() {
    private var _mutablePin = MutableLiveData<Pin>()
    val mutablePin: MutableLiveData<Pin>
        get() = _mutablePin
    fun getDataFromAPI() {
            viewModelScope.launch {
                try {
                    Log.d(
                        "alp4",
                        "PinAPIService.retrofitInstance.getPins() = ${PinAPIService.retrofitInstance.getPins()}"
                    )

                    _mutablePin.value = PinAPIService.retrofitInstance.getPins()
                    Log.d("alp4", "_mutableValue = ${_mutablePin.value}")
                } catch (e: Exception) {
                    Log.e(TAG, "${e.message}")
                }
            }
    }
}