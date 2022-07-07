package com.rinoarias.recyclerviewcardviewitems

import Adapters.ArticlesAdapter
import Clases.Article
import Clases.Volume
import Utils.Endpoint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.rinoarias.recyclerviewcardviewitems.databinding.ActivityArticulosBinding
import java.lang.Exception

class ArticulosActivity : AppCompatActivity() {
    lateinit var binding: ActivityArticulosBinding
    private var adapter : ArticlesAdapter = ArticlesAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticulosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val datosVolumen = intent.getSerializableExtra("volumen") as Volume
        val lstArticulos = ArrayList<Article>()
        val cola = Volley.newRequestQueue(this)
        val solicitud = JsonArrayRequest(Request.Method.GET,
        Endpoint.PUBS.path + "?i_id=" + datosVolumen.issueID,
        null,
            { response ->
                try {
                    for (i in 0 until response.length()){
                        var item = response.getJSONObject(i)
                        lstArticulos.add(Article(item))
                    }
                    binding.rvArticulos.setHasFixedSize(true)
                    binding.rvArticulos.layoutManager = LinearLayoutManager(this)
                    adapter.ArticlesAdapter(lstArticulos, this)
                    binding.rvArticulos.adapter = adapter
                    binding.lblTitulo.text = "Vol. " + datosVolumen.volume +
                            " Num. " + datosVolumen.number + ":\n" +
                            "Revista "
                } catch (e: Exception){
                    Toast.makeText(this,
                        "No se cargaron los datos",
                        Toast.LENGTH_SHORT)
                }
            }, {
                Toast.makeText(this,
                    "Error al cargar datos del volumen",
                    Toast.LENGTH_SHORT).show()
            })
        cola.add(solicitud)
    }
}