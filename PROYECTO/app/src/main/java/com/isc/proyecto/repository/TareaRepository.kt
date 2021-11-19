package com.isc.proyecto.repository

import androidx.lifecycle.LiveData
import com.isc.proyecto.data.TareaDao
import com.isc.proyecto.model.Tarea

class TareaRepository(private val tareaDao: TareaDao) {
    val getAllData:LiveData<List<Tarea>> = tareaDao.getAllData()

    suspend fun addTarea (tarea: Tarea){
        tareaDao.addTarea(tarea)
    }

    suspend fun updateTarea(tarea: Tarea){
        tareaDao.updateTarea(tarea)
    }

    suspend fun deleteTarea(tarea: Tarea){
        tareaDao.deleteTarea(tarea)
    }

}