package com.apptecsite

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MisReservasActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_reservas)

        val sharedPreferences = getSharedPreferences("Reservas", Context.MODE_PRIVATE)

        val tipoReservaPolideportivo = sharedPreferences.getString("tipoReservaPolideportivo", "")
        val fechaPolideportivo = sharedPreferences.getString("fechaPolideportivo", "")
        val horaPolideportivo = sharedPreferences.getString("horaPolideportivo", "")

        val tipoReservaLaboratorio = sharedPreferences.getString("tipoReservaLaboratorio", "")
        val fechaLaboratorio = sharedPreferences.getString("fechaLaboratorio", "")
        val horaLaboratorio = sharedPreferences.getString("horaLaboratorio", "")

        Log.d("MisReservasActivity", "Tipo Reserva Polideportivo: $tipoReservaPolideportivo")
        Log.d("MisReservasActivity", "Fecha Reserva Polideportivo: $fechaPolideportivo")
        Log.d("MisReservasActivity", "Hora Reserva Polideportivo: $horaPolideportivo")
        Log.d("MisReservasActivity", "Tipo Reserva Laboratorio: $tipoReservaLaboratorio")
        Log.d("MisReservasActivity", "Fecha Reserva Laboratorio: $fechaLaboratorio")
        Log.d("MisReservasActivity", "Hora Reserva Laboratorio: $horaLaboratorio")

        val tvTipoReservaPolideportivo: TextView = findViewById(R.id.tvTipoReservaPolideportivo)
        val tvFechaReservaPolideportivo: TextView = findViewById(R.id.tvFechaReservaPolideportivo)
        val tvHoraReservaPolideportivo: TextView = findViewById(R.id.tvHoraReservaPolideportivo)

        val tvTipoReservaLaboratorio: TextView = findViewById(R.id.tvTipoReservaLaboratorio)
        val tvFechaReservaLaboratorio: TextView = findViewById(R.id.tvFechaReservaLaboratorio)
        val tvHoraReservaLaboratorio: TextView = findViewById(R.id.tvHoraReservaLaboratorio)

        if (!tipoReservaPolideportivo.isNullOrEmpty()) {
            tvTipoReservaPolideportivo.text =
                "Campo a reservar (Polideportivo): $tipoReservaPolideportivo"
            tvFechaReservaPolideportivo.text = "Fecha de Reserva: $fechaPolideportivo"
            tvHoraReservaPolideportivo.text = "Hora de Reserva: $horaPolideportivo"
        } else {
            tvTipoReservaPolideportivo.visibility = View.GONE
            tvFechaReservaPolideportivo.visibility = View.GONE
            tvHoraReservaPolideportivo.visibility = View.GONE
        }

        if (!tipoReservaLaboratorio.isNullOrEmpty()) {
            tvTipoReservaLaboratorio.text =
                "Campo a reservar (Laboratorio): $tipoReservaLaboratorio"
            tvFechaReservaLaboratorio.text = "Fecha de Reserva: $fechaLaboratorio"
            tvHoraReservaLaboratorio.text = "Hora de Reserva: $horaLaboratorio"
        } else {
            tvTipoReservaLaboratorio.visibility = View.GONE
            tvFechaReservaLaboratorio.visibility = View.GONE
            tvHoraReservaLaboratorio.visibility = View.GONE
        }

        val btnVolverMenuPrincipal: Button = findViewById(R.id.btnVolverMenuPrincipal)
        btnVolverMenuPrincipal.setOnClickListener {

            val intent = Intent(this@MisReservasActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val btnCancelarReservasPolideportivo: Button =
            findViewById(R.id.btnCancelarReservaPolideportivo)
        val btnCancelarReservasLaboratorio: Button =
            findViewById(R.id.btnCancelarReservaLaboratorio)

        if (!tipoReservaPolideportivo.isNullOrEmpty()) {
            btnCancelarReservasPolideportivo.visibility = View.VISIBLE
            btnCancelarReservasPolideportivo.setOnClickListener {

                sharedPreferences.edit().remove("tipoReservaPolideportivo").apply()
                recreate()
            }
        } else {
            btnCancelarReservasPolideportivo.visibility = View.GONE
        }

        if (!tipoReservaLaboratorio.isNullOrEmpty()) {
            btnCancelarReservasLaboratorio.visibility = View.VISIBLE
            btnCancelarReservasLaboratorio.setOnClickListener {

                sharedPreferences.edit().remove("tipoReservaLaboratorio").apply()
                recreate()
            }
        } else {
            btnCancelarReservasLaboratorio.visibility = View.GONE
        }
    }
}
