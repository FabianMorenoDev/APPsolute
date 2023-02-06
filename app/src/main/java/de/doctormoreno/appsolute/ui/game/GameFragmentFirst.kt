package de.doctormoreno.appsolute.ui.game

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import de.doctormoreno.appsolute.ALPHA_NOT_SELECTED
import de.doctormoreno.appsolute.ALPHA_SELECTED
import de.doctormoreno.appsolute.MainViewModel
import de.doctormoreno.appsolute.audio.GAME_ONE_PATTERNS
import de.doctormoreno.appsolute.data.models.Note
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.audio.playNote
import de.doctormoreno.appsolute.databinding.FragmentGameFirstBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameFragmentFirst : Fragment() {

    private lateinit var binding: FragmentGameFirstBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    private val gameViewModel: GameViewModel by activityViewModels()

    private var mode = 2

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
        binding = FragmentGameFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()
        setOnClickListeners()
        gameViewModel.pause()
        gameViewModel.next.observe(viewLifecycleOwner) {
            if (it) {
                startPattern()
            }
        }
    }

    private fun startPattern() {
        try {
            mainViewModel.octaves.observe(viewLifecycleOwner) {
                binding.gameContainerView.visibility = View.GONE
                binding.tvCorrect.visibility = View.GONE
                binding.tvTryAgain.visibility = View.GONE
                val chord = GAME_ONE_PATTERNS.random()
                val notesToPlay =
                    it.random().notes.filter { note: Note -> chord.contains(note.name) }
                var playJob: Job? = null
                for (note in notesToPlay) {
                    playJob = lifecycleScope.launch {
                        playNote(note,context)
                    }
                }
                gameViewModel.saveAnswer(chord[1])
                gameViewModel.pause()
                playJob?.invokeOnCompletion {
                    binding.gameContainerView.visibility = View.VISIBLE
                }
            }
        } catch (_: Exception) {
        }
    }

    private fun setOnClickListeners(){
        binding.btnNext.setOnClickListener {
            findNavController().navigate(GameFragmentFirstDirections.actionGameFragmentFirstToGameFragmentSecond())
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
        binding.btn2.setOnClickListener {
            binding.btn2.alpha = ALPHA_SELECTED
            binding.btn3.alpha = ALPHA_NOT_SELECTED
            binding.btn4.alpha = ALPHA_NOT_SELECTED
            mode = 2
        }
        binding.btn3.setOnClickListener {
            binding.btn3.alpha = ALPHA_SELECTED
            binding.btn2.alpha = ALPHA_NOT_SELECTED
            binding.btn4.alpha = ALPHA_NOT_SELECTED
            mode = 3
        }
        binding.btn4.setOnClickListener {
            binding.btn4.alpha = ALPHA_SELECTED
            binding.btn2.alpha = ALPHA_NOT_SELECTED
            binding.btn3.alpha = ALPHA_NOT_SELECTED
            mode = 4
        }
    }

    private fun initializeView() {
        binding.ivBackgroundGame.load(R.drawable.background_overlay_game)
        binding.btn2.alpha = ALPHA_SELECTED
        binding.btn3.alpha = ALPHA_NOT_SELECTED
        binding.btn4.alpha = ALPHA_NOT_SELECTED
        binding.tvTryAgain.visibility = View.GONE
        binding.tvCorrect.visibility = View.GONE
    }

    companion object {
        private val TAG = GameFragmentFirst::class.simpleName
    }
}