package de.doctormoreno.appsolute.audio

import de.doctormoreno.appsolute.MELODY_NOTE_DELAY
import de.doctormoreno.appsolute.data.NOTE_NAMES
import kotlinx.coroutines.delay


val GAME_ONE_PATTERNS = listOf(
    listOf(NOTE_NAMES[0], NOTE_NAMES[4], NOTE_NAMES[7]),
    listOf(NOTE_NAMES[1], NOTE_NAMES[5], NOTE_NAMES[8]),
    listOf(NOTE_NAMES[2], NOTE_NAMES[6], NOTE_NAMES[8]),
    listOf(NOTE_NAMES[3], NOTE_NAMES[7], NOTE_NAMES[10]),
    listOf(NOTE_NAMES[4], NOTE_NAMES[8], NOTE_NAMES[11]),
    listOf(NOTE_NAMES[5], NOTE_NAMES[9], NOTE_NAMES[0]),
    listOf(NOTE_NAMES[6], NOTE_NAMES[10], NOTE_NAMES[1]),
    listOf(NOTE_NAMES[7], NOTE_NAMES[11], NOTE_NAMES[2]),
    listOf(NOTE_NAMES[8], NOTE_NAMES[0], NOTE_NAMES[3]),
    listOf(NOTE_NAMES[9], NOTE_NAMES[1], NOTE_NAMES[4]),
    listOf(NOTE_NAMES[10], NOTE_NAMES[2], NOTE_NAMES[5]),
    listOf(NOTE_NAMES[11], NOTE_NAMES[3], NOTE_NAMES[6])
)

val GAME_ONE_PATTERNS_1 = listOf(
    Pattern("C5 F#4 D#6|F#6|A#6 C4|C5|C6")
)

class Pattern(notes: String) {
    val notations = mutableListOf<Notation>()
    private val pattern = notes.split(" ")

    init {
        pattern.forEach {
            if (it.contains('|')){
                val str = it.split('|')
                val chordNotes = mutableListOf<NoteNotation>()
                for (s in str){
                    chordNotes.add(newNote(s))
                }
                notations.add(ChordNotation(chordNotes))
            } else {
                notations.add(newNote(it))
            }
        }
    }

    private fun newNote(str: String): NoteNotation {
        return NoteNotation(str.dropLast(1), str.last().toString().toInt())
    }

    suspend fun play(){
        for (n in notations){
            n.play()
            delay(MELODY_NOTE_DELAY)
        }
    }
}

interface Notation{
    fun printNotation()
    suspend fun play()
}

data class ChordNotation(
    var notes: List<NoteNotation>
) : Notation {
    override fun printNotation() {
        notes.forEach{
            println("chord note ${it.name} in octave ${it.octave}")
        }
    }
    override suspend fun play() {

    }
}

data class NoteNotation(
    var name: String,
    var octave: Int
) : Notation {
    override fun printNotation() {
        println("note $name in octave $octave")
    }
    override suspend fun play() {
        TODO("Not yet implemented")
    }
}