package com.example.financasapp.Presenter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Looper
import android.view.View
import android.widget.DatePicker
import android.widget.NumberPicker
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.API.Chamada
import com.example.financasapp.Adapter.Orcamento.OrcamentoAdapter
import com.example.financasapp.Adapter.Principal.PrincipalAdapter
import com.example.financasapp.Contrato.Contrato
import com.example.financasapp.Mapper.Calendario
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.UI.AdicionarActivity
import com.example.financasapp.UI.OrcamentoActivity
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_orcamento.*
import kotlinx.android.synthetic.main.activity_principal.*
import java.io.Serializable
import java.util.*

class OrcamentoPresenter(val activity: OrcamentoActivity) : Contrato.OrcamentoPresenter {

    init {
        Thread {
            activity.runOnUiThread {
                activity.window.setStatusBarColor(Color.parseColor("#00CCC5"))
                activity.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor("#005187")))
                activity.title = "Orçamentos"
            }
        }.start()
    }

    override fun changeActivity(startActivity: Activity, changeActivity: Activity, extra: Any) {
        val intent = Intent(
            startActivity.applicationContext,
            changeActivity.javaClass
        ).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        if (extra is String) intent.putExtra("tipo", extra)
        else if (extra is Serializable) intent.putExtra("orcamento", extra)
        startActivity.startActivity(intent)
    }

    override fun setAdapter(valores: MutableList<Valores>) {
        Thread {
            activity.runOnUiThread {
                activity.list2.layoutManager = LinearLayoutManager(activity.applicationContext)
                val adapter = OrcamentoAdapter(valores, activity)
                activity.list2.adapter = adapter
            }
        }.start()
    }

    override fun setDate(view: TextView): String {

        val calendar = Calendar.getInstance()
        var ano = calendar.get(Calendar.YEAR)
        var mes = calendar.get(Calendar.MONTH)
        val calendario = Calendario()

        Thread {
            activity.runOnUiThread {
                val setListener =
                    DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                        val calendario = Calendario()
                        calendario.setMes(month + 1)
                        calendario.ano = year
                        view.text = "${calendario.mes}/${calendario.ano}"
                        activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE).edit()
                            .putString("dataGeral", "${calendario.mes}/${calendario.ano}").apply()
                        getLista(view.text.toString())
                    }

                val datePickerDialog =
                    DatePickerDialog(
                        activity,
                        AlertDialog.THEME_HOLO_LIGHT,
                        setListener,
                        ano,
                        mes,
                        0
                    )
                datePickerDialog.datePicker.findViewById<NumberPicker>(
                    activity.resources.getIdentifier(
                        "day",
                        "id",
                        "android"
                    )
                ).visibility = View.GONE
                view.setOnClickListener {
                    Thread {
                        activity.runOnUiThread {
                            datePickerDialog.show()
                        }
                    }.start()
                }
            }
        }.start()

        return "${calendario.mes}/${calendario.ano}"
    }

    override fun setButtons() {
        Thread {
            activity.runOnUiThread {
                activity.orBtAdd.setOnClickListener {
                    changeActivity(activity, AdicionarActivity(), "Orçamento")
                }
            }
        }.start()
    }


    @SuppressLint("CheckResult")
    override fun getLista(dataSet: String) {
        Thread {
            activity.runOnUiThread {
                activity.progressBar4.visibility = View.VISIBLE
                activity.progressBar4.bringToFront()
                Chamada.chamarLista(dataSet).subscribeOn(Schedulers.io())
                    .subscribe { response, _ ->
                        activity.runOnUiThread {
                            activity.progressBar4.visibility = View.GONE;
                            activity.carregarLista(response.valores)
                        }
                    }
            }
        }.start()
    }

    override fun getDespesas(valores: MutableList<Valores>) {

        Thread {
            activity.runOnUiThread {
                var valoresCopia = valores.filter { i -> i.tipo == "Orçamento" }.toMutableList()
                var valoresCopia2 = valores.filter { i -> i.tipo == "Orçamento" }.toMutableList()
                var first = true
                var totalDespesa = 0.0
                var totalPagar = 0.0
                valoresCopia2.forEach { i ->
                    run {
                        valoresCopia.removeAll {
                            if (it.categoria == i.categoria) (!first).also {
                                first = false
                            } else false
                        }
                        first = true
                        totalDespesa += i.valor!!
                    }
                }
                valoresCopia.forEach { i ->
                    run {
                        valores.forEach { j ->
                            run {
                                if (j.tipo == "Dívida Paga" && j.categoria == i.categoria) totalPagar += j.valor!!
                            }
                        }
                    }
                }

                activity.orcamentoSoma.text = totalDespesa.toString()
                activity.orcamentoPrevisao.text = totalPagar.toString()
            }
        }.start()
    }

}