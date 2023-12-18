package com.apptecsite

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ReservaPolideportivosActivity : AppCompatActivity() {

    private lateinit var etFecha: TextInputEditText
    private lateinit var etHora: EditText

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserva_polideportivos)

        etFecha = findViewById(R.id.etFecha)
        etHora = findViewById(R.id.etHora)


        etFecha.inputType = InputType.TYPE_NULL
        etFecha.setOnClickListener { showDatePickerDialog() }

        etHora.setOnClickListener { showTimePickerDialog() }

        val btnConfirmarReserva: Button = findViewById(R.id.btnConfirmarReserva)
        btnConfirmarReserva.setOnClickListener {
            confirmarReserva()
        }

        sharedPreferences = getSharedPreferences("Reservas", Context.MODE_PRIVATE)
    }

    private fun showDatePickerDialog() {
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = String.format(Locale.getDefault(), "%02d/%02d/%04d", dayOfMonth, month + 1, year)
                etFecha.setText(selectedDate)
            },
            Calendar.getInstance().get(Calendar.YEAR),
            Calendar.getInstance().get(Calendar.MONTH),
            Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun showTimePickerDialog() {
        val cal = Calendar.getInstance()
        val horaActual = cal.get(Calendar.HOUR_OF_DAY)
        val minutoActual = cal.get(Calendar.MINUTE)

        // Horas
        val horasDeseadas = arrayOf("8:00-9:00", "9:00-10:00", "10:00-11:00", "11:00-12:00", "12:00-13:00", "13:00-14:00", "14:00-15:00", "15:00-16:00", "16:00-17:00")

        val timePickerDialog = TimePickerDialog(
            this,
            TimePickerDialog.OnTimeSetListener { _, hora, minuto ->
                onTimeSet(hora, minuto, horasDeseadas)
            },
            horaActual,
            minutoActual,
            false
        )

        timePickerDialog.show()
    }

    private fun onTimeSet(hora: Int, minuto: Int, horasDeseadas: Array<String>) {
        val cal = Calendar.getInstance()
        cal.set(Calendar.HOUR_OF_DAY, hora)
        cal.set(Calendar.MINUTE, minuto)

        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val horaInicio = simpleDateFormat.format(cal.time)
        cal.add(Calendar.HOUR, 1)
        val horaFin = simpleDateFormat.format(cal.time)

        val rangoSeleccionado = "$horaInicio-$horaFin"
        if (horasDeseadas.contains(rangoSeleccionado)) {
            etHora.setText(rangoSeleccionado)
        } else {
            Toast.makeText(this, "Por favor, selecciona un rango de horas válido.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun confirmarReserva() {
        val tipoReserva = obtenerTipoReservaSeleccionado()
        val fecha = obtenerFechaReserva()
        val hora = obtenerHoraReserva()


        val editor = sharedPreferences.edit()
        editor.putString("tipoReservaPolideportivo", tipoReserva)
        editor.putString("fechaPolideportivo", fecha)
        editor.putString("horaPolideportivo", hora)
        editor.apply()

        val intent = Intent(this@ReservaPolideportivosActivity, MisReservasActivity::class.java)
        startActivity(intent)
    }

    private fun obtenerTipoReservaSeleccionado(): String {
        val opcionesReserva: RadioGroup = findViewById(R.id.opcionesReserva)
        val radioButtonSeleccionado: RadioButton = findViewById(opcionesReserva.checkedRadioButtonId)
        return radioButtonSeleccionado.text.toString()
    }

    private fun obtenerFechaReserva(): String {
        return etFecha.text.toString()
    }

    private fun obtenerHoraReserva(): String {
        return etHora.text.toString()
    }
}

