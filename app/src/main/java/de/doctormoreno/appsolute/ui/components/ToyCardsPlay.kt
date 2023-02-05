package de.doctormoreno.appsolute.ui.components

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import de.doctormoreno.appsolute.ALPHA_NOT_SELECTED
import de.doctormoreno.appsolute.ALPHA_SELECTED
import de.doctormoreno.appsolute.data.models.Note
import de.doctormoreno.appsolute.audio.ambientResonate
import de.doctormoreno.appsolute.audio.playNote
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ToyCardsPlay : ToyCardsFragment() {

    private lateinit var toyCards: List<ImageView>
    private var playJob: Job? = null
    private var resoJob: Job? = null

    private lateinit var context: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        this.context = context
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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
        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpListeners() {
        mainViewModel.octaves.observe(viewLifecycleOwner) { oct ->
            for (i in toyCards.indices){
                toyCards[i].setOnClickListener {
                    if (playJob == null || playJob?.isActive == false &&
                        resoJob == null || resoJob?.isActive == false) {
                        for (toyCard in toyCards) {
                            if (toyCard != it) {
                                toyCard.alpha = ALPHA_NOT_SELECTED
                            }
                        }
                        playAmbientNote(oct[1].notes[i])
                    }
                }
            }
        }
    }

    private fun playAmbientNote(note: Note) {
        resoJob = lifecycleScope.launch{
           ambientResonate(note)
        }
        resoJob?.invokeOnCompletion {
            playJob = lifecycleScope.launch {
                playNote(note, context)
            }
            playJob?.invokeOnCompletion {
                toyCards.forEach { it.alpha = ALPHA_SELECTED }
            }
        }
    }
    companion object{
        private val TAG = ToyCardsPlay::class.simpleName
    }
}