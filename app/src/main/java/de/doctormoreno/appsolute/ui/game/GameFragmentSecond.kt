package de.doctormoreno.appsolute.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.databinding.FragmentGameSecondBinding
import kotlinx.coroutines.Job

class GameFragmentSecond : Fragment() {

    private lateinit var binding: FragmentGameSecondBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        binding.btnNext.setOnClickListener {
            findNavController().navigate(GameFragmentSecondDirections.actionGameFragmentSecondToGameFragmentThird())
        }
        gameViewModel.pause()
        gameViewModel.next.observe(viewLifecycleOwner) {
            if (it) {
                startPattern()
            }
        }
        binding.btnOk.setOnClickListener {
            binding.cvDescrpition.visibility = View.GONE
            gameViewModel.resume()
            gameViewModel.stats.observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    if (it.last()) {
                        binding.tvCorrect.visibility = View.VISIBLE
                    } else {
                        binding.tvTryAgain.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun startPattern() {
        try {
            mainViewModel.octaves.observe(viewLifecycleOwner) {
                binding.gameContainerView.visibility = View.GONE
                binding.tvCorrect.visibility = View.GONE
                binding.tvTryAgain.visibility = View.GONE
                var playJob: Job? = null

                playJob?.invokeOnCompletion {
                    binding.gameContainerView.visibility = View.VISIBLE
                }
            }
        } catch (_: Exception) {
        }
    }

    private fun initializeView() {
        binding.ivBackgroundGame.load(R.drawable.background_overlay_game)
        binding.tvTryAgain.visibility = View.GONE
        binding.tvCorrect.visibility = View.GONE
    }


    companion object {
        private val TAG = GameFragmentSecond::class.simpleName
    }
}