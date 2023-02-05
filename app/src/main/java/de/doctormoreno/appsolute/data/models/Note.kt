package de.doctormoreno.appsolute.data.models

data class Note(
    val name: String,
    var soundId: Int = 0,
    val soundRes: Int,
    var imageRes: Int = 0,
    var frequencies: List<Float>
)
