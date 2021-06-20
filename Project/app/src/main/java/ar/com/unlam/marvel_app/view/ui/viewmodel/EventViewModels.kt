package ar.com.unlam.marvel_app.view.ui.viewmodel

import ar.com.unlam.marvel_app.data.model.apiModel.ResultsEvent
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.unlam.marvel_app.data.model.network.MarvelServiceImpl
import kotlinx.coroutines.launch

class EventViewModels:ViewModel() {

/*    var _listaEventos = MutableLiveData<List<ar.com.unlam.marvel_app.data.model.apiCharacterModel.BaseMarvelEventsResponse>>()
    val eventos = MutableLiveData<ar.com.unlam.marvel_app.data.model.apiCharacterModel.BaseMarvelEventsResponse>()*/
    val listaEventos = MutableLiveData<List<ResultsEvent>>()
    val status = MutableLiveData<Status>()

    enum class Status {
        SUCCES,
        ERROR,
    }

    init {
    }

    fun getRecyclerListObserver(): MutableLiveData<List<ResultsEvent>> {
        return listaEventos
    }

    fun getEvents() {
        viewModelScope.launch {
            try {
                val servicio = MarvelServiceImpl()
                listaEventos.value =  servicio.getEvents().data.results
                Log.d(" characters.value", listaEventos.value.toString())
                //  _charactersListLiveData.value = charactersRepository.getHeroes(1,15)
                status.value = Status.SUCCES
            } catch (e: Exception) {
                status.value = Status.ERROR
            }
        }
    }
}