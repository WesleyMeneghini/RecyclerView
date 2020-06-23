package com.example.recyclerviewds3t.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewds3t.R
import com.example.recyclerviewds3t.model.Dentista
import kotlinx.android.synthetic.main.layout_lista_dentistas.view.*

class DentistaRecyclerAdapter (var lista: List<Dentista>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.layout_lista_dentistas, parent, false)

        return DentistaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        
        when(holder){
            is DentistaViewHolder -> {
                holder.bind(lista[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    class DentistaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val txtNome: TextView = itemView.txtNome
        val txtEmail: TextView = itemView.txtEmail
        val img: ImageView = itemView.img

        fun bind(dentista: Dentista){
            txtNome.text = dentista.nome
            txtEmail.text = dentista.email
        }

    }
}