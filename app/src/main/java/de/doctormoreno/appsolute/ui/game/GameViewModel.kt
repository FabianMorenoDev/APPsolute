package de.doctormoreno.appsolute.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {

    private val _next = MutableLiveData<Boolean>()
    val next: LiveData<Boolean>
        get() = _next

    private val _stats = MutableLiveData<MutableList<Boolean>>(mutableListOf())
    val stats: LiveData<MutableList<Boolean>>
        get() = _stats

    private var _savedAnswer = MutableLiveData<String>()
    val savedAnswer: LiveData<String>
        get() = _savedAnswer

    fun saveAnswer(answer: String) {
        _savedAnswer.postValue(answer)
    }

    fun setStats(rightAnswer: Boolean) {
        _stats.value?.add(rightAnswer)
        _stats.postValue(_stats.value)
    }

    fun selectAnswer(answer: String) {
        setStats(answer == savedAnswer.value)
    }

    fun pause() {
        _next.postValue(false)
    }

    fun resume() {
        _next.postValue(true)
    }

    companion object {
        private val TAG = GameViewModel::class.simpleName
    }

}