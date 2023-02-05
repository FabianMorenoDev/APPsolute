package de.doctormoreno.appsolute.ui.toys

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.animation.doOnEnd
import de.doctormoreno.appsolute.data.models.ToyPosition

private const val TAG = "ViewAnimator"

fun animateView(
    containerWidth: Number,
    containerHeight: Number,
    view: View,
    toyPosition: ToyPosition,
    delay: Number,
    doOnEnd: (View) -> Unit = { },
    interpolator: TimeInterpolator = LinearInterpolator()
) {
    changeViewSize(
        containerWidth,
        containerHeight,
        view,
        toyPosition.scale,
        delay,
        toyPosition.animationDuration,
        doOnEnd,
        interpolator
    )
    moveViewToPosition(
        containerWidth,
        containerHeight,
        view,
        toyPosition.x,
        toyPosition.y,
        delay,
        toyPosition.animationDuration,
        doOnEnd,
        interpolator
    )
    rotateView(
        view,
        toyPosition.rotation,
        delay,
        toyPosition.animationDuration,
        doOnEnd,
        interpolator
    )
}

fun moveViewToPosition(
    containerWidth: Number,
    containerHeight: Number,
    view: View,
    x: Number,
    y: Number,
    delay: Number = 0,
    duration: Number = 0,
    doOnEnd: (View) -> Unit = { },
    interpolator: TimeInterpolator = LinearInterpolator()
) {
    view.post {
        val translatorX =
            ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_X,
                containerWidth.toFloat() * x.toFloat() - view.width / 2
            )
        translatorX.startDelay = delay.toLong()
        translatorX.duration = duration.toLong()
        translatorX.doOnEnd { doOnEnd(view) }
        translatorX.interpolator = interpolator
        translatorX.start()

        val translatorY =
            ObjectAnimator.ofFloat(
                view,
                View.TRANSLATION_Y,
                containerHeight.toFloat() * y.toFloat() - view.height / 2
            )
        translatorY.startDelay = delay.toLong()
        translatorY.duration = duration.toLong()
        translatorY.doOnEnd { doOnEnd(view) }
        translatorY.interpolator = interpolator
        translatorY.start()
    }
}

fun changeViewSize(
    containerWidth: Number,
    containerHeight: Number,
    view: View,
    scale: Number,
    delay: Number = 0,
    duration: Number = 0,
    doOnEnd: (View) -> Unit = { },
    interpolator: TimeInterpolator = LinearInterpolator()
) {
    // Adjust for different screen sizes, base: Pixel 5
    val scaleXAdj = scale.toFloat() * containerWidth.toFloat() / 2010f

    val scalerX = ObjectAnimator.ofFloat(view, View.SCALE_X, scaleXAdj)
    scalerX.startDelay = delay.toLong()
    scalerX.duration = duration.toLong()
    scalerX.doOnEnd { doOnEnd(view) }
    scalerX.interpolator = interpolator
    scalerX.start()

    // Adjust for different screen sizes, base: Pixel 5
    val scaleYAdj = scale.toFloat() * containerHeight.toFloat() / 1014f

    val scalerY = ObjectAnimator.ofFloat(view, View.SCALE_Y, scaleYAdj)
    scalerY.startDelay = delay.toLong()
    scalerY.duration = duration.toLong()
    scalerY.doOnEnd { doOnEnd(view) }
    scalerY.interpolator = interpolator
    scalerY.start()
}

fun rotateView(
    view: View,
    rotation: Number,
    delay: Number = 0,
    duration: Number = 0,
    doOnEnd: (View) -> Unit = { },
    interpolator: TimeInterpolator = LinearInterpolator()
) {
    val rotator = ObjectAnimator.ofFloat(view, View.ROTATION, rotation.toFloat())
    rotator.startDelay = delay.toLong()
    rotator.duration = duration.toLong()
    rotator.doOnEnd { doOnEnd(view) }
    rotator.interpolator = interpolator
    rotator.start()
}
