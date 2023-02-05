package de.doctormoreno.appsolute.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.doctormoreno.appsolute.TOY_COLORS
import de.doctormoreno.appsolute.R
import de.doctormoreno.appsolute.data.enums.Instrument
import de.doctormoreno.appsolute.data.models.Note
import de.doctormoreno.appsolute.data.models.Octave
import de.doctormoreno.appsolute.data.models.Toy

private const val TAG = "AppRepositoryTAG"

private const val NUMBER_OF_OCTAVES = 3
private const val OFFSET = 4
val NOTE_NAMES = listOf("C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "A#", "B")
val NOTE_FREQUENCIES = listOf(
    listOf(261.63f * 1, 261.63f * 2, 261.63f * 3, 261.63f * 4, 261.63f * 5), // C
    listOf(277.18f * 1, 277.18f * 2, 277.18f * 3, 277.18f * 4, 277.18f * 5), // C#
    listOf(293.66f * 1, 293.66f * 2, 293.66f * 3, 293.66f * 4, 293.66f * 5), // D
    listOf(311.13f * 1, 311.13f * 2, 311.13f * 3, 311.13f * 4, 311.13f * 5), // D#
    listOf(329.63f * 1, 329.63f * 2, 329.63f * 3, 329.63f * 4, 329.63f * 5), // E
    listOf(349.23f * 1, 349.23f * 2, 349.23f * 3, 349.23f * 4, 349.23f * 5), // F
    listOf(369.99f * 1, 369.99f * 2, 369.99f * 3, 369.99f * 4, 369.99f * 5), // F#
    listOf(369.99f * 1, 369.99f * 2, 369.99f * 3, 369.99f * 4, 369.99f * 5), // G
    listOf(392.00f * 1, 392.00f * 2, 392.00f * 3, 392.00f * 4, 392.00f * 5), // G#
    listOf(440.00f * 1, 440.00f * 2, 440.00f * 3, 440.00f * 4, 440.00f * 5), // A
    listOf(466.16f * 1, 466.16f * 2, 466.16f * 3, 466.16f * 4, 466.16f * 5), // A#
    listOf(493.88f * 1, 493.88f * 2, 493.88f * 3, 493.88f * 4, 493.88f * 5)  // B
)

class AppRepository() {

    private val animationSequences = AnimationSequences().animationSequences

    private val _octaves = MutableLiveData<List<Octave>>()
    val octaves: LiveData<List<Octave>>
        get() = _octaves

    private val _toys = MutableLiveData<List<Toy>>()
    val toys: LiveData<List<Toy>>
        get() = _toys

    init {
        _toys.value = loadToys()
    }

    private fun loadToys(): List<Toy> {
        return listOf(
            Toy(
                NOTE_NAMES[0],
                R.drawable.note_c_animal,
                R.drawable.note_c_object,
                R.drawable.note_c_combined,
                animationSequences[0],
                TOY_COLORS[0]
            ),
            Toy(
                NOTE_NAMES[1],
                R.drawable.note_c_sharp_animal,
                R.drawable.note_c_sharp_object,
                R.drawable.note_c_sharp_combined,
                animationSequences[1],
                TOY_COLORS[1]
            ),
            Toy(
                NOTE_NAMES[2],
                R.drawable.note_d_animal,
                R.drawable.note_d_object,
                R.drawable.note_d_combined,
                animationSequences[2],
                TOY_COLORS[2]
            ),
            Toy(
                NOTE_NAMES[3],
                R.drawable.note_d_sharp_animal,
                R.drawable.note_d_sharp_object,
                R.drawable.note_d_sharp_combined,
                animationSequences[3],
                TOY_COLORS[3]
            ),
            Toy(
                NOTE_NAMES[4],
                R.drawable.note_e_animal,
                R.drawable.note_e_object,
                R.drawable.note_e_combined,
                animationSequences[4],
                TOY_COLORS[4]
            ),
            Toy(
                NOTE_NAMES[5],
                R.drawable.note_f_animal,
                R.drawable.note_f_object,
                R.drawable.note_f_combined,
                animationSequences[5],
                TOY_COLORS[5]
            ),
            Toy(
                NOTE_NAMES[6],
                R.drawable.note_f_sharp_animal,
                R.drawable.note_f_sharp_object,
                R.drawable.note_f_sharp_combined,
                animationSequences[6],
                TOY_COLORS[6]
            ),
            Toy(
                NOTE_NAMES[7],
                R.drawable.note_g_animal,
                R.drawable.note_g_object,
                R.drawable.note_g_combined,
                animationSequences[7],
                TOY_COLORS[7]
            ),
            Toy(
                NOTE_NAMES[8],
                R.drawable.note_g_sharp_animal,
                R.drawable.note_g_sharp_object,
                R.drawable.note_g_sharp_combined,
                animationSequences[8],
                TOY_COLORS[8]
            ),
            Toy(
                NOTE_NAMES[9],
                R.drawable.note_a_animal,
                R.drawable.note_a_object,
                R.drawable.note_a_combined,
                animationSequences[9],
                TOY_COLORS[9]
            ),
            Toy(
                NOTE_NAMES[10],
                R.drawable.note_a_sharp_animal,
                R.drawable.note_a_sharp_object,
                R.drawable.note_a_sharp_combined,
                animationSequences[10],
                TOY_COLORS[10]
            ),
            Toy(
                NOTE_NAMES[11],
                R.drawable.note_b_animal,
                R.drawable.note_b_object,
                R.drawable.note_b_combined,
                animationSequences[11],
                TOY_COLORS[11]
            )
        )
    }

    fun loadOctaves(instrument: Instrument) {
        val octavesList = mutableListOf<Octave>()
        for (o in 0 until NUMBER_OF_OCTAVES) {
            val notesList = mutableListOf<Note>()
            for (n in 0 until 12) {
                when (instrument) {
                    Instrument.PIANO -> notesList.add(
                        Note(
                            name = NOTE_NAMES[n],
                            soundRes = loadPianoSounds[o * 12 + n],
                            frequencies = NOTE_FREQUENCIES[n]
                        )
                    )
                    Instrument.GUITAR -> notesList.add(
                        Note(
                            name = NOTE_NAMES[n],
                            soundRes = loadGuitarSounds[o * 12 + n],
                            frequencies = NOTE_FREQUENCIES[n]
                        )
                    )
                    Instrument.STRINGS -> notesList.add(
                        Note(
                            name = NOTE_NAMES[n],
                            soundRes = loadStringSounds[o * 12 + n],
                            frequencies = NOTE_FREQUENCIES[n]
                        )
                    )
                    Instrument.BRASS -> notesList.add(
                        Note(
                            name = NOTE_NAMES[n],
                            soundRes = loadBrassSounds[o * 12 + n],
                            frequencies = NOTE_FREQUENCIES[n]
                        )
                    )
                }
            }

            octavesList.add(
                Octave(
                    o + OFFSET,
                    notesList
                )
            )
        }
        _octaves.postValue(octavesList)
    }

    private val loadPianoSounds = listOf(
        R.raw.piano_c4,
        R.raw.piano_c4_sharp,
        R.raw.piano_d4,
        R.raw.piano_d4_sharp,
        R.raw.piano_e4,
        R.raw.piano_f4,
        R.raw.piano_f4_sharp,
        R.raw.piano_g4,
        R.raw.piano_g4_sharp,
        R.raw.piano_a4,
        R.raw.piano_a4_sharp,
        R.raw.piano_b4,
        R.raw.piano_c5,
        R.raw.piano_c5_sharp,
        R.raw.piano_d5,
        R.raw.piano_d5_sharp,
        R.raw.piano_e5,
        R.raw.piano_f5,
        R.raw.piano_f5_sharp,
        R.raw.piano_g5,
        R.raw.piano_g5_sharp,
        R.raw.piano_a5,
        R.raw.piano_a5_sharp,
        R.raw.piano_b5,
        R.raw.piano_c6,
        R.raw.piano_c6_sharp,
        R.raw.piano_d6,
        R.raw.piano_d6_sharp,
        R.raw.piano_e6,
        R.raw.piano_f6,
        R.raw.piano_f6_sharp,
        R.raw.piano_g6,
        R.raw.piano_g6_sharp,
        R.raw.piano_a6,
        R.raw.piano_a6_sharp,
        R.raw.piano_b6
    )

    private val loadGuitarSounds = listOf(
        R.raw.guitar_c4,
        R.raw.guitar_c4_sharp,
        R.raw.guitar_d4,
        R.raw.guitar_d4_sharp,
        R.raw.guitar_e4,
        R.raw.guitar_f4,
        R.raw.guitar_f4_sharp,
        R.raw.guitar_g4,
        R.raw.guitar_g4_sharp,
        R.raw.guitar_a4,
        R.raw.guitar_a4_sharp,
        R.raw.guitar_b4,
        R.raw.guitar_c5,
        R.raw.guitar_c5_sharp,
        R.raw.guitar_d5,
        R.raw.guitar_d5_sharp,
        R.raw.guitar_e5,
        R.raw.guitar_f5,
        R.raw.guitar_f5_sharp,
        R.raw.guitar_g5,
        R.raw.guitar_g5_sharp,
        R.raw.guitar_a5,
        R.raw.guitar_a5_sharp,
        R.raw.guitar_b5,
        R.raw.guitar_c6,
        R.raw.guitar_c6_sharp,
        R.raw.guitar_d6,
        R.raw.guitar_d6_sharp,
        R.raw.guitar_e6,
        R.raw.guitar_f6,
        R.raw.guitar_f6_sharp,
        R.raw.guitar_g6,
        R.raw.guitar_g6_sharp,
        R.raw.guitar_a6,
        R.raw.guitar_a6_sharp,
        R.raw.guitar_b6
    )

    private val loadStringSounds = listOf(
        R.raw.strings_c4,
        R.raw.strings_c4_sharp,
        R.raw.strings_d4,
        R.raw.strings_d4_sharp,
        R.raw.strings_e4,
        R.raw.strings_f4,
        R.raw.strings_f4_sharp,
        R.raw.strings_g4,
        R.raw.strings_g4_sharp,
        R.raw.strings_a4,
        R.raw.strings_a4_sharp,
        R.raw.strings_b4,
        R.raw.strings_c5,
        R.raw.strings_c5_sharp,
        R.raw.strings_d5,
        R.raw.strings_d5_sharp,
        R.raw.strings_e5,
        R.raw.strings_f5,
        R.raw.strings_f5_sharp,
        R.raw.strings_g5,
        R.raw.strings_g5_sharp,
        R.raw.strings_a5,
        R.raw.strings_a5_sharp,
        R.raw.strings_b5,
        R.raw.strings_c6,
        R.raw.strings_c6_sharp,
        R.raw.strings_d6,
        R.raw.strings_d6_sharp,
        R.raw.strings_e6,
        R.raw.strings_f6,
        R.raw.strings_f6_sharp,
        R.raw.strings_g6,
        R.raw.strings_g6_sharp,
        R.raw.strings_a6,
        R.raw.strings_a6_sharp,
        R.raw.strings_b6
    )

    private val loadBrassSounds = listOf(
        R.raw.brass_c4,
        R.raw.brass_c4_sharp,
        R.raw.brass_d4,
        R.raw.brass_d4_sharp,
        R.raw.brass_e4,
        R.raw.brass_f4,
        R.raw.brass_f4_sharp,
        R.raw.brass_g4,
        R.raw.brass_g4_sharp,
        R.raw.brass_a4,
        R.raw.brass_a4_sharp,
        R.raw.brass_b4,
        R.raw.brass_c5,
        R.raw.brass_c5_sharp,
        R.raw.brass_d5,
        R.raw.brass_d5_sharp,
        R.raw.brass_e5,
        R.raw.brass_f5,
        R.raw.brass_f5_sharp,
        R.raw.brass_g5,
        R.raw.brass_g5_sharp,
        R.raw.brass_a5,
        R.raw.brass_a5_sharp,
        R.raw.brass_b5,
        R.raw.brass_c6,
        R.raw.brass_c6_sharp,
        R.raw.brass_d6,
        R.raw.brass_d6_sharp,
        R.raw.brass_e6,
        R.raw.brass_f6,
        R.raw.brass_f6_sharp,
        R.raw.brass_g6,
        R.raw.brass_g6_sharp,
        R.raw.brass_a6,
        R.raw.brass_a6_sharp,
        R.raw.brass_b6
    )

}
