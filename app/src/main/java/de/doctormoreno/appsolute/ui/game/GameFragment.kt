package de.doctormoreno.appsolute.ui.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.data.enums.Instrument
import de.doctormoreno.appsolute.FabDelegate
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()

    private lateinit var binding: FragmentGameBinding

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
        binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        if (mainViewModel.instrumentSelected.value == null) {
            mainViewModel.selectInstrument(Instrument.PIANO)
        }
        binding.btnStart.setOnClickListener {
            findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameFragmentFirst())
        }
    }

    override fun onStart() {
        super.onStart()
        fabDelegate.setFabFilterAlpha(1f)
        fabDelegate.showModeFabs(false)
    }

    private fun initializeView() {
        binding.ivBackgroundGame.load(R.drawable.background_overlay_game)
    }

    companion object {
        private val TAG = GameFragment::class.simpleName
    }
}