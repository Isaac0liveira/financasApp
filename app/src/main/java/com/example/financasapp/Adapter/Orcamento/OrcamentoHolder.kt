package com.example.financasapp.Adapter.Orcamento

import android.content.Intent
import android.view.ContextMenu
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.API.Chamada
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.Recursos.OnSwipeTouchListener
import com.example.financasapp.Recursos.OrcamentoSwiper
import com.example.financasapp.UI.AdicionarActivity
import com.example.financasapp.UI.OrcamentoActivity
import kotlinx.android.synthetic.main.orcamento_adapter_item.view.*
import kotlinx.android.synthetic.main.principal_adapter_item.view.*
import kotlinx.android.synthetic.main.principal_adapter_item.view.orData
import kotlinx.android.synthetic.main.principal_adapter_item.view.orNome
import kotlinx.android.synthetic.main.principal_adapter_item.view.orTxtData
import kotlinx.android.synthetic.main.principal_adapter_item.view.orValor

class OrcamentoHolder(view: View, val activity: OrcamentoActivity, val valores: MutableList<Valores>) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
    init {
        view.setOnCreateContextMenuListener(this)
        view.setOnTouchListener(object: OnSwipeTouchListener(){
            override fun onSwipeLeft() {
                OrcamentoSwiper(activity).onSwipeLeft()
            }

            override fun onSwipeRight() {
                OrcamentoSwiper(activity).onSwipeRight()
            }
        })
    }

    val tipo = view.orTipo
    val nome = view.orNome
    val valor = view.orValor
    val data = view.orData
    val txtData = view.orTxtData
    val note = view.orNote
    val id = view.orMyID
    val categoria = view.orCategoria
    val progresso = view.progressBar3



    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        valores.get(adapterPosition).tipo?.let {
            if (it == "Orçamento") {
                var item2 = menu!!.add("Excluir")
                item2?.let {
                    it.setOnMenuItemClickListener {
                        true
                    }
                }

                var item3 = menu!!.add("Atualizar")
                item3?.let {
                    it.setOnMenuItemClickListener {
                        val valores: Valores = valores.get(adapterPosition)
                        activity.startActivity(Intent(activity.applicationContext, AdicionarActivity().javaClass).putExtra("orçamento", valores))
                        true
                    }
                }

            }
        }
    }
}