package com.isc.proyecto.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.isc.proyecto.databinding.TareaFilaBinding
import com.isc.proyecto.model.Tarea
import com.isc.proyecto.ui.tarea.TareaFragmentDirections

class TareaAdapter: RecyclerView.Adapter<TareaAdapter.TareaViewHolder> (){

    private var listaTareas = emptyList<Tarea>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TareaAdapter.TareaViewHolder {
       val itemBinding = TareaFilaBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TareaViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: TareaViewHolder, position: Int) {

        val tareaActual = listaTareas[position]
        holder.bind(tareaActual)
    }

    override fun getItemCount(): Int {return listaTareas.size}

    fun setData(tareas:List<Tarea>){
        this.listaTareas=tareas
        notifyDataSetChanged()
    }

    inner class TareaViewHolder(private val itemBinding:TareaFilaBinding):
    RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(tarea: Tarea){
            itemBinding.tvTitulo.text=tarea.tituloTarea
            itemBinding.tvDescripcion.text=tarea.DescTarea
            itemBinding.tvFecha.text=tarea.fechaEntrega
            itemBinding.vistaTarea.setOnClickListener{
                val action = TareaFragmentDirections
                    .actionNavTareaToUpdateTareaFragment(tarea)
                itemView.findNavController().navigate(action)
            }

        }
    }
}