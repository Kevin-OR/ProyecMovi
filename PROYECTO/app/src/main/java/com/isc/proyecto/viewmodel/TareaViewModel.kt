package com.isc.proyecto.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.isc.proyecto.data.TareaDao
import com.isc.proyecto.data.TareaDatabase
import com.isc.proyecto.model.Tarea
import com.isc.proyecto.repository.TareaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TareaViewModel(application: Application):AndroidViewModel(application) {

    val getAllData:LiveData<List<Tarea>>
    private val repository : TareaRepository

    init {
        val tareaDao = TareaDatabase.getDatabase(application).tareaDao()
        repository = TareaRepository(tareaDao)
        getAllData = repository.getAllData

    }

    fun addTarea(tarea: Tarea){
        viewModelScope.launch (Dispatchers.IO){repository.addTarea(tarea)}
    }

    fun updateTarea(tarea: Tarea){
        viewModelScope.launch (Dispatchers.IO){repository.updateTarea(tarea)}
    }

    fun deleteTarea(tarea: Tarea){
        viewModelScope.launch (Dispatchers.IO){repository.deleteTarea(tarea)}
    }

}

