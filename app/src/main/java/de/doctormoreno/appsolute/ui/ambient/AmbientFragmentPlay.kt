package de.doctormoreno.appsolute.ui.ambient

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.data.enums.Ambient
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.audio.playAmbientAudio
import de.doctormoreno.appsolute.audio.stopAmbientAudio
import de.doctormoreno.appsolute.databinding.FragmentAmbientPlayBinding

class AmbientFragmentPlay : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val ambientViewModel: AmbientViewModel by activityViewModels()

    private lateinit var binding: FragmentAmbientPlayBinding

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
        binding = FragmentAmbientPlayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    override fun onPause() {
        super.onPause()
        stopAmbientAudio()
    }

    override fun onResume() {
        super.onResume()
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

    private fun initializeView() {

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
        private val TAG = AmbientFragmentPlay::class.simpleName
    }
}