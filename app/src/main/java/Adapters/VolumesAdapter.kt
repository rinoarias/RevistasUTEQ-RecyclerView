package Adapters

import Clases.Volume
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rinoarias.recyclerviewcardviewitems.ArticulosActivity
import com.rinoarias.recyclerviewcardviewitems.VolumesActivity
import com.rinoarias.recyclerviewcardviewitems.databinding.ItemVolumeBinding

class VolumesAdapter : RecyclerView.Adapter<VolumesAdapter.ViewHolder>() {

    var volumesList: ArrayList<Volume> = ArrayList()
    lateinit var context: Context

    class ViewHolder(val binding: ItemVolumeBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    fun VolumesAdapter(listVolumes: ArrayList<Volume>, mContext: Context){
        this.volumesList = listVolumes
        this.context = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemVolumeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemVolume = volumesList.get(position)
        holder.binding.txtVolumenDescription.text = "Vol. " + itemVolume.volume +
                " Num. " + itemVolume.number
        holder.binding.txtDoi.text = "Doi: " + itemVolume.doi
        holder.binding.txtFechaPublicado.text = "Publicado: " + itemVolume.datePublished
        //Cargar imagen con Glide
        Glide.with(context).load(itemVolume.cover).into(holder.binding.imgVolumen)

        holder.binding.infoVolumen.setOnClickListener {
            Toast.makeText(
                context,
                "Volumen Seleccionado: " + itemVolume.issueID,
                Toast.LENGTH_SHORT).show()

            //agregar funcionalidad de llamar nueva actividad con volumenes de revista
            val act = Intent(context.applicationContext, ArticulosActivity::class.java)
            act.putExtra("volumen", volumesList[position])
            act.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.getApplicationContext().startActivity(act)
        }
    }

    override fun getItemCount(): Int {
        return volumesList.size
    }

}