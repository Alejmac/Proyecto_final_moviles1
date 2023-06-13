package com.example.notas

import android.app.DatePickerDialog
import android.app.DatePickerDialog.*
import android.content.Intent
import android.graphics.ColorSpace.Rgb
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatDelegate
import com.example.notas.databinding.ActivityCalendarBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar
 // clase para la segunda vista
class CalendarActivity : AppCompatActivity() {

    // variables globales
    private lateinit var binding:ActivityCalendarBinding
    private lateinit var tvdatePicker :TextView
    private lateinit var btnDatePiker :Button
    private lateinit var sEstado:Spinner;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_calendar)
        // se binding para inflar la actividad
        binding = ActivityCalendarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // id de fecha
        tvdatePicker = binding.tvDatepiker
        btnDatePiker = binding.btnDate

        // spinner
        sEstado=binding.spEstado
        var listadO = arrayListOf("Completado","Encurso","Terminado",)
        val adaptador:ArrayAdapter<*>
        adaptador= ArrayAdapter(this,android.R.layout.simple_spinner_item,listadO)
        sEstado.adapter=adaptador

        // boton para regresar a la vista
        val btnActv1 : Button = findViewById(R.id.btnActv1)
           btnActv1.setOnClickListener {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        //radio buton group
            val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
         radioGroup.setOnCheckedChangeListener{group,checkedId->
             when(checkedId){
                 R.id.rbEscuela -> Toast.makeText(this,"Escogiste la Escuela", Toast.LENGTH_LONG).show();
                 R.id.rbHogar-> Toast.makeText(this,"Escogiste el Hogar", Toast.LENGTH_LONG).show();
                 R.id.rbTrabajo->Toast.makeText(this,"Escogiste el Trabajo", Toast.LENGTH_LONG).show();
             }
         }

        //se ins. el calendario con los datos
        val myCalendar = Calendar.getInstance()
        val datePicker = OnDateSetListener{view, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR,year)
            myCalendar.set(Calendar.MONTH,month)
            myCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)
            updateLable(myCalendar);
        }
        // metodo click cuando se presiona la fecha
        btnDatePiker.setOnClickListener{
        DatePickerDialog(this,datePicker,myCalendar.get(Calendar.YEAR),myCalendar.get(Calendar.MONTH),myCalendar.get(
        Calendar.DAY_OF_MONTH)).show()
        }
        // metodo cambiar a modo oscuro
        // se crea la variable
        val switch = findViewById<Switch>(R.id.Switch)
        // se l ascigna un metodo onChecked para checar el estado de
        switch.setOnCheckedChangeListener{ _ , _->
            if(switch.isChecked){
                // modo default para cambiar el estado de una actividad a modo oscuro
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                switch.text="Desactivar modo Oscuro"
            }else{
                // modo default para cambiar el estado de una actividad a modo normal
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                switch.text="Activar modo Oscuro"
            }
        }

    }

    //funcion para el datepikerdialog se establece un formato
    private fun updateLable(myCalendar: Calendar){
        val myFormat = "DD-MM-YY"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvdatePicker.text = sdf.format(myCalendar.time)
    }




}