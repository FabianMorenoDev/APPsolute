package de.doctormoreno.appsolute

import android.animation.ObjectAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.animation.doOnEnd
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.NavigationUiSaveStateControl
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.navigation.findNavController
import de.doctormoreno.appsolute.databinding.ActivityMainBinding
import de.doctormoreno.appsolute.ui.ambient.AmbientFragmentHearDirections
import de.doctormoreno.appsolute.ui.ambient.AmbientFragmentPlayDirections
import de.doctormoreno.appsolute.ui.components.BottomSheetFragment


interface FabDelegate {
    fun setFabFilterAlpha(alpha: Float)
    fun toggleFabFilter(enabled: Boolean)
    fun showModeFabs(show: Boolean)
}

class MainActivity : AppCompatActivity(), FabDelegate {

    private lateinit var binding: ActivityMainBinding

    private val filterBottomSheet = BottomSheetFragment()

    private var fabFilterAlpha = 0f

    private var closingToastLastShown = 0L

    private lateinit var navController: NavController

    private lateinit var navDirections: String

    @OptIn(NavigationUiSaveStateControl::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_fragment)
        NavigationUI.setupWithNavController(binding.navigationRail, navController, false)
        setOnClickListeners()
        handleBackButton()
    }

    private fun setOnClickListeners() {
        binding.fabFilter.setOnClickListener {
            filterBottomSheet.show(supportFragmentManager, null)
            toggleFabFilter(false)
        }
        binding.fabPlay.setOnClickListener {
            it.alpha = ALPHA_SELECTED
            it.isClickable = false
            binding.fabHear.alpha = ALPHA_NOT_SELECTED
            binding.fabHear.isClickable = true
            navController.navigate(AmbientFragmentHearDirections.actionAmbientFragmentHearToAmbientFragmentPlay())
        }
        binding.fabHear.setOnClickListener {
            it.alpha = ALPHA_SELECTED
            it.isClickable = false
            binding.fabPlay.alpha = ALPHA_NOT_SELECTED
            binding.fabPlay.isClickable = true
            navController.navigate(AmbientFragmentPlayDirections.actionAmbientFragmentPlayToAmbientFragmentHear())
        }
    }

    override fun setFabFilterAlpha(alpha: Float) {
        if (fabFilterAlpha == alpha) return else fabFilterAlpha = alpha
        if (fabFilterAlpha != 0f) toggleFabFilter(true)
        val animator =
            ObjectAnimator.ofFloat(binding.fabFilter, FloatingActionButton.ALPHA, fabFilterAlpha)
        animator.duration = 1000
        animator.doOnEnd { if (fabFilterAlpha == 0f) toggleFabFilter(false) }
        animator.start()
    }

    override fun toggleFabFilter(enabled: Boolean) {
        if (enabled)
            binding.fabFilter.visibility = View.VISIBLE
        else
            binding.fabFilter.visibility = View.GONE
    }

    override fun showModeFabs(show: Boolean) {
        if (show) {
            binding.fabPlay.alpha = ALPHA_NOT_SELECTED
            binding.fabHear.alpha = ALPHA_SELECTED
            binding.fabHear.isClickable = false
            binding.fabPlay.isClickable = true
        }
        showFab(binding.fabPlay, show, -dpToPx(56f + 8f))
        showFab(binding.fabHear, show, -dpToPx(56f + 8f + 56f + 8f))
    }

    private fun handleBackButton() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val currentTime = System.currentTimeMillis()
                if (navController.backQueue.size > 2) {
                    navController.navigateUp()
                } else if (currentTime > closingToastLastShown + 2000) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.confirm_close_app),
                        Toast.LENGTH_SHORT
                    ).show()
                    closingToastLastShown = currentTime
                } else {
                    finishAndRemoveTask()
                }
            }
        })
    }

    private fun showFab(fab: FloatingActionButton, show: Boolean, offsetX: Float) {
        if (fab.visibility == View.GONE && !show) return
        if (fab.visibility == View.VISIBLE && show) return
        if (show) {
            fab.visibility = View.VISIBLE
            ObjectAnimator.ofFloat(fab, FloatingActionButton.TRANSLATION_X, offsetX).apply {
                duration = 500
                start()
            }
        } else {
            ObjectAnimator.ofFloat(fab, FloatingActionButton.TRANSLATION_X, 0f).apply {
                duration = 300
                doOnEnd { fab.visibility = View.GONE }
                start()
            }
        }
    }

    private fun dpToPx(dp: Float): Float {
        return dp * resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT
    }

    companion object {
        private val TAG = MainActivity::class.simpleName
    }
}