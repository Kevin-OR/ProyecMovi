package com.isc.proyecto.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.isc.proyecto.model.Tarea


@Dao
interface TareaDao {
    @Query("SELECT * FROM Tarea")
    fun getAllData():LiveData<List<Tarea>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTarea(tarea: Tarea)

    @Update
    suspend fun updateTarea(tarea: Tarea)

    @Delete
    suspend fun deleteTarea(tarea: Tarea)
}