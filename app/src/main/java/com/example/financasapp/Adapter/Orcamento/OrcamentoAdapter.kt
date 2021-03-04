package com.example.financasapp.Adapter.Orcamento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.Adapter.Principal.PrincipalHolder
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.R
import com.example.financasapp.Recursos.OnSwipeTouchListener
import com.example.financasapp.Recursos.OrcamentoSwiper
import com.example.financasapp.Recursos.Swiper
import com.example.financasapp.UI.OrcamentoActivity

class OrcamentoAdapter(var valores: MutableList<Valores>, val activity: OrcamentoActivity): RecyclerView.Adapter<OrcamentoHolder>() {

    var copyValores = mutableListOf<Valores>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrcamentoHolder {
        val view = LayoutInflater.from(activity.applicationContext).inflate(R.layout.orcamento_adapter_item, parent, false)
        copyValores.clear()
        valores.map { i -> if(i.tipo == "Orçamento") copyValores.add(i)}
        return OrcamentoHolder(view, activity, copyValores)
    }
    override fun onBindViewHolder(holder: OrcamentoHolder, position: Int) {
            val map = copyValores.get(position)
            holder.tipo.text = map.tipo
            holder.txtData
            holder.nome.text = map.nome
            holder.valor.text = map.valor.toString()
            holder.data.text = map.data
            holder.note.text = map.note
            holder.id.text = map.id.toString()
            holder.categoria.text = map.categoria
            holder.progresso.setProgress(getProgress(map))
    }

    fun getProgress(valor: Valores): Int{
        var percent = 0.0
        for (i in valores) if(i.tipo == "Dívida Paga" && i.categoria == valor.categoria) percent += i.valor!!
        return (percent * 100 / valor.valor!!).toInt()
    }

    override fun getItemCount(): Int {
        var count = 0
        valores.map { i -> if(i.tipo == "Orçamento") count++}
        return count
    }

}