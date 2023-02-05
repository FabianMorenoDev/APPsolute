package de.doctormoreno.appsolute.ui.components

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import de.doctormoreno.appsolute.ALPHA_NOT_SELECTED
import de.doctormoreno.appsolute.ALPHA_SELECTED
import de.doctormoreno.appsolute.data.NOTE_NAMES
import de.doctormoreno.appsolute.ui.game.GameViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ToyCardsGame : ToyCardsFragment() {

    private val gameViewModel: GameViewModel by activityViewModels()

    private var selectionJob: Job? = null

    private lateinit var toyCards: List<ImageView>
    private lateinit var rightAnswerTC: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toyCards = listOf(
            binding.ivToyCard0,
            binding.ivToyCard1,
            binding.ivToyCard2,
            binding.ivToyCard3,
            binding.ivToyCard4,
            binding.ivToyCard5,
            binding.ivToyCard6,
            binding.ivToyCard7,
            binding.ivToyCard8,
            binding.ivToyCard9,
            binding.ivToyCard10,
            binding.ivToyCard11
        )
        setUpListeners()
        gameViewModel.savedAnswer.observe(viewLifecycleOwner) {
            when (it) {
                NOTE_NAMES[0] -> rightAnswerTC = toyCards[0]
                NOTE_NAMES[1] -> rightAnswerTC = toyCards[1]
                NOTE_NAMES[2] -> rightAnswerTC = toyCards[2]
                NOTE_NAMES[3] -> rightAnswerTC = toyCards[3]
                NOTE_NAMES[4] -> rightAnswerTC = toyCards[4]
                NOTE_NAMES[5] -> rightAnswerTC = toyCards[5]
                NOTE_NAMES[6] -> rightAnswerTC = toyCards[6]
                NOTE_NAMES[7] -> rightAnswerTC = toyCards[7]
                NOTE_NAMES[8] -> rightAnswerTC = toyCards[8]
                NOTE_NAMES[9] -> rightAnswerTC = toyCards[9]
                NOTE_NAMES[10] -> rightAnswerTC = toyCards[10]
                NOTE_NAMES[11] -> rightAnswerTC = toyCards[11]
            }
        }
    }

    private fun setUpListeners() {
        for (i in toyCards.indices) {
            setClickListenerToy(i)
        }
    }

    private fun setClickListenerToy(index: Int) {
        toyCards[index].setOnClickListener {
            if (selectionJob == null || selectionJob?.isActive == false) {
                selectionJob = lifecycleScope.launch {
                    for (toyCard in toyCards) {
                        if (toyCard != rightAnswerTC) {
                            toyCard.alpha = ALPHA_NOT_SELECTED
                        }
                    }
                    gameViewModel.selectAnswer(NOTE_NAMES[index])
                    delay(2000)
                    gameViewModel.resume()
                    toyCards.forEach { it.alpha = ALPHA_SELECTED }
                }
            }
        }
    }
}