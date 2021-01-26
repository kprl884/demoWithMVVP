package com.example.hextechgreen.view

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.hextechgreen.R
import com.example.hextechgreen.databinding.FragmentPinBinding
import com.example.hextechgreen.model.Pin
import com.example.hextechgreen.utils.Constants.Companion.delay_time
import com.example.hextechgreen.viewmodel.PinViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PinFragment : Fragment() {
    private lateinit var binding: FragmentPinBinding
    private lateinit var imageViewList: Array<ImageView>
    private lateinit var viewModel: PinViewModel
    private lateinit var pinFromService: Pin
    private var listOfPin: ArrayList<Boolean> = ArrayList()
    private val pinNumberOfModel: Int = 4
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPinBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageViewList = arrayOf(
            binding.pin1Iv,
            binding.pin2Iv,
            binding.pin3Iv,
            binding.pin4Iv,
        )
        viewModel = ViewModelProvider(this).get(PinViewModel::class.java)
        viewModel.getDataFromAPI()
        observerLiveData()
    }

    private fun observerLiveData() {
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getDataFromAPI()
        }

        viewModel.mutablePin.observe(viewLifecycleOwner, {
            it?.let {
                var countStartIndexOfArray = 0
                var countEndIndexOfArray = pinNumberOfModel
                pinFromService = it // get data with service like lively
                //initialize pin lights
                Log.d("alp"," in viewModel in main  it = ${it}")
                Log.d("alp"," in viewModel in main  pin fromService = ${pinFromService}")
                listOfPin.add(pinFromService.p1)
                listOfPin.add(pinFromService.p2)
                listOfPin.add(pinFromService.p3)
                listOfPin.add(pinFromService.p4)

                Log.d("alp"," in viewModel in main listOfPin = ${listOfPin}")

                //delay time calculate for each (size / pinNumberOfModel) time onTick will start
                object :
                    CountDownTimer(
                        ((listOfPin.size / pinNumberOfModel) * delay_time).toLong(),
                        delay_time.toLong()
                    ) {
                    override fun onFinish() {
                    }

                    override fun onTick(millisUntilFinished: Long) {
                        /*
                            each 15 second function called and corresponding pins passed argument
                            then start end indexes will increment by pinNumberOfModel
                         */

                        lightPin(listOfPin, countStartIndexOfArray, countEndIndexOfArray)
                        countEndIndexOfArray += pinNumberOfModel
                        countStartIndexOfArray += pinNumberOfModel
                    }
                }.start()
            }
        })
    }

    private fun lightPin(listOfPin: ArrayList<Boolean>, startIndex: Int, endIndex: Int) {
        //loop and light if true signal for pin
        //when called the function between start and end indexes pin  values check then pin will design 'on' or 'off'
        Log.d("alp"," in function listOfPin = ${listOfPin}")
        Log.d("alp"," in function startIndex = ${startIndex}")
        Log.d("alp"," in function endIndex = ${endIndex}")
        var flag: Int = startIndex
        while (flag < endIndex) {
            if (listOfPin[flag]) {
                imageViewList[flag % 4].setImageResource(R.drawable.ic_baseline_toggle_on_24)
            } else if (!listOfPin[flag]) {
                imageViewList[flag % 4].setImageResource(R.drawable.ic_baseline_toggle_off_24)
            }
            flag += 1
        }
    }
}