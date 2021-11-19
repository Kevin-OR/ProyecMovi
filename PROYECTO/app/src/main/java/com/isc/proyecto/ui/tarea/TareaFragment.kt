package com.isc.proyecto.ui.tarea

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.isc.proyecto.R
import com.isc.proyecto.adapter.TareaAdapter
import com.isc.proyecto.databinding.FragmentTareaBinding
import com.isc.proyecto.viewmodel.TareaViewModel

class TareaFragment : Fragment() {

    private lateinit var tareaViewModel: TareaViewModel
    private var _binding: FragmentTareaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tareaViewModel =
            ViewModelProvider(this).get(TareaViewModel::class.java)
        _binding = FragmentTareaBinding.inflate(inflater, container, false)

        binding.addTareaButton.setOnClickListener{
            findNavController().navigate(R.id.action_nav_tarea_to_addTareaFragment)
        }

        //activacion del recycler...

        val tareaAdapter = TareaAdapter()
        val reciclador = binding.reciclador
        reciclador.adapter = tareaAdapter
        reciclador.layoutManager= LinearLayoutManager(requireContext())

        //obtencion de data
        tareaViewModel= ViewModelProvider(this)
            .get(TareaViewModel::class.java)
        tareaViewModel.getAllData.observe(viewLifecycleOwner,{
            tareas-> tareaAdapter.setData(tareas)
        })
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}