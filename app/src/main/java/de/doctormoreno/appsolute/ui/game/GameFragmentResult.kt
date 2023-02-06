package de.doctormoreno.appsolute.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import coil.load
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.databinding.FragmentGameResultBinding
import kotlin.math.round

class GameFragmentResult : Fragment() {

    private lateinit var binding: FragmentGameResultBinding
    private val gameViewModel: GameViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameResultBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
    }

    private fun initializeView() {
        binding.ivBackgroundGame.load(R.drawable.background_overlay_game)
        var correct = 0
        gameViewModel.stats.value?.forEach { answer: Boolean ->
            if (answer) {
                correct++
            }
        }
        val result = "${
            if (correct == 0) {
                "0"
            } else {
                round(100* 100.0 / (gameViewModel.stats.value?.size ?: 1) * correct)/100
            }
        }%"
        binding.tvScore.text = result
    }

    companion object {
        private val TAG = GameFragmentResult::class.simpleName
    }
}