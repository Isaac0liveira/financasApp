package com.example.financasapp.UI

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.Presenter.OrcamentoPresenter
import com.example.financasapp.Presenter.PrincipalPresenter
import com.example.financasapp.R
import kotlinx.android.synthetic.main.activity_orcamento.*
import kotlinx.android.synthetic.main.activity_principal.*


class OrcamentoActivity : AppCompatActivity() {

    var valores = mutableListOf<Valores>()
    lateinit var presenter: OrcamentoPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orcamento)
        presenter = OrcamentoPresenter(this)
        Handler().postDelayed({
            presenter.setAdapter(valores)
            presenter.setButtons()
            presenter.setDate(orcamentoData)
            super.onResume()
            getSharedPreferences("dataGeral", Context.MODE_PRIVATE).getString("dataGeral", "")?.let { refresh(it) }
        }, 1000)
    }

    fun refresh(dataSet: String) {
        orcamentoData.text = dataSet
        presenter.getLista(dataSet)
    }

    fun carregarLista(response: List<Valores>?) {
        valores.clear()
        if (!response.isNullOrEmpty()) valores.addAll(response.toMutableList())
        presenter.setAdapter(valores)
        presenter.getDespesas(valores)
        list2.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in))
        Handler().postDelayed({list2.visibility = View.VISIBLE}, 500)
    }

    override fun onResume() {
        super.onResume()
        getSharedPreferences("dataGeral", Context.MODE_PRIVATE).getString("dataGeral", "")
            ?.let { refresh(it) }
    }
}