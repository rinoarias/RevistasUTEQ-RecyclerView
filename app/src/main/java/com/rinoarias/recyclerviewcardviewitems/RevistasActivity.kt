package com.rinoarias.recyclerviewcardviewitems

import Adapters.JournalsAdapter
import Clases.Journal
import Utils.Endpoint
import Utils.LocaleLanguage
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.rinoarias.recyclerviewcardviewitems.databinding.ActivityRevistasBinding

class RevistasActivity : AppCompatActivity() {

    lateinit var binding: ActivityRevistasBinding
    private var adapter: JournalsAdapter = JournalsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRevistasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var lstRevistas = ArrayList<Journal>()

        val cola = Volley.newRequestQueue(this)
        val intent = intent.extras
        val idiomaSeleccionado = intent?.getString("localeLenguage").toString()
        var url = Endpoint.JOURNALS.path
        if(idiomaSeleccionado != LocaleLanguage.SPANISH.language) {
            url = "$url&locale=$idiomaSeleccionado"
        }

        val solicitud = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    for (i in 0 until response.length()){
                        var item = response.getJSONObject(i)
                        lstRevistas.add(Journal(item))
                    }
                    binding.rvRevistas.setHasFixedSize(true)
                    binding.rvRevistas.layoutManager = LinearLayoutManager(this)
                    adapter.JournalsAdapter(lstRevistas, this)
                    binding.rvRevistas.adapter = adapter
                    binding.lblTitulo.text = "LISTA DE REVISTAS"
                    //Toast.makeText(this, "Descarga Exitosa", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this, "Error al obtener los datos: $e", Toast.LENGTH_LONG).show()
                    System.out.println(e.toString())
                }
            }, {
                Toast.makeText(this, "Error al obtener los datos: $it", Toast.LENGTH_LONG).show()
                System.out.println(it.toString())
            })
        cola.add(solicitud)


    }

}