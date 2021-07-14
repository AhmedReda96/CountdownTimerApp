package com.jobs.countdowntimerapp.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StartFragmentVM : ViewModel() {
    private val TAG = StartFragmentVM::class.java.simpleName

    public var validateMD: MutableLiveData<String> = MutableLiveData()


    fun validate(hour: String, minute: String, seconds: String): Boolean {
        var unitTestValidation: Boolean = true
        if (hour.isEmpty() || hour.toInt() > 24) {
            validateMD.value = "invalid hours"
            Log.d(TAG, "validate: invalid hours")
            unitTestValidation=false
            return unitTestValidation
        } else {
            if (minute.isEmpty() || minute.toInt() > 60) {
                validateMD.value = "invalid minute"
                Log.d(TAG, "validate: invalid minute")
                unitTestValidation=false
                return unitTestValidation
            } else {
                if (seconds.isEmpty() || seconds.toInt() > 60) {
                    validateMD.value = "invalid second"
                    Log.d(TAG, "validate: invalid second")
                    unitTestValidation=false
                    return unitTestValidation
                } else {
                    if (hour.toInt() == 0 && minute.toInt() == 0 && seconds.toInt() == 0) {
                        validateMD.value = "invalid time"
                        Log.d(TAG, "validate: invalid time")

                        unitTestValidation=false
                        return unitTestValidation
                    } else {



                        validateMD.value = "valid"
                        Log.d(TAG, "validate: valid")
                        unitTestValidation=true
                        return unitTestValidation                    }

                }

            }
        }
        return unitTestValidation
    }


}