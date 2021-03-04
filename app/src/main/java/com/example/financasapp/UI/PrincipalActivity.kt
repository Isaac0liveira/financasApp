package com.example.financasapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
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
        presenter = PrincipalPresenter(this)
        presenter.setAdapter(valores)
        presenter.setButtons()
        refresh(presenter.setDate(principalData))
    }

    fun refresh(dataSet: String) {
        principalData.text = dataSet
        presenter.getLista(dataSet)
    }

    fun carregarLista(response: List<Valores>?) {
        valores.clear()
        if (!response.isNullOrEmpty()) valores.addAll(response.toMutableList())
        presenter.setAdapter(valores)
        presenter.getDespesas(valores)
        list.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
        Handler().postDelayed({list.visibility = View.VISIBLE}, 500)
    }

    override fun onResume() {
        super.onResume()
        getSharedPreferences("dataGeral", Context.MODE_PRIVATE).getString("dataGeral", "")
            ?.let { refresh(it) }
    }

}