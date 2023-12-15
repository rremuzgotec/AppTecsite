package com.apptecsite

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MisReservasActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_reservas)

        // Obtener una instancia de SharedPreferences
        val sharedPreferences = getSharedPreferences("Reservas", Context.MODE_PRIVATE)

        // Recuperar los datos de reserva guardados en SharedPreferences
        val tipoReserva = sharedPreferences.getString("tipoReserva", "")
        val fecha = sharedPreferences.getString("fecha", "")
        val hora = sharedPreferences.getString("hora", "")

        // Mostrar los datos en los campos correspondientes de activity_mis_reservas.xml
        val tvTipoReserva: TextView = findViewById(R.id.tvTipoReserva)
        val tvFechaReserva: TextView = findViewById(R.id.tvFechaReserva)
        val tvHoraReserva: TextView = findViewById(R.id.tvHoraReserva)

        tvTipoReserva.text = "Campo a reservar: $tipoReserva"
        tvFechaReserva.text = "Fecha de Reserva: $fecha"
        tvHoraReserva.text = "Hora de Reserva: $hora"

        // Botón "Volver al Menú Principal"
        val btnVolverMenuPrincipal: Button = findViewById(R.id.btnVolverMenuPrincipal)
        btnVolverMenuPrincipal.setOnClickListener {
            // Crear un Intent para abrir MainActivity
            val intent = Intent(this@MisReservasActivity, MainActivity::class.java)
            startActivity(intent)
            finish() // Finaliza la actividad actual para evitar volver a ella al presionar "Atrás"
        }

        // Botón "Cancelar Reserva"
        val btnCancelarReserva: Button = findViewById(R.id.btnCancelarReserva)

        // Verificar si hay reserva
        if (tipoReserva.isNullOrEmpty()) {
            // Si no hay reserva, ocultar el botón de Cancelar Reserva
            btnCancelarReserva.visibility = View.GONE
        } else {
            // Si hay una reserva, mostrar el botón de Cancelar Reserva
            btnCancelarReserva.visibility = View.VISIBLE
            btnCancelarReserva.setOnClickListener {
                // Aquí pondrías la lógica para cancelar la reserva
                // Por ejemplo, eliminar los datos de reserva en SharedPreferences
                sharedPreferences.edit().clear().apply()

                // Luego, ir a la actividad MainActivity
                val intent = Intent(this@MisReservasActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Finaliza la actividad actual
            }
        }
    }
}