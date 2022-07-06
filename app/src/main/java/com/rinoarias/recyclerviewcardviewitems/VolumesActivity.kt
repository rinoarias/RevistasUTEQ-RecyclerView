package com.rinoarias.recyclerviewcardviewitems

import Adapters.JournalsAdapter
import Adapters.VolumesAdapter
import Clases.Journal
import Clases.Volume
import Utils.Endpoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.rinoarias.recyclerviewcardviewitems.databinding.ActivityRevistasBinding
import com.rinoarias.recyclerviewcardviewitems.databinding.ActivityVolumesBinding
import com.rinoarias.recyclerviewcardviewitems.databinding.ItemVolumeBinding
import java.lang.Exception

class VolumesActivity : AppCompatActivity() {
    lateinit var binding: ActivityVolumesBinding
    private var adapter: VolumesAdapter = VolumesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVolumesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datosRevista = intent.getSerializableExtra("revista") as Journal

        val lstVolumenes = ArrayList<Volume>()

        val cola = Volley.newRequestQueue(this)

        val solicitud = JsonArrayRequest(Request.Method.GET,
        Endpoint.ISSUES.path + "?j_id=" + datosRevista.journalID,
            null,
            { response ->
                try {
                    for (i in 0 until response.length()){
                        var item = response.getJSONObject(i)
                        lstVolumenes.add(Volume(item))
                    }
                    binding.rvVolumenes.setHasFixedSize(true)
                    binding.rvVolumenes.layoutManager = LinearLayoutManager(this)
                    adapter.VolumesAdapter(lstVolumenes, this)
                    binding.lblTitulo.text = datosRevista.journalName
                } catch (e: Exception){
                    Toast.makeText(this,
                    "No se cargaron los datos",
                    Toast.LENGTH_SHORT)
                }
            }, {
                Toast.makeText(this,
                "Error al cargar datos de revista",
                Toast.LENGTH_SHORT).show()
            })
        cola.add(solicitud)

    }
}