package com.example.financasapp.Adapter.Principal

import android.content.Context
import android.util.Log
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.R

class PrincipalAdapter(val valores: MutableList<Valores>, val activity: PrincipalActivity): RecyclerView.Adapter<PrincipalHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrincipalHolder {
        val view = LayoutInflater.from(activity.applicationContext).inflate(R.layout.principal_adapter_item, parent, false)
        return PrincipalHolder(view, activity, valores)
    }
    override fun onBindViewHolder(holder: PrincipalHolder, position: Int) {
        val map = valores.get(position)
        holder.tipo.text = map.tipo
        if(map.tipo == "Dívida" || map.tipo == "Dívida Paga"){
            holder.txtData.text = "Pagar até:"
        }else{
            holder.txtData.text = "Recebe em:"
        }
        holder.nome.text = map.nome
        holder.valor.text = map.valor.toString()
        holder.data.text = map.data
        holder.note.text = map.note
        holder.id.text = map.id.toString()
    }



    override fun getItemCount(): Int {
        return valores.size
    }

}