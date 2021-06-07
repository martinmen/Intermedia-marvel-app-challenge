package ar.com.unlam.marvel_app.view.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.com.unlam.marvel_app.data.model.Hero
import ar.com.unlam.marvel_app.data.model.network.MarvelServiceImpl
import baseResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//class HerosViewModel(private val charactersRepository: HeroRepostitoryImpl) : ViewModel()  {
class HerosViewModel:ViewModel(){
    var _charactersListLiveData = MutableLiveData<List<baseResponse>>()
    val characters = MutableLiveData<baseResponse>()
    val status = MutableLiveData<Status>()

    enum class Status {
        SUCCES,
        ERROR,
    }

    init {
        _charactersListLiveData = MutableLiveData()
    }


    fun getRecyclerListObserver(): MutableLiveData<List<baseResponse>>{
        return _charactersListLiveData
    }
   // private val _characters = MutableLiveData<List<Hero>>()
    //val characters: LiveData<List<Hero>> get() = _characters

    fun getHerores() {
        viewModelScope.launch {
            try {
                val Servicio = MarvelServiceImpl()

                _charactersListLiveData.value =  Servicio.getHeroes()
              //  _charactersListLiveData.value = charactersRepository.getHeroes(1,15)
                status.value = Status.SUCCES
            } catch (e: Exception) {
                status.value = Status.ERROR
            }
        }
    }
}