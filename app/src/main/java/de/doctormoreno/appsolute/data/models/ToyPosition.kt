package de.doctormoreno.appsolute.data.models

import android.animation.TimeInterpolator
import android.view.animation.LinearInterpolator

data class ToyPosition(
    val x: Number,
    val y: Number,
    val scale: Number,
    val rotation: Number,
    val animationDuration: Number,
    val interpolator: TimeInterpolator = LinearInterpolator()
)
