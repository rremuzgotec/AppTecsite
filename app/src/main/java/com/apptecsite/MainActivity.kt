package com.apptecsite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun irAReservaPolideportivos(view: View) {
        val intent = Intent(this, ReservaPolideportivosActivity::class.java)
        startActivity(intent)
    }

    fun irAReservaLaboratorios(view: View) {
        val intent = Intent(this, ReservaLaboratoriosActivity::class.java)
        startActivity(intent)
    }

    fun irAMisReservas(view: View) {
        val intent = Intent(this, MisReservasActivity::class.java)
        startActivity(intent)
    }

    fun cerrarSesion(view: View?) {
        val intent = Intent(this@MainActivity, SignInActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        finish()
    }
}