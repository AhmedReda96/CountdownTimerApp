package com.jobs.countdowntimerapp.ui.start

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.jobs.countdowntimerapp.R
import com.jobs.countdowntimerapp.viewModel.StartFragmentVM
import kotlinx.android.synthetic.main.fragment_start.*

class StartFragment : Fragment(), View.OnClickListener {

    public lateinit var viewModel: StartFragmentVM
    private lateinit var bundle: Bundle
    private  var minute: String="00"
    private  var hour: String="00"
    private  var second: String="00"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(StartFragmentVM::class.java)
        bundle = Bundle()
        listenOnMLD()
        okBtn.setOnClickListener(this)
    }

    private fun listenOnMLD() {
        activity?.let {
            viewModel.validateMD.observe(it, Observer {
                when (it) {
                    "invalid hours" -> Toast.makeText(
                        activity,
                        R.string.invalid_hours,
                        Toast.LENGTH_LONG
                    ).show()
                    "invalid minute" -> Toast.makeText(
                        activity,
                        R.string.invalid_minute,
                        Toast.LENGTH_LONG
                    ).show()
                    "invalid second" -> Toast.makeText(
                        activity,
                        R.string.invalid_second,
                        Toast.LENGTH_LONG
                    ).show()
                    "invalid time" -> Toast.makeText(
                        activity,
                        R.string.invalid_time,
                        Toast.LENGTH_LONG
                    ).show()
                    "valid" ->
                        moveToTimerScreen()


                }


            })
        }


    }

    private fun moveToTimerScreen() {

        bundle.putString("timerValue", "$hour:$minute:$second")
        findNavController()
            .navigate(R.id.action_startFragment_to_timerFragment, bundle)
    }

    override fun onClick(v: View?) {
        if (v == okBtn) {
            hour = hourET.text.toString()
            minute = minuteET.text.toString()
            second = secondET.text.toString()
            viewModel.validate(hour, minute, second)
        }

    }


}