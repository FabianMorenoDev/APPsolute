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
import de.doctormoreno.appsolute.FabDelegate
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.data.enums.Ambient
import de.doctormoreno.appsolute.data.enums.Instrument
import de.doctormoreno.appsolute.databinding.FragmentAmbientBinding
import androidx.navigation.fragment.findNavController



class AmbientFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val ambientViewModel: AmbientViewModel by activityViewModels()

    private lateinit var binding: FragmentAmbientBinding

    private lateinit var fabDelegate: FabDelegate

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fabDelegate = context as FabDelegate
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAmbientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (mainViewModel.instrumentSelected.value == null) {
            mainViewModel.selectInstrument(Instrument.PIANO)
        }
        initializeView()
        setUpClickListeners()
    }

    override fun onStart() {
        super.onStart()
        fabDelegate.setFabFilterAlpha(1f)
        fabDelegate.showModeFabs(false)
    }

    private fun setUpClickListeners() {
        val navController = findNavController()
        binding.mcvAmbient0.setOnClickListener {
            fabDelegate.showModeFabs(true)
            navController.navigate(AmbientFragmentDirections.actionAmbientFragmentToAmbientFragmentHear())
            ambientViewModel.selectAmbient(Ambient.FOREST)
        }
        binding.mcvAmbient1.setOnClickListener {
            fabDelegate.showModeFabs(true)
            navController.navigate(AmbientFragmentDirections.actionAmbientFragmentToAmbientFragmentHear())
            ambientViewModel.selectAmbient(Ambient.CITY)
        }
        binding.mcvAmbient2.setOnClickListener {
            fabDelegate.showModeFabs(true)
            navController.navigate(AmbientFragmentDirections.actionAmbientFragmentToAmbientFragmentHear())
            ambientViewModel.selectAmbient(Ambient.NIGHT)
        }
        binding.mcvAmbient3.setOnClickListener {
            fabDelegate.showModeFabs(true)
            navController.navigate(AmbientFragmentDirections.actionAmbientFragmentToAmbientFragmentHear())
            ambientViewModel.selectAmbient(Ambient.OCEAN)
        }
        binding.mcvAmbient4.setOnClickListener {
            fabDelegate.showModeFabs(true)
            navController.navigate(AmbientFragmentDirections.actionAmbientFragmentToAmbientFragmentHear())
            ambientViewModel.selectAmbient(Ambient.RIVER)
        }
        binding.mcvAmbient5.setOnClickListener {
            fabDelegate.showModeFabs(true)
            navController.navigate(AmbientFragmentDirections.actionAmbientFragmentToAmbientFragmentHear())
            ambientViewModel.selectAmbient(Ambient.BEES)
        }

    }

    private fun initializeView() {
        binding.ivAmbient0.load(R.drawable.overlay_forest_small)
        binding.ivAmbient1.load(R.drawable.overlay_city_small)
        binding.ivAmbient2.load(R.drawable.overlay_night_small)
        binding.ivAmbient3.load(R.drawable.overlay_ocean_small)
        binding.ivAmbient4.load(R.drawable.overlay_river_small)
        binding.ivAmbient5.load(R.drawable.overlay_bees_small)
    }

    companion object {
        private val TAG = AmbientFragment::class.simpleName
    }
}