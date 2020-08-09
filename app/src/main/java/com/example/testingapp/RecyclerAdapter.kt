package com.example.testingapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_gravitation.view.*
import kotlinx.android.synthetic.main.item_list.view.*

class RecyclerAdapter(val listaDatos:ArrayList<GravityBody>)
    : RecyclerView.Adapter<RecyclerAdapter.ViewHolderDatos>() {

    class ViewHolderDatos(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dato:TextView = itemView.cuerpo
        fun asignarDatos(s:GravityBody) {
            this.itemView.cuerpo.text = "Cuerpo " + s.id
            this.itemView.masa.text = "Masa: " + s.mass + " kg"
            this.itemView.posicion.text = "Posición ( "+ s.position.x + ", " + s.position.y + ", " + s.position.z + " )"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDatos {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolderDatos(view)
    }

    override fun getItemCount(): Int {
        return listaDatos.size
    }
    override fun onBindViewHolder(holder: ViewHolderDatos, position: Int) {
        holder.asignarDatos(listaDatos[position])
    }
}