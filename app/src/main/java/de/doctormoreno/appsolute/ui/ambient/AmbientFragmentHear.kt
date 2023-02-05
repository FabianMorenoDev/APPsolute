package de.doctormoreno.appsolute.ui.ambient

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import de.doctormoreno.appsolute.AMBIENT_NOTE_INTERVAL
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.audio.ambientResonate
import de.doctormoreno.appsolute.data.enums.Ambient
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.audio.playAmbientAudio
import de.doctormoreno.appsolute.audio.playNote
import de.doctormoreno.appsolute.audio.stopAmbientAudio
import de.doctormoreno.appsolute.databinding.FragmentAmbientHearBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AmbientFragmentHear : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val ambientViewModel: AmbientViewModel by activityViewModels()
    private lateinit var binding: FragmentAmbientHearBinding

    private var play = true

    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAmbientHearBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        mainViewModel.octaves.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                while (play) {
                    val note = it.random().notes.random()
                    val resoJob = this.launch { ambientResonate(note) }
                    resoJob.invokeOnCompletion {
                        this.launch { playNote(note,context) }
                    }
                    delay(AMBIENT_NOTE_INTERVAL)
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        stopAmbientAudio()
    }

    override fun onResume() {
        super.onResume()
        binding.vvVideo.start()
        ambientViewModel.ambientSelected.observe(viewLifecycleOwner) {
            when (it) {
                Ambient.FOREST -> {
                    playAmbientAudio(R.raw.ambient_forest,context)
                }
                Ambient.CITY -> {
                    playAmbientAudio(R.raw.ambient_city,context)
                }
                Ambient.NIGHT -> {
                    playAmbientAudio(R.raw.ambient_night,context)
                }
                Ambient.OCEAN -> {
                    playAmbientAudio(R.raw.ambient_ocean,context)
                }
                Ambient.RIVER -> {
                    playAmbientAudio(R.raw.ambient_river,context)
                }
                Ambient.BEES -> {
                    playAmbientAudio(R.raw.ambient_bees,context)
                }
                null -> {}
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        play = false
    }

    private fun initializeView() {
        val path =
            "android.resource://" + requireContext().packageName.toString() + "/" + R.raw.audio_animation
        binding.vvVideo.setVideoURI(Uri.parse(path))
        binding.vvVideo.setOnPreparedListener { vp -> vp.isLooping = true }
        ambientViewModel.ambientSelected.observe(viewLifecycleOwner) {
            when (it) {
                Ambient.FOREST -> {
                    binding.ivBackgroundAmbient.load(R.drawable.background_overlay_forest)
                }
                Ambient.CITY -> {
                    binding.ivBackgroundAmbient.load(R.drawable.background_overlay_city)
                }
                Ambient.NIGHT -> {
                    binding.ivBackgroundAmbient.load(R.drawable.background_overlay_night)
                }
                Ambient.OCEAN -> {
                    binding.ivBackgroundAmbient.load(R.drawable.background_overlay_ocean)
                }
                Ambient.RIVER -> {
                    binding.ivBackgroundAmbient.load(R.drawable.background_overlay_river)
                }
                Ambient.BEES -> {
                    binding.ivBackgroundAmbient.load(R.drawable.background_overlay_bees)
                }
                null -> {}
            }
        }
    }

    companion object {
        private val TAG = AmbientFragmentHear::class.simpleName
    }
}