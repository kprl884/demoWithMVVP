package com.example.hextechgreen.view

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.hextechgreen.Constans.Companion.delay_time
import com.example.hextechgreen.R
import com.example.hextechgreen.databinding.FragmentPinBinding
import com.example.hextechgreen.model.Pin
import com.example.hextechgreen.viewmodel.PinViewModel


class PinFragment : Fragment() {
    lateinit var binding: FragmentPinBinding
    private lateinit var imageViewList: Array<ImageView>
    private lateinit var viewModel: PinViewModel
    private lateinit var pinFromService: Pin
    private  var listOfPin : ArrayList<Boolean> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPinBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageViewList = arrayOf<ImageView>(
            binding.pin1Iv,
            binding.pin2Iv,
            binding.pin3Iv,
            binding.pin4Iv,
        )

        viewModel = ViewModelProvider(this).get(PinViewModel::class.java)
        viewModel.getDataFromAPI()

        observerLiveData()
    }

    private fun observerLiveData(){
        viewModel.mutablePin.observe(viewLifecycleOwner, Observer {
            it?.let {
                 pinFromService = it // get data with service like lively
                Log.d("alp","come it from retrofit = ${it}")

                //initialize pin lights
                 listOfPin.add(pinFromService.isLightPinOne)
                 listOfPin.add(pinFromService.isLightPinTwo)
                 listOfPin.add(pinFromService.isLightPinThree)
                 listOfPin.add(pinFromService.isLightPinFour)

                lightPin(listOfPin)
            }
        })
    }

    fun lightPin(listOfPin: ArrayList<Boolean>) {
        var count: Int = 0
        for (i in listOfPin) {
            //loop and light if true signal for pin
            if (i) {
                Log.d("alp", "i in list = ${i}")
                imageViewList[count].setImageResource(R.drawable.ic_baseline_toggle_on_24)
            } else {
                imageViewList[count].setImageResource(R.drawable.ic_baseline_toggle_off_24)
                Log.d("alp", "i in list = ${i}")
            }
            Handler().postDelayed({
                //doSomethingHere()
            }, (15000).toLong())
            //delay 15000 ms
            count += 1
        }
    }

}