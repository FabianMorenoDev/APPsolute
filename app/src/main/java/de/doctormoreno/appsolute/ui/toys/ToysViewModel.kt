package de.doctormoreno.appsolute.ui.toys

import androidx.lifecycle.ViewModel

class ToysViewModel : ViewModel() {

    private var isAnimating = false

    fun setIsAnimating(animating: Boolean) {
        isAnimating = animating
    }

    fun isAnimating(): Boolean {
        return isAnimating
    }

    companion object {
        private val TAG = ToysViewModel::class.simpleName
    }
}