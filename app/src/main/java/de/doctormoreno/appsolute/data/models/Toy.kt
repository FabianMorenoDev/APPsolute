package de.doctormoreno.appsolute.data.models

data class Toy(
    val name: String,
    val animalRes: Int,
    val objectRes: Int,
    val combinedRes: Int,
    val animationSequence: List<Pair<ToyPosition, ToyPosition>>,
    val colorRes: Int = 0
)
