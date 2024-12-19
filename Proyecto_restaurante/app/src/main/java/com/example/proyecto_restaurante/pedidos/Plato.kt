package com.example.proyecto_restaurante.pedidos

data class Plato(
    val nombre : String,
    val precio: Int,
    var cantidad: Int = 0

){


    fun calcularSubtotal(): Int {
        return cantidad * precio

    }
}