package com.example.financasapp.Adapter.Principal

import android.content.Context
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.API.Chamada
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.R
import kotlinx.android.synthetic.main.principal_adapter_item.view.*

class PrincipalHolder(view: View, val activity: PrincipalActivity, val valores: MutableList<Valores>) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
    init {
        view.setOnCreateContextMenuListener(this)
    }

    val tipo = view.paTipo
    val nome = view.paNome
    val valor = view.paValor
    val data = view.paData
    val txtData = view.paTxtData
    val note = view.paNote
    val id = view.myID

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        valores.get(adapterPosition).tipo?.let {
            if (it == "Dívida" || it == "Dívida Paga") {
                var myActionItem = menu!!.add("Marcar como Paga")
                myActionItem?.let { it.setOnMenuItemClickListener(this) }
                valores.get(adapterPosition).tipo?.let { if (it == "Dívida Paga") myActionItem?.title = "Marcar como Não Paga" }
            } else {
                var myActionItem = menu!!.add("Teste Receita")
            }
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        valores.get(adapterPosition).id?.let { Chamada.chamarMarcarPago(it.toString(), activity.applicationContext) }
        activity.refresh()
        return true
    }


}