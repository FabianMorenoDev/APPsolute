package de.doctormoreno.appsolute.ui.ambient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import de.doctormoreno.appsolute.data.enums.Ambient

class AmbientViewModel : ViewModel() {

    private val _ambientSelected = MutableLiveData<Ambient>()
    val ambientSelected: LiveData<Ambient>
        get() = _ambientSelected

    fun selectAmbient(ambient: Ambient) {
        _ambientSelected.postValue(ambient)
    }

    companion object {
        private val TAG = AmbientViewModel::class.simpleName
    }
}