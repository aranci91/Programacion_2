package com.example.proyecto_restaurante.pedidos

class Pedido(
    private val platos: List<Plato>,
    private var incluirPropina: Boolean = false
) {

    fun calcularSubtotal(): Int {
        return platos.sumOf { it.calcularSubtotal() }
    }

    fun calcularPropina(): Int {
        return if (incluirPropina) (calcularSubtotal() * 0.1).toInt() else 0
    }

    fun calcularTotal(): Int {
        return calcularSubtotal() + calcularPropina()
    }

    fun setIncluirPropina(valor: Boolean) {
        incluirPropina = valor
    }
}


