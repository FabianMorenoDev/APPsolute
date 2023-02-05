package de.doctormoreno.appsolute

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import de.doctormoreno.appsolute.data.AppRepository
import de.doctormoreno.appsolute.data.enums.Instrument

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AppRepository()

    val octaves = repository.octaves

    val toys = repository.toys

    private val _instrumentSelected = MutableLiveData<Instrument>()
    val instrumentSelected: LiveData<Instrument>
        get() = _instrumentSelected

    fun selectInstrument(instrument: Instrument) {
        _instrumentSelected.postValue(instrument)
        repository.loadOctaves(instrument)
    }

    companion object {
        private val TAG = MainViewModel::class.simpleName
    }
}
