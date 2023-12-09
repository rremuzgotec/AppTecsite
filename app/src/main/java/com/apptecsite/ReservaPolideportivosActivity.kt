package com.apptecsite

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity

class ReservaPolideportivosActivity : AppCompatActivity() {

    private lateinit var etFecha: EditText
    private lateinit var etHora: EditText
    private lateinit var etCodigoEstudiante: EditText
    private lateinit var etDNI: EditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_polideportivos)

        // Asignar referencias a los elementos del XML
        etFecha = findViewById(R.id.etFecha)
        etHora = findViewById(R.id.etHora)
        etCodigoEstudiante = findViewById(R.id.etCodigoEstudiante)
        etDNI = findViewById(R.id.etDNI)

        val btnConfirmarReserva: Button = findViewById(R.id.btnConfirmarReserva)
        btnConfirmarReserva.setOnClickListener {
            confirmarReserva()
        }

        // Obtener la instancia de SharedPreferences
        sharedPreferences = getSharedPreferences("Reservas", Context.MODE_PRIVATE)
    }

    private fun confirmarReserva() {
        // Obteniendo los datos ingresados en el formulario
        val tipoReserva = obtenerTipoReservaSeleccionado()
        val fecha = obtenerFechaReserva()
        val hora = obtenerHoraReserva()
        val codigoEstudiante = obtenerCodigoEstudiante()
        val dni = obtenerNumeroDNI()

        // Guardar los datos de reserva usando SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("tipoReserva", tipoReserva)
        editor.putString("fecha", fecha)
        editor.putString("hora", hora)
        editor.putString("codigoEstudiante", codigoEstudiante)
        editor.putString("dni", dni)
        editor.apply()

        // Crear un Intent para enviar los datos a MisReservasActivity
        val intent = Intent(this@ReservaPolideportivosActivity, MisReservasActivity::class.java)
        startActivity(intent)
    }

    private fun obtenerTipoReservaSeleccionado(): String {
        // Lógica para obtener el tipo de reserva seleccionado (RadioButton seleccionado)
        val opcionesReserva: RadioGroup = findViewById(R.id.opcionesReserva)
        val radioButtonSeleccionado: RadioButton = findViewById(opcionesReserva.checkedRadioButtonId)
        return radioButtonSeleccionado.text.toString()
    }

    private fun obtenerFechaReserva(): String {
        // Obtener la fecha del EditText etFecha
        return etFecha.text.toString()
    }

    private fun obtenerHoraReserva(): String {
        // Obtener la hora del EditText etHora
        return etHora.text.toString()
    }

    private fun obtenerCodigoEstudiante(): String {
        // Obtener el código de estudiante del EditText etCodigoEstudiante
        return etCodigoEstudiante.text.toString()
    }

    private fun obtenerNumeroDNI(): String {
        // Obtener el número de DNI del EditText etDNI
        return etDNI.text.toString()
    }
}
