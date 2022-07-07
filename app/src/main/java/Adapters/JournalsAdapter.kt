package Adapters

import Clases.Journal
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rinoarias.recyclerviewcardviewitems.VolumesActivity
import com.rinoarias.recyclerviewcardviewitems.databinding.ItemRevistaBinding

class JournalsAdapter : RecyclerView.Adapter<JournalsAdapter.ViewHolder>() {

    var journalList: ArrayList<Journal> = ArrayList()
    lateinit var context: Context

    class ViewHolder(val binding: ItemRevistaBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    fun JournalsAdapter(listJournals: ArrayList<Journal>, mContext: Context){
        this.journalList = listJournals
        this.context = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemRevistaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
        false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemRevista = journalList.get(position)
        holder.binding.txtNombreRevista.text = itemRevista.journalName
        holder.binding.txtAbreviatura.text = itemRevista.journalAbbreviation
        //Cargar imagen con Glide
        Glide.with(context).load(itemRevista.journalPortada).into(holder.binding.imgRevista)

        holder.binding.infoRevista.setOnClickListener {
            Toast.makeText(
                context,
                "Revista Seleccionada: " + itemRevista.journalName,
                Toast.LENGTH_SHORT).show()

            //agregar funcionalidad de llamar nueva actividad con volumenes de revista
            val act = Intent(context.applicationContext, VolumesActivity::class.java)
            act.putExtra("revista", journalList[position])
            act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.getApplicationContext().startActivity(act)
        }
    }

    override fun getItemCount(): Int {
        return journalList.size
    }

}