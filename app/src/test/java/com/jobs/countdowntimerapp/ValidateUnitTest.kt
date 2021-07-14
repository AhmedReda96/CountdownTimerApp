package com.jobs.countdowntimerapp

import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import com.google.common.truth.Truth.assertThat
import com.jobs.countdowntimerapp.ui.start.StartFragment
import com.jobs.countdowntimerapp.viewModel.StartFragmentVM
import org.junit.Test

class ValidateUnitTest {

    @Test
    fun `empty hour returns false`() {
        val result = StartFragmentVM().validate("", "8", "9")
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid hour returns false`() {
        val result = StartFragmentVM().validate("25", "8", "9")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty minute returns false`() {
        val result = StartFragmentVM().validate("8", "", "9")
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid minute returns false`() {
        val result = StartFragmentVM().validate("8", "88", "9")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty second returns false`() {
        val result = StartFragmentVM().validate("8", "8", "")
        assertThat(result).isFalse()
    }

    @Test
    fun `invalid second returns false`() {
        val result = StartFragmentVM().validate("8", "8", "99")
        assertThat(result).isFalse()
    }
}