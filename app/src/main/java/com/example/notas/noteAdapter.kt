package com.example.notas

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.notas.databinding.ItemNoteBinding


    // construir un adaptador en kotlin para conectar la data class con la vista
    // se implementa el recyclerview
class noteAdapter(var notelist: MutableList<Note>, private val listener: MainActivity)
    :RecyclerView.Adapter<noteAdapter.ViewHolder> (){

    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        //variable de solo lectura
        context = parent.context
        // infamos el xml item_note , para asignarlo al listview
        val view= LayoutInflater.from(parent.context).inflate(
            R.layout.item_note,parent,false
        )
        return ViewHolder(view)
    }

    // clase que nos permite inflar la vista
    inner class ViewHolder(view: View ): RecyclerView.ViewHolder(view){
        // con esto se infla la clase adapter
        var binding = ItemNoteBinding.bind(view)

        fun setListener(note: Note) {
            //detecta cuando cambia el valor de el chechbox
            binding.cbFinal.setOnClickListener{
                note.esfinal = (it as CheckBox ).isChecked
                    listener.onChecked(note)
            }
            binding.root.setOnLongClickListener{
                listener.onLongClick(note,this@noteAdapter)
                true
            }

        }
    }

    // nos idnidca cuantoselementos hay en el listado
    override fun getItemCount(): Int {
        return notelist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //asignamos valor a la propiedad descriccion
        val note = notelist.get(position)
        // menadmos el holder a la funcion de setlistener para eliminar
        holder.setListener(note)

        // accedemos a la vista
        holder.binding.tvDescripcion.text= note.descripcion
        holder.binding.cbFinal.isChecked = note.esfinal

        //definimos cuando esta seleccionado un check
        if(note.esfinal){
            holder.binding.tvDescripcion.setTextSize(TypedValue.COMPLEX_UNIT_SP,12f)
        }
        else{
            holder.binding.tvDescripcion.setTextSize(TypedValue.COMPLEX_UNIT_SP,16f)
        }
    }

    // funcion para añadir un a nota
    fun add(note: Note) {
        notelist.add(note)
        // notificamos a la lista que se est aañadiendo un cmapo
        notifyDataSetChanged()
    }

    //funcion para eliminar una nota
    fun remove(note: Note) {
        notelist.remove(note)
        // notificamos a la lista que se est aañadiendo un cmapo
        notifyDataSetChanged()
    }
}