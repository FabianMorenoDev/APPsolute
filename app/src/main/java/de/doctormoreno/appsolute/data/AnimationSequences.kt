package de.doctormoreno.appsolute.data

import de.doctormoreno.appsolute.data.models.ToyPosition

private const val TAG = "AnimationSequencesTAG"

// O = OFFSET, D = DISTANCE, C = CENTER

private const val C_SCALE = 7
private const val DURATION = 2000//1500

private const val X_C = 0.5
private const val Y_C = 0.5

private const val X_S_O = 1 / 24.0
private const val X_S_D = 1 / 12.0
private const val X_W_O = 1 / 14.0
private const val X_W_D = 1 / 7.0
private const val X_B_D = 1 / 7.0

private const val Y_S1_O = 0.07
private const val Y_S2_O = 0.23
private const val Y_W_O = 0.85
private const val Y_B_O = 0.66

class AnimationSequences {

    val animationSequences = listOf(
        // C animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 0, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 0, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C, 0.45, C_SCALE, 0, DURATION * 0.2),
                ToyPosition(X_C, 0.55, C_SCALE, 0, DURATION * 0.2)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.7),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.7)
            ),
            // END POS
            Pair(
                ToyPosition(X_W_O + X_W_D * 0, Y_W_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_W_O + X_W_D * 0, Y_W_O, 1, 0, DURATION * 0.1)
            )
        ),
        // C# animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 1, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 1, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(0.48, Y_C, C_SCALE, 0, DURATION * 0.2),
                ToyPosition(0.52, Y_C, C_SCALE, 0, DURATION * 0.2)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(0.52, Y_C, C_SCALE, 0, DURATION * 0.7),
                ToyPosition(0.48, Y_C, C_SCALE, 0, DURATION * 0.7)
            ),
            // END POS
            Pair(
                ToyPosition(X_B_D * 1, Y_B_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_B_D * 1, Y_B_O, 1, 0, DURATION * 0.1)
            )
        ),
        // D animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 2, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 2, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(0.35, 0.43, C_SCALE * 0.8, -30, DURATION * 0.4),
                ToyPosition(0.3, 0.75, C_SCALE * 1.5, 60, DURATION * 0.4)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(0.35, 0.43, C_SCALE * 0.8, -30, DURATION * 0.2),
                ToyPosition(0.3, 0.75, C_SCALE * 1.5, 60, DURATION * 0.2)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(1.55, -0.82, C_SCALE * 0.8, -30, DURATION * 0.4),
                ToyPosition(1.5, -0.5, C_SCALE * 1.5, 60, DURATION * 0.4)
            )
        ),
        // D# animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 3, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 3, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.3),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.3)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C - 0.03, Y_C, C_SCALE, 30, DURATION * 0.3),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.3)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(X_C + 0.03, Y_C, C_SCALE, -30, DURATION * 0.3),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.3)
            ),
            // END POS
            Pair(
                ToyPosition(X_B_D * 2, Y_B_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_B_D * 2, Y_B_O, 1, 0, DURATION * 0.1)
            )
        ),
        // E animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 4, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 4, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C + 0.01, Y_C, C_SCALE, 5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C + 0.02, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(X_C + 0.03, Y_C, C_SCALE, 5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 4
            Pair(
                ToyPosition(X_C + 0.04, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 5
            Pair(
                ToyPosition(X_C + 0.03, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 6
            Pair(
                ToyPosition(X_C + 0.02, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 7
            Pair(
                ToyPosition(X_C + 0.01, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 8
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 9
            Pair(
                ToyPosition(X_C - 0.01, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C - 0.01, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // END POS
            Pair(
                ToyPosition(X_W_O + X_W_D * 2, Y_W_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_W_O + X_W_D * 2, Y_W_O, 1, 0, DURATION * 0.1)
            )
        ),
        // F animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 5, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 5, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(X_C, Y_C - 0.2, C_SCALE, -10, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 4
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 10, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 5
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.2),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.2)
            ),
            // CENTER POS 6
            Pair(
                ToyPosition(X_C, Y_C - 0.2, C_SCALE, -10, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 7
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 10, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 8
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // END POS
            Pair(
                ToyPosition(X_W_O + X_W_D * 3, Y_W_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_W_O + X_W_D * 3, Y_W_O, 1, 0, DURATION * 0.1)
            )
        ),
        // F# animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 6, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 6, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C + 0.01, Y_C, C_SCALE, 5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C + 0.02, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(X_C + 0.03, Y_C, C_SCALE, 5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 4
            Pair(
                ToyPosition(X_C + 0.04, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 5
            Pair(
                ToyPosition(X_C + 0.03, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 6
            Pair(
                ToyPosition(X_C + 0.02, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 7
            Pair(
                ToyPosition(X_C + 0.01, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 8
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 9
            Pair(
                ToyPosition(X_C - 0.01, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C - 0.01, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // END POS
            Pair(
                ToyPosition(X_B_D * 4, Y_B_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_B_D * 4, Y_B_O, 1, 0, DURATION * 0.1)
            )
        ),
        // G animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 7, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 7, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C - 0.2, Y_C - 0.1, C_SCALE, 0, DURATION * 0.4),
                ToyPosition(X_C - 0.2, Y_C + 0.1, C_SCALE, 0, DURATION * 0.4)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C + 0.2, Y_C - 0.1, C_SCALE, 0, DURATION * 0.5),
                ToyPosition(X_C + 0.2, Y_C + 0.1, C_SCALE, 360, DURATION * 0.5)
            ),
            // END POS
            Pair(
                ToyPosition(X_W_O + X_W_D * 4, Y_W_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_W_O + X_W_D * 4, Y_W_O, 1, 0, DURATION * 0.1)
            )
        ),
        // G# animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 8, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 8, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C + 0.03, Y_C - 0.03 * 1.5, C_SCALE, 5, DURATION * 0.2),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.2)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(X_C + 0.06, Y_C - 0.06 * 1.5, C_SCALE, -5, DURATION * 0.2),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.2)
            ),
            // END POS
            Pair(
                ToyPosition(X_B_D * 5, Y_B_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_B_D * 5, Y_B_O, 1, 0, DURATION * 0.1)
            )
        ),
        // A animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 9, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 9, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C, Y_C - 0.01, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, -2, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(X_C, Y_C - 0.01, C_SCALE, -4, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 4
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, -6, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 5
            Pair(
                ToyPosition(X_C, Y_C - 0.01, C_SCALE, -8, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 6
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, -6, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 7
            Pair(
                ToyPosition(X_C, Y_C - 0.01, C_SCALE, -4, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 8
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, -2, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 9
            Pair(
                ToyPosition(X_C, Y_C - 0.01, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // END POS
            Pair(
                ToyPosition(X_W_O + X_W_D * 5, Y_W_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_W_O + X_W_D * 5, Y_W_O, 1, 0, DURATION * 0.1)
            )
        ),
        // A# animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 10, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 10, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(0.52, Y_C, C_SCALE, 0, DURATION * 0.2),
                ToyPosition(0.48, Y_C, C_SCALE, 0, DURATION * 0.2)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(0.48, Y_C, C_SCALE, 0, DURATION * 0.7),
                ToyPosition(0.52, Y_C, C_SCALE, 0, DURATION * 0.7)
            ),
            // END POS
            Pair(
                ToyPosition(X_B_D * 6, Y_B_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_B_D * 6, Y_B_O, 1, 0, DURATION * 0.1)
            )
        ),
        // B animation (animal/object)
        listOf(
            // START POS
            Pair(
                ToyPosition(X_S_O + X_S_D * 11, Y_S1_O, 1, 0, 0),
                ToyPosition(X_S_O + X_S_D * 11, Y_S2_O, 1, 0, 0)
            ),
            // CENTER POS 1
            Pair(
                ToyPosition(X_C + 0.01, Y_C, C_SCALE, 5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 2
            Pair(
                ToyPosition(X_C + 0.02, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 3
            Pair(
                ToyPosition(X_C + 0.03, Y_C, C_SCALE, 5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 4
            Pair(
                ToyPosition(X_C + 0.04, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 5
            Pair(
                ToyPosition(X_C + 0.03, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 6
            Pair(
                ToyPosition(X_C + 0.02, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 7
            Pair(
                ToyPosition(X_C + 0.01, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 8
            Pair(
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1),
                ToyPosition(X_C, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // CENTER POS 9
            Pair(
                ToyPosition(X_C - 0.01, Y_C, C_SCALE, -5, DURATION * 0.1),
                ToyPosition(X_C - 0.01, Y_C, C_SCALE, 0, DURATION * 0.1)
            ),
            // END POS
            Pair(
                ToyPosition(X_B_D * 7, Y_W_O, 1, 0, DURATION * 0.1),
                ToyPosition(X_B_D * 7, Y_W_O, 1, 0, DURATION * 0.1)
            )
        )
    )
}
