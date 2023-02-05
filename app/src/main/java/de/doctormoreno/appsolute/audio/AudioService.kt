package de.doctormoreno.appsolute.audio

import android.animation.ValueAnimator
import android.content.Context
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioTrack
import android.media.MediaPlayer
import android.media.audiofx.DynamicsProcessing
import android.os.CountDownTimer
import androidx.core.animation.doOnEnd
import de.doctormoreno.appsolute.AMBIENT_NOTE_ATTACK
import de.doctormoreno.appsolute.AMBIENT_RESONANCE_AMOUNT
import de.doctormoreno.appsolute.AMBIENT_RESONANCE_WIDTH
import de.doctormoreno.appsolute.EQ_BANDS
import de.doctormoreno.appsolute.data.NOTE_FREQUENCIES
import de.doctormoreno.appsolute.data.models.Note
import kotlinx.coroutines.*


private val noteDynamics = mutableListOf<DynamicsProcessing>()
private var ambientDynamics: DynamicsProcessing? = null
private var ambientPlayer: MediaPlayer? = null
private var eqMix = 0f
private val loopTimer = object : CountDownTimer(10000, 1000) {
    override fun onTick(p0: Long) {}
    override fun onFinish() {
        ambientPlayer?.seekTo(0)
        this.start()
    }
}

suspend fun ambientResonate(note: Note) {
    withContext(Dispatchers.Main) {
        for (i in 0 until note.frequencies.size * 2) {
            val animator = ValueAnimator.ofFloat(0f, AMBIENT_RESONANCE_AMOUNT)
            animator.duration = AMBIENT_NOTE_ATTACK
            animator.addUpdateListener { valAnim ->
                val band = ambientDynamics?.getPostEqBandByChannelIndex(0, i)
                band?.let {
                    it.apply {
                        isEnabled = true
                        if (i % 2 == 0) {
                            cutoffFrequency = note.frequencies[i / 2] - AMBIENT_RESONANCE_WIDTH
                            gain = 0f
                        } else {
                            cutoffFrequency = note.frequencies[i / 2] + AMBIENT_RESONANCE_WIDTH
                            gain = valAnim.animatedValue as Float
                        }
                    }
                }
                ambientDynamics?.setPostEqBandAllChannelsTo(i, band)
            }
            animator.doOnEnd {
                val band = ambientDynamics?.getPostEqBandByChannelIndex(0, i)
                band?.let {
                    it.apply {
                        gain = 0f
                    }
                }
                ambientDynamics?.setPostEqBandAllChannelsTo(i, band)
            }
            animator.start()
        }
        delay(AMBIENT_NOTE_ATTACK)
    }
}

suspend fun playNote(note: Note, context: Context) {

    val bufferSize = AudioTrack.getMinBufferSize(
        44100,
        AudioFormat.CHANNEL_OUT_STEREO,
        AudioFormat.ENCODING_PCM_16BIT
    ) * 10

    try {
        val audioTrack = AudioTrack.Builder()
            .setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            .setBufferSizeInBytes(bufferSize)
            .setAudioFormat(
                AudioFormat.Builder()
                    .setSampleRate(44100)
                    .setChannelMask(AudioFormat.CHANNEL_OUT_STEREO)
                    .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                    .build()
            )
            .setTransferMode(AudioTrack.MODE_STREAM)
            .build().apply {
                setVolume(AudioTrack.getMaxVolume())
                play()
            }

        val dynamics = configDynamics(audioTrack.audioSessionId)
        noteDynamics.add(dynamics)

        try {
            withContext(Dispatchers.IO) {
                context.resources.openRawResource(note.soundRes).buffered()
                    .use { bufferedInputStream ->

                        val buffer = ByteArray(bufferSize)

                        while (isActive) {
                            val bytesRead = bufferedInputStream.read(buffer, 0, buffer.size)
                            if (bytesRead < 0) break
                            audioTrack.write(buffer, 0, bytesRead)
                        }
                    }
            }
        } finally {
            noteDynamics.remove(dynamics)
            audioTrack.release()
        }
    } catch (_: java.lang.Exception) {
    }
}

fun playAmbientAudio(audioResId: Int, context: Context) {
    if (ambientPlayer?.isPlaying == true) stopAmbientAudio()
    ambientPlayer = MediaPlayer.create(context, audioResId)
    ambientPlayer?.audioSessionId?.let {
        ambientDynamics = configDynamics(it)
    }
    ambientPlayer?.start()
    loopTimer.start()
}

fun stopAmbientAudio() {
    ambientPlayer?.stop()
    ambientPlayer?.release()
    ambientPlayer = null
    loopTimer.cancel()
}

private fun configDynamics(audioSessionId: Int): DynamicsProcessing {

    val dynamicsConfig = DynamicsProcessing.Config.Builder(
        DynamicsProcessing.VARIANT_FAVOR_FREQUENCY_RESOLUTION,
        1,
        true, EQ_BANDS.size,
        false, 0,
        true, NOTE_FREQUENCIES[0].size,
        false
    ).build()

    val equalizer = dynamicsConfig.getChannelByChannelIndex(0).preEq

    for (i in EQ_BANDS.indices) {
        equalizer.getBand(i).apply {
            isEnabled = true
            cutoffFrequency = EQ_BANDS[i].frequency
            gain = EQ_BANDS[i].gain * eqMix
        }
    }

    val dynamics = DynamicsProcessing(
        0, audioSessionId, dynamicsConfig
    )
    dynamics.enabled = true

    return dynamics
}

fun updateEQs(mix: Float) {
    eqMix = mix
    for (dP in noteDynamics) {
        for (i in EQ_BANDS.indices) {
            val band = dP.getPreEqBandByChannelIndex(0, i)
            band.gain = EQ_BANDS[i].gain * mix
            dP.setPreEqBandAllChannelsTo(i, band)
        }
    }
    for (i in EQ_BANDS.indices) {
        val band = ambientDynamics?.getPreEqBandByChannelIndex(0, i)
        band?.gain = EQ_BANDS[i].gain * mix
        ambientDynamics?.setPreEqBandAllChannelsTo(i, band)
    }
}

