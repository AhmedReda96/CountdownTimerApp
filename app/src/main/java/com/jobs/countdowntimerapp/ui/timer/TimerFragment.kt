package com.jobs.countdowntimerapp.ui.timer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.jobs.countdowntimerapp.R
import kotlinx.android.synthetic.main.fragment_timer.*
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*


class TimerFragment : Fragment() {
    private lateinit var selectedTime: String
    private lateinit var currentTime: String
    private lateinit var parentJob: Job


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val onBackPressedListener = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                parentJob.cancel()
                findNavController()
                    .navigate(R.id.action_timerFragment_to_startFragment)
            }

        }
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedListener)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_timer, container, false)

    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        runTimer()
    }

    private fun init() {
        selectedTime = arguments?.getString("timerValue").toString()

    }

    @InternalCoroutinesApi
    private fun runTimer() {
         parentJob = Job()

        val coroutineScope = CoroutineScope(Dispatchers.Main + parentJob)
        coroutineScope.launch {
            while (NonCancellable.isActive) {
                try {
                    currentTime = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(Date())
                    val format = SimpleDateFormat("hh:mm:ss")
                    val date1 = format.parse(currentTime)
                    val date2 = format.parse(selectedTime)
                    val mills = date1.time - date2.time
                    Log.v("Data1", "" + date1.time)
                    Log.v("Data2", "" + date2.time)
                    val hours = (mills / (1000 * 60 * 60)).toInt()
                    val mins = (mills / (1000 * 60)).toInt() % 60
                    val sec = (mills / (1000)).toInt() % 60

                    val diff = "$hours:$mins:$sec"


                    timerTV.text = diff
                    Log.d("TAG", "testTag: $diff")

                } catch (e: Exception) {
                    e.printStackTrace()
                }

                delay(1000L)
            }

        }


    }


    @InternalCoroutinesApi
    override fun onResume() {
        super.onResume()
        runTimer()
    }

    override fun onDestroy() {
        super.onDestroy()
        parentJob.cancel()
    }

    override fun onStop() {
        super.onStop()
        parentJob.cancel()
    }

}