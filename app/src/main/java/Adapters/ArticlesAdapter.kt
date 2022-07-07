package Adapters

import Clases.Article
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rinoarias.recyclerviewcardviewitems.ArticulosActivity
import com.rinoarias.recyclerviewcardviewitems.databinding.ItemArticuloBinding

class ArticlesAdapter : RecyclerView.Adapter<ArticlesAdapter.ViewHolder>() {
    var articlesList: ArrayList<Article> = ArrayList()
    lateinit var context: Context

    class ViewHolder(val binding: ItemArticuloBinding) : RecyclerView.ViewHolder(binding.root) {
    }

    fun ArticlesAdapter(listArticles: ArrayList<Article>, mContext: Context){
        this.articlesList = listArticles
        this.context = mContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemArticuloBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemArticulo = articlesList.get(position)
        holder.binding.txtTituloArticulo.text = itemArticulo.title
        holder.binding.txtDoiArticulo.text = itemArticulo.doi
        holder.binding.txtFechaArticulo.text = itemArticulo.datePublished

        holder.binding.infoArticulo.setOnClickListener {
            Toast.makeText(
                context,
                "Articulo Seleccionado: " + itemArticulo,
                Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return articlesList.size
    }
}