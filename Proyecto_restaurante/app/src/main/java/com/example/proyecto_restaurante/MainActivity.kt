package com.example.proyecto_restaurante

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.proyecto_restaurante.pedidos.Plato
import com.example.proyecto_restaurante.pedidos.Pedido
import java.text.NumberFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var etCantidadPastel: EditText
    private lateinit var etCantidadCazuela: EditText
    private lateinit var switchPropina: Switch
    private lateinit var tvCalculoPastel: TextView
    private lateinit var tvCalculoCazuela: TextView
    private lateinit var tvTotalComida: TextView
    private lateinit var tvTotalPropina: TextView
    private lateinit var tvTotalFinal: TextView

    private lateinit var pastelDeChoclo: Plato
    private lateinit var cazuela: Plato
    private lateinit var pedido: Pedido

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        etCantidadPastel = findViewById(R.id.EtCantidadpastel)
        etCantidadCazuela = findViewById(R.id.EtCantidadcazuela)
        switchPropina = findViewById(R.id.SwitchPropina)
        tvCalculoPastel = findViewById(R.id.TvCalculoPastel)
        tvCalculoCazuela = findViewById(R.id.TvCalculoCazuela)
        tvTotalComida = findViewById(R.id.TvTotalcomida)
        tvTotalPropina = findViewById(R.id.TvTotalpropina)
        tvTotalFinal = findViewById(R.id.TvTotalfinal)


        pastelDeChoclo = Plato("Pastel de Choclo", 12000)
        cazuela = Plato("Cazuela", 10000)


        pedido = Pedido(listOf(pastelDeChoclo, cazuela))


        etCantidadPastel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                pastelDeChoclo.cantidad = cantidad
                actualizarValores()
            }
        })

        etCantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                cazuela.cantidad = cantidad
                actualizarValores()
            }
        })

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            pedido.setIncluirPropina(isChecked)
            actualizarValores()
        }


        actualizarValores()
    }


    private fun actualizarValores() {
        val currencyFormat = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        tvCalculoPastel.text = currencyFormat.format(pastelDeChoclo.calcularSubtotal())
        tvCalculoCazuela.text = currencyFormat.format(cazuela.calcularSubtotal())
        tvTotalComida.text = currencyFormat.format(pedido.calcularSubtotal())
        tvTotalPropina.text = currencyFormat.format(pedido.calcularPropina())
        tvTotalFinal.text = currencyFormat.format(pedido.calcularTotal())
    }
}
