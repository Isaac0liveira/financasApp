package com.example.financasapp.Adapter.Principal

import android.content.Intent
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.API.Chamada
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.Recursos.OnSwipeTouchListener
import com.example.financasapp.Recursos.Swiper
import com.example.financasapp.UI.AdicionarActivity
import kotlinx.android.synthetic.main.principal_adapter_item.view.*

class PrincipalHolder(view: View, val activity: PrincipalActivity, val valores: MutableList<Valores>) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
    init {
        view.setOnCreateContextMenuListener(this)
        view.setOnTouchListener(object: OnSwipeTouchListener(){
            override fun onSwipeLeft() {
                Swiper(activity).onSwipeLeft()
            }

            override fun onSwipeRight() {
                Swiper(activity).onSwipeRight()
            }
        })
    }

    val tipo = view.paTipo
    val nome = view.orNome
    val valor = view.orValor
    val data = view.orData
    val txtData = view.orTxtData
    val note = view.paNote
    val id = view.myID
    val categoria = view.paCategoria

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        valores.get(adapterPosition).tipo?.let {
            if (it == "Dívida" || it == "Dívida Paga") {
                var item1: MenuItem? = null;
                if (it == "Dívida") {
                    item1 = menu!!.add("Marcar como Paga")
                } else {
                    item1 = menu!!.add("Marcar como NÃO Paga")
                }
                item1?.let {
                    it.setOnMenuItemClickListener {
                        valores.get(adapterPosition).id?.let {
                            Chamada.chamarMarcarPago(
                                it.toString(),
                                activity
                            )
                        }
                        true
                    }
                }

                var item2 = menu!!.add("Excluir")
                item2?.let {
                    it.setOnMenuItemClickListener {
                        valores.get(adapterPosition).id?.let {
                            Chamada.chamarExcluir(
                                it.toString(),
                                activity
                            )
                        }
                        true
                    }
                }


                var item3 = menu!!.add("Atualizar")
                item3?.let {
                    it.setOnMenuItemClickListener {
                        val valores: Valores = valores.get(adapterPosition)
                        activity.startActivity(
                            Intent(
                                activity.applicationContext,
                                AdicionarActivity().javaClass
                            ).putExtra("valores", valores)
                        )
                        true
                    }
                }

            } else {
                var item2 = menu!!.add("Excluir")
                item2?.let {
                    it.setOnMenuItemClickListener {
                        valores.get(adapterPosition).id?.let {
                            Chamada.chamarExcluir(
                                it.toString(),
                                activity
                            )
                        }
                        true
                    }
                }


                var item3 = menu!!.add("Atualizar")
                item3?.let {
                    it.setOnMenuItemClickListener {
                        val valores: Valores = valores.get(adapterPosition)
                        activity.startActivity(
                            Intent(
                                activity.applicationContext,
                                AdicionarActivity().javaClass
                            ).putExtra("valores", valores)
                        )
                        true
                    }
                }
            }
        }
    }

}