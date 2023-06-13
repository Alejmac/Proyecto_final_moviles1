package com.example.notas

interface onClickListener {
    // definir un solo metodo par aeliminar nuestra nota
    fun onLongClick(note:Note,currentAdapter: noteAdapter)
    // definimos un metodo para verel estado de las notas
    fun onChecked(note: Note)
}