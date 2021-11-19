package com.isc.proyecto.ui.tarea

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.isc.proyecto.R
import com.isc.proyecto.databinding.FragmentUpdateTareaBinding
import com.isc.proyecto.model.Tarea
import com.isc.proyecto.viewmodel.TareaViewModel


class UpdateTareaFragment : Fragment() {
    private lateinit var tareaViewModel: TareaViewModel
    private var _binding: FragmentUpdateTareaBinding?=null
    private val binding get() = _binding!!

    private val args by navArgs<UpdateTareaFragmentArgs>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        tareaViewModel = ViewModelProvider(this).get(TareaViewModel::class.java)
        _binding = FragmentUpdateTareaBinding.inflate(inflater,container,false)

        binding.etTitulo.setText(args.tarea.tituloTarea)
        binding.etDescTarea.setText(args.tarea.DescTarea)
        binding.etCurso.setText(args.tarea.curso)
        binding.etFechaEntrega.setText(args.tarea.fechaEntrega)


        binding.btActualizarTarea.setOnClickListener{
            modificarTarea()}
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId==R.id.menu_delete){
            deleteTarea()
        }
        return super.onOptionsItemSelected(item)
    }



    private fun modificarTarea() {
        val titulo = binding.etTitulo.text.toString()
        val desc = binding.etDescTarea.text.toString()
        val curso = binding.etCurso.text.toString()
        val fecha = binding.etFechaEntrega.text.toString()
        if(validos(titulo,desc,curso,fecha)){
            val tarea = Tarea (args.tarea.idTarea,titulo,desc,curso,fecha)
            tareaViewModel.updateTarea(tarea)
            Toast.makeText(requireContext(),getString(R.string.msgTareaAgregada),Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(requireContext(),getString(R.string.msgFaltanDatos),Toast.LENGTH_LONG).show()
        }
    }

    private fun validos(titulo: String, desc: String, curso: String, fecha: String): Boolean {
    return !(titulo.isEmpty() || desc.isEmpty()||curso.isEmpty()||fecha.isEmpty())
    }

    private fun deleteTarea(){
        val builder =AlertDialog.Builder(requireContext())
        builder.setPositiveButton(getString(R.string.si)){_,_->
            tareaViewModel.deleteTarea(args.tarea)
            Toast.makeText(requireContext(),
                getString(R.string.deleted)+"${args.tarea.tituloTarea}!",
            Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateTareaFragment_to_nav_tarea)
        }
        builder.setNegativeButton(getString(R.string.no)){_,_->}
        builder.setTitle(R.string.deleted)
        builder.setMessage(getString(R.string.seguroBorrar)+
                "${args.tarea.tituloTarea}?")
        builder.create().show()
    }
}