package com.example.notas


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),OnClickListener {
    //variable global par ala actividadm principal
    private lateinit var binding:ActivityMainBinding
    // variable global del adaptador
    private lateinit var  noteadaptador :noteAdapter
    private lateinit var  notefinishAdapter: noteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)

         val btn2 : Button =findViewById(R.id.btn2)
        btn2.setOnClickListener {
            val intent:Intent =  Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        noteadaptador = noteAdapter( mutableListOf(),this)
        binding.rvNotes.apply{
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=noteadaptador
        }

        //se agrega lo de la nota al terminado
        notefinishAdapter = noteAdapter( mutableListOf() ,this)
        binding.rvNotesFinish.apply{
            layoutManager= LinearLayoutManager(this@MainActivity)
            adapter=notefinishAdapter
        }

        //en cso de que no ste vacio & añadir en tiempo real
        binding.btnAdd.setOnClickListener {
            if(binding.etDescription.text.toString().isNotBlank()){
                val note = Note((noteadaptador.itemCount + 1).toLong(),binding.etDescription.text.toString().trim() )
                // agregamos nota en tiempo de ejecucion
                addNoteAuto(note)
                //limpiamos la area de etdesc
                binding.etDescription.text?.clear()
            }else{
                binding.etDescription.error="CAMPO REQUERIDO"
            }
                }
    }
    //ciclo de visa de actividad
    override fun onStart(){
       super.onStart()
        getData()

    }

    //funcion para estanciar los datos
    private  fun getData(){
     val data = mutableListOf(
         Note (1,"estudiar "))
     data.forEach{
         note->addNoteAuto(note)

     }
 }

    //para agregar nota
    private fun addNoteAuto(note: Note) {
   if(note.esfinal){
       notefinishAdapter.add(note)
      }
     else{
       noteadaptador.add(note)
     }
    }

     //funcion par acuando eliminamos
    private fun deleteNoteAuto(note: Note) {
        if (note.esfinal){
        noteadaptador.remove(note)}
        else{
            notefinishAdapter.remove(note)
        }
    }
   //funcion para eliminar una nota , se usa un long (click sostenido)
    fun onLongClick(note: Note, noteAdapter: noteAdapter) {
        var builder=AlertDialog.Builder(this)
            .setTitle(getString(R.string.Dialog_title) )
                .setPositiveButton(getString(R.string.Dialos_final),{dialoInterface, i->
                    noteAdapter.remove(note)
            })
            .setNegativeButton("cancelar",null)
        builder.create().show()
    }

    //funcion para mover entre listados
    fun onChecked(note: Note){
     deleteNoteAuto(note)
      addNoteAuto(note)
}

    // menu de oocpiones
    override fun onCreateOptionsMenu(menu: Menu, ): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_opciones,menu)
        return true
    }
  // cuando el menu esta selecionado
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()){
            R.id.itemNuevo -> Toast.makeText(this,"nuevo",Toast.LENGTH_LONG).show();
            R.id.itemCalendar -> Toast.makeText(this,"opciones",Toast.LENGTH_LONG).show();
            R.id.itemAyuda -> Toast.makeText(this,"Necesitas ayuda?",Toast.LENGTH_LONG).show();
            R.id.itemSalir -> Toast.makeText(this,"Deseas salir?",Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item);
    }

    override fun onClick(v: View?) {
    }

}