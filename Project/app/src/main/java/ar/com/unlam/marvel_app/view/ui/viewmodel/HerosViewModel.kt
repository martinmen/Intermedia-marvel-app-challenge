package ar.com.unlam.marvel_app.view.ui.viewmodel

import Results
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.unlam.marvel_app.data.model.network.MarvelServiceImpl
import BaseMarvelResponse
import kotlinx.coroutines.launch

class HerosViewModel:ViewModel(){
    var _charactersListLiveData = MutableLiveData<List<BaseMarvelResponse>>()
    val characters = MutableLiveData<BaseMarvelResponse>()
    val listaPersonajes = MutableLiveData<List<Results>>()
    val status = MutableLiveData<Status>()

    enum class Status {
        SUCCES,
        ERROR,
    }

    init {
    }

    fun getRecyclerListObserver(): MutableLiveData<List<Results>>{
        return listaPersonajes
    }

    fun getHerores() {
        viewModelScope.launch {
            try {
                val servicio = MarvelServiceImpl()
                listaPersonajes.value =  servicio.getHeroes().data.results
                Log.d(" characters.value", listaPersonajes.value.toString())
              //  _charactersListLiveData.value = charactersRepository.getHeroes(1,15)
                status.value = Status.SUCCES
            } catch (e: Exception) {
                status.value = Status.ERROR
            }
        }
    }
}