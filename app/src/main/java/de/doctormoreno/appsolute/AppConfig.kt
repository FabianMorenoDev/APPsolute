package de.doctormoreno.appsolute

/*------------ UI CONFIG ------------*/
const val ALPHA_NOT_SELECTED: Float = 0.2f
const val ALPHA_SELECTED: Float = 1f
const val ALPHA_KEY_PRESSED: Float = 0.7f
val TOY_COLORS = listOf(
    R.color.toy_c,
    R.color.toy_c_sharp,
    R.color.toy_d,
    R.color.toy_d_sharp,
    R.color.toy_e,
    R.color.toy_f,
    R.color.toy_f_sharp,
    R.color.toy_g,
    R.color.toy_g_sharp,
    R.color.toy_a,
    R.color.toy_a_sharp,
    R.color.toy_b
)

/*------------ AUDIO CONFIG ------------*/
const val AMBIENT_NOTE_ATTACK: Long = 5000
const val AMBIENT_NOTE_INTERVAL: Long = 5000
const val AMBIENT_RESONANCE_AMOUNT: Float = 24f
const val AMBIENT_RESONANCE_WIDTH: Float = 15f
const val MELODY_NOTE_DELAY: Long = 500
val EQ_BANDS =
    listOf(
        250 hzWithGain 0f,
        300 hzWithGain -0.1f,
        350 hzWithGain -0.2f,
        420 hzWithGain -0.5f,
        480 hzWithGain -1f,
        540 hzWithGain -1.7f,
        600 hzWithGain -3f,
        680 hzWithGain -3.6f,
        780 hzWithGain -2.8f,
        880 hzWithGain 1.4f,
        990 hzWithGain -4.1f,
        1160 hzWithGain -2.3f,
        1350 hzWithGain 2f,
        1500 hzWithGain 7f,
        1700 hzWithGain 5.4f,
        1900 hzWithGain 3.3f,
        2100 hzWithGain 3f,
        2400 hzWithGain 2.8f,
        2700 hzWithGain 1.9f,
        3000 hzWithGain 2f,
        3400 hzWithGain 0.5f,
        3800 hzWithGain 2f,
        4200 hzWithGain 11f,
        4800 hzWithGain -3f,
        5400 hzWithGain -10.5f,
        6100 hzWithGain -8.1f,
        7000 hzWithGain -2.5f,
        7900 hzWithGain 0.6f,
        9000 hzWithGain 0f
    )

/*------------ UTILS ------------*/
data class EqBandConfig(
    var frequency: Float,
    var gain: Float
)

infix fun Int.hzWithGain(gain: Float): EqBandConfig {
    return EqBandConfig(this.toFloat(), gain)
}