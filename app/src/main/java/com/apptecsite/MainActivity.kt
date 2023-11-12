package com.apptecsite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

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
}