package de.doctormoreno.appsolute.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import de.doctormoreno.appsolute.FabDelegate
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.audio.playAmbientAudio
import de.doctormoreno.appsolute.audio.stopAmbientAudio
import de.doctormoreno.appsolute.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private lateinit var binding: FragmentSettingsBinding

    private lateinit var fabDelegate: FabDelegate

    private var testAudioPlaying = false

    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
        fabDelegate = context as FabDelegate

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        fabDelegate.setFabFilterAlpha(1f)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPlayTestSound.setOnClickListener {
            testAudioPlaying = if (testAudioPlaying) {
                (it as Button).text = "PLAY EQ CALIBRATION TEST SOUND"
                stopAmbientAudio()
                false
            } else {
                (it as Button).text = "STOP EQ CALIBRATION TEST SOUND"
                playAmbientAudio(R.raw.test_calibration_sound,context)
                true
            }
        }
    }

    companion object {
        private val TAG = SettingsFragment::class.simpleName
    }
}