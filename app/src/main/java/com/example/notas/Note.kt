package com.example.notas
 // data clas para crear las variabls donde se guardaran las notas , su estado y si son visibles finales
data class Note(var id: Long = 0 , var descripcion : String= "", var esfinal : Boolean = false  ) {
}