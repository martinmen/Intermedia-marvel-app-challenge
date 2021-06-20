package ar.com.unlam.marvel_app.view.ui.viewmodel

import ResultsCharacters
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.unlam.marvel_app.data.model.network.MarvelServiceImpl
import BaseMarvelCharactersResponse
import kotlinx.coroutines.launch

class CharacterViewModel:ViewModel(){
    val characters = MutableLiveData<BaseMarvelCharactersResponse>()
    val listaPersonajes = MutableLiveData<List<ResultsCharacters>>()
    val status = MutableLiveData<Status>()

    enum class Status {
        SUCCES,
        ERROR,
    }

    init {
    }

    fun getRecyclerListObserver(): MutableLiveData<List<ResultsCharacters>>{
        return listaPersonajes
    }

    fun getCharacters() {
        viewModelScope.launch {
            try {
                val servicio = MarvelServiceImpl()
                listaPersonajes.value =  servicio.getHeroes().data.results
                status.value = Status.SUCCES
            } catch (e: Exception) {
                status.value = Status.ERROR
            }
        }
    }
}