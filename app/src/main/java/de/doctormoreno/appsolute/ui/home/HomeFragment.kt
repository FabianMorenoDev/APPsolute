package de.doctormoreno.appsolute.ui.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import de.doctormoreno.appsolute.*
import de.doctormoreno.appsolute.data.enums.Instrument
import de.doctormoreno.appsolute.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding

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
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        setUpClickListeners()
        setUpObservers()
    }

    override fun onStart() {
        super.onStart()
        fabDelegate.setFabFilterAlpha(1f)
        fabDelegate.showModeFabs(false)
    }

    private fun setUpClickListeners() {
        val navController = findNavController()
        binding.btnAbout.setOnClickListener { navController.navigate(HomeFragmentDirections.actionHomeFragmentToAboutFragment()) }
        binding.btnSettings.setOnClickListener { navController.navigate(HomeFragmentDirections.actionHomeFragmentToSettingsFragment()) }
        binding.mcvInstrument0.setOnClickListener { mainViewModel.selectInstrument(Instrument.PIANO) }
        binding.mcvInstrument1.setOnClickListener { mainViewModel.selectInstrument(Instrument.GUITAR) }
        binding.mcvInstrument2.setOnClickListener { mainViewModel.selectInstrument(Instrument.STRINGS) }
        binding.mcvInstrument3.setOnClickListener { mainViewModel.selectInstrument(Instrument.BRASS) }
    }

    private fun setUpObservers() {
        mainViewModel.instrumentSelected.observe(viewLifecycleOwner) {
            binding.mcvInstrument0.alpha = ALPHA_NOT_SELECTED
            binding.mcvInstrument1.alpha = ALPHA_NOT_SELECTED
            binding.mcvInstrument2.alpha = ALPHA_NOT_SELECTED
            binding.mcvInstrument3.alpha = ALPHA_NOT_SELECTED
            when (it) {
                Instrument.PIANO -> binding.mcvInstrument0.alpha = ALPHA_SELECTED
                Instrument.GUITAR -> binding.mcvInstrument1.alpha = ALPHA_SELECTED
                Instrument.STRINGS -> binding.mcvInstrument2.alpha = ALPHA_SELECTED
                Instrument.BRASS -> binding.mcvInstrument3.alpha = ALPHA_SELECTED
                null -> {}
            }
        }
    }

    private fun initializeView() {
        binding.ivBackgroundHome.load(R.drawable.background_overlay_home)
        binding.ivMozart.load(R.drawable.mozart_round)
        binding.ivInstrument0.load(R.drawable.instrument_piano)
        binding.ivInstrument1.load(R.drawable.instrument_guitar)
        binding.ivInstrument2.load(R.drawable.instrument_violin)
        binding.ivInstrument3.load(R.drawable.instrument_trumpet)
    }

    companion object {
        private val TAG = HomeFragment::class.simpleName
    }
}