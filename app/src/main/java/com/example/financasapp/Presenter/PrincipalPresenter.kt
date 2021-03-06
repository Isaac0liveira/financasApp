package com.example.financasapp.Presenter

import OnSwipeTouchListener
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.example.financasapp.API.Chamada
import com.example.financasapp.Adapter.Principal.PrincipalAdapter
import com.example.financasapp.Contrato.Contrato
import com.example.financasapp.Mapper.Calendario
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.UI.AdicionarActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_principal.*
import java.util.*


class PrincipalPresenter(val context: Context, val activity: PrincipalActivity) : Contrato.PrincipalPresenter {


    init {
        activity.supportActionBar?.hide()
    }

    override fun changeActivity(startActivity: Activity, changeActivity: Activity) {
        startActivity.applicationContext.startActivity(Intent(startActivity.applicationContext, changeActivity.javaClass).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }


    override fun setDate(view: TextView): String {
        val calendar = Calendar.getInstance()
        var ano = calendar.get(Calendar.YEAR)
        var mes = calendar.get(Calendar.MONTH)
        val calendario = Calendario()

        val setListener = DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val calendario = Calendario()
            calendario.setMes(month + 1)
            calendario.ano = year
            view.text = "${calendario.mes}/${calendario.ano}"
            getLista(view.text.toString())
        }

        val datePickerDialog = DatePickerDialog(activity, AlertDialog.THEME_HOLO_LIGHT, setListener, ano, mes, 0)
        datePickerDialog.datePicker.findViewById<NumberPicker>(activity.resources.getIdentifier("day", "id", "android")).visibility = View.GONE
        view.setOnClickListener {
            datePickerDialog.show()
        }

        calendario.setMes(mes + 1)
        calendario.ano = ano
        view.text = "${calendario.mes}/${calendario.ano}"
        return view.text.toString()
    }

    override fun setButtons() {
        activity.btAdd.setOnClickListener {
            changeActivity(activity, AdicionarActivity())
        }
    }


    override fun setAdapter(list: RecyclerView, valores: MutableList<Valores>) {
        activity.runOnUiThread {
            list.layoutManager = LinearLayoutManager(context)
            val adapter = PrincipalAdapter(valores, activity)
            list.adapter = adapter
        }
    }


    @SuppressLint("CheckResult")
    override fun getLista(dataSet: String) {
        Chamada.chamarLista(dataSet).subscribeOn(Schedulers.io()).subscribe { response, _ -> activity.carregarLista(response.valores) }
    }

    override fun getDespesas(valores: MutableList<Valores>) {
        var totalDespesa = 0.0
        var totalPagar = 0.0
        valores.map { if (it.tipo == "Dívida") totalDespesa += it.valor!! else if(it.tipo == "Dívida Paga") totalPagar += it.valor!! }
        totalDespesa = totalDespesa + totalPagar
        totalPagar = totalDespesa - totalPagar
        activity.runOnUiThread {
            activity.principalDespesa.text = totalDespesa.toString()
            activity.principalPagar.text = totalPagar.toString()
        }
    }


}