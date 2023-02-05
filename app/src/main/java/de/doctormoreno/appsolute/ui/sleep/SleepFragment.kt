package de.doctormoreno.appsolute.ui.sleep

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.data.enums.Instrument
import de.doctormoreno.appsolute.FabDelegate
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.databinding.FragmentSleepBinding

class SleepFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentSleepBinding

    private lateinit var fabDelegate: FabDelegate

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
        binding = FragmentSleepBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        if (mainViewModel.instrumentSelected.value == null) {
            mainViewModel.selectInstrument(Instrument.PIANO)
        }
    }

    override fun onStart() {
        super.onStart()
        fabDelegate.setFabFilterAlpha(0f)
        fabDelegate.showModeFabs(false)
    }

    private fun initializeView() {
        binding.ivBackgroundSleep.load(R.drawable.background_overlay_sleep)
    }

    companion object {
        private val TAG = SleepFragment::class.simpleName
    }
}