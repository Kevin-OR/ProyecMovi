package com.isc.proyecto.ui.tarea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.isc.proyecto.R
import com.isc.proyecto.databinding.FragmentAddTareaBinding
import com.isc.proyecto.model.Tarea
import com.isc.proyecto.viewmodel.TareaViewModel


class AddTareaFragment : Fragment() {

    private var _binding: FragmentAddTareaBinding?=null
    private val binding get() = _binding!!

    private lateinit var tareaViewModel:TareaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTareaBinding.inflate(layoutInflater,container,false)

        tareaViewModel = ViewModelProvider(this).get(TareaViewModel::class.java)

        binding.btAdd.setOnClickListener{insertaTarea()}

        return binding.root
    }

    private fun insertaTarea() {
        val titulo = binding.etTitulo.text.toString()
        val desc = binding.etDescTarea.text.toString()
        val curso = binding.etCurso.text.toString()
        val fecha = binding.etFechaEntrega.text.toString()
        if(validos(titulo,desc,curso,fecha)){
            val tarea = Tarea (0,titulo,desc,curso,fecha)
            tareaViewModel.addTarea(tarea)
            Toast.makeText(requireContext(),getString(R.string.msgTareaAgregada),Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(),getString(R.string.msgFaltanDatos),Toast.LENGTH_LONG).show()
        }
    }

    private fun validos(titulo: String, desc: String, curso: String, fecha: String): Boolean {
    return !(titulo.isEmpty() || desc.isEmpty()||curso.isEmpty()||fecha.isEmpty())
    }

}