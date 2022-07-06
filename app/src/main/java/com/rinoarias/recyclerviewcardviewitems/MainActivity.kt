package com.rinoarias.recyclerviewcardviewitems

import Utils.Endpoint
import Utils.LocaleLanguage
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Glide.with(this).
            load("https://www.uteq.edu.ec/images/page/4/l_uteq.png").
            into(findViewById(R.id.imgLogo))

        val lenguajes = enumValues<LocaleLanguage>()
        val cboIdiomas = findViewById<Spinner>(R.id.cboIdiomas)
        var adaptador = ArrayAdapter<LocaleLanguage>(
            this,
            android.R.layout.simple_spinner_item,
            lenguajes)
        adaptador.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        cboIdiomas.adapter = adaptador
        cboIdiomas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                cboIdiomas.tooltipText = "Seleccione un idioma"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
        }
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            startActivity(Intent(
                this,
                RevistasActivity::class.java
            ))
        }
    }
}
