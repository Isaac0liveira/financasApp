package com.example.financasapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.Presenter.PrincipalPresenter
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity : AppCompatActivity() {

    var valores = mutableListOf<Valores>()
    lateinit var presenter: PrincipalPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        presenter = PrincipalPresenter(this.applicationContext, this)
        presenter.setAdapter(list, valores)
        presenter.setButtons()
        refresh()
    }

    fun refresh(){presenter.getLista(presenter.setDate(principalData))}

    fun carregarLista(response: List<Valores>?) {
        valores.clear()
        if (!response.isNullOrEmpty()) valores.addAll(response.toMutableList())
        presenter.getDespesas(valores)
        runOnUiThread { list.adapter?.notifyDataSetChanged() }
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

}