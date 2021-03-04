package com.example.financasapp.Adapter.Principal

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.R
import com.example.financasapp.Recursos.OnSwipeTouchListener
import com.example.financasapp.Recursos.Swiper

class PrincipalAdapter(val valores: MutableList<Valores>, val activity: PrincipalActivity): RecyclerView.Adapter<PrincipalHolder>() {

    var copyValores = mutableListOf<Valores>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrincipalHolder {
        val view = LayoutInflater.from(activity.applicationContext).inflate(R.layout.principal_adapter_item, parent, false)
        copyValores.clear()
        valores.map { i -> if(i.tipo != "Orçamento") copyValores.add(i)}
        return PrincipalHolder(view, activity, copyValores)
    }
    override fun onBindViewHolder(holder: PrincipalHolder, position: Int) {
            val map = copyValores.get(position)
            holder.tipo.text = map.tipo
            if (map.tipo == "Dívida" || map.tipo == "Dívida Paga") {
                holder.txtData.text = "Pagar até:"
            } else {
                holder.txtData.text = "Recebe em:"
            }
            holder.nome.text = map.nome
            holder.valor.text = map.valor.toString()
            holder.data.text = map.data
            holder.note.text = map.note
            holder.id.text = map.id.toString()
            holder.categoria.text = map.categoria
    }



    override fun getItemCount(): Int {
        var count = 0
        valores.map { i -> if(i.tipo != "Orçamento") count++}
        return count
    }

}