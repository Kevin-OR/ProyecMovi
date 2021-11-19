package com.isc.proyecto.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tarea")
data class Tarea(
    @PrimaryKey(autoGenerate = true)
    val idTarea:Int,
    @ColumnInfo(name="tituloTarea")
    val tituloTarea: String?,
    @ColumnInfo(name="descTarea")
    val DescTarea: String?,
    @ColumnInfo(name="curso")
    val curso: String?,
    @ColumnInfo(name="fechaEntrega")
    val fechaEntrega: String?
): Parcelable
