package com.example.financasapp.Presenter

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.*
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.example.financasapp.API.Chamada
import com.example.financasapp.Contrato.Contrato
import com.example.financasapp.Mapper.Calendario
import com.example.financasapp.Mapper.Valores
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.R
import com.example.financasapp.UI.AdicionarActivity
import kotlinx.android.synthetic.main.activity_adicionar.*
import java.util.*

class AdicionarPresenter(val activity: AdicionarActivity) : Contrato.AdicionarPresenter {

    init {
        activity.title = "Adicionar"
        activity.btAdicionar.setOnClickListener {
            var tipo = ""
            var dataSet = ""
            var categoria = ""
            activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE).getString("dataSet", "")
                ?.let { dataSet = it }
            activity.getSharedPreferences("tipo", Context.MODE_PRIVATE).getString("tipo", "")
                ?.let { tipo = it }
            if (activity.spinner.isVisible && activity.spinner.selectedItem != null) categoria = activity.spinner.selectedItem.toString() else categoria = "Receita"
            if(!activity.txtNome.text.toString().isEmpty() && activity.txtValor.text.toString().isEmpty() && activity.txtData.text.toString().isEmpty() && activity.txtNote.text.toString().isEmpty() && tipo.isEmpty() && categoria.isEmpty()) {
                Chamada.chamarAdicionar(
                    activity.txtNome.text.toString(),
                    activity.txtValor.text.toString().toDouble(),
                    activity.txtData.text.toString(),
                    activity.txtNote.text.toString(),
                    tipo,
                    dataSet,
                    categoria,
                    activity
                )
            }else{
                Toast.makeText(activity.applicationContext, "Dados em falta! Preencha-os", Toast.LENGTH_SHORT).show()
            }
        }

        if (activity.intent.getSerializableExtra("valores") != null) {
            activity.title = "Atualizar"
            val valores = activity.intent.getSerializableExtra("valores") as Valores
            activity.txtNome.setText(valores.nome)
            activity.txtValor.setText(valores.valor.toString())
            activity.txtData.setText(valores.data)
            activity.txtNote.setText(valores.note)
            if (valores.categoria != "Receita" && valores.tipo != "Receita") {
                valores.tipo?.let { setSpinner(activity.spinner, it) }
            }
            activity.getSharedPreferences("tipo", Context.MODE_PRIVATE).edit()
                .putString("tipo", valores.tipo).apply()
            activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE).edit()
                .putString("dataSet", valores.dataSet).apply()
            activity.btAdicionar.text = "Atualizar"
            activity.btAdicionar.setOnClickListener {
                var tipo = ""
                var dataSet = ""
                var categoria = ""
                activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE)
                    .getString("dataSet", "")?.let { dataSet = it }
                activity.getSharedPreferences("tipo", Context.MODE_PRIVATE).getString("tipo", "")
                    ?.let { tipo = it }
                if (activity.spinner.isVisible) categoria = activity.spinner.selectedItem.toString() else categoria = "Receita"
                if(!(activity.txtNome.text.toString().isEmpty() && activity.txtValor.text.toString().isEmpty() && activity.txtData.text.toString().isEmpty() && activity.txtNote.text.toString().isEmpty() && tipo.isEmpty() && dataSet.isEmpty() && categoria.isEmpty())) {
                    Chamada.chamarAtualizar(
                        valores.id.toString(),
                        activity.txtNome.text.toString(),
                        activity.txtValor.text.toString().toDouble(),
                        activity.txtData.text.toString(),
                        activity.txtNote.text.toString(),
                        tipo,
                        dataSet,
                        categoria,
                        activity
                    )
                }else{
                    Toast.makeText(activity.applicationContext, "Dados em falta! Preencha-os", Toast.LENGTH_SHORT).show()
                }
            }
            if (valores.tipo == "Dívida" || valores.tipo == "Dívida Paga") {
                activity.rdTipo.check(R.id.btDivida)
            } else {
                activity.rdTipo.check(R.id.btReceita)
            }
        }

        if (activity.intent.getSerializableExtra("orçamento") != null) {
            activity.title = "Atualizar"
            val valores = activity.intent.getSerializableExtra("orçamento") as Valores
            activity.txtNome.setText(valores.nome)
            activity.txtValor.setText(valores.valor.toString())
            activity.txtData.setText(valores.data)
            activity.txtNote.setText(valores.note)
            if (valores.categoria != "Receita" && valores.tipo != "Receita") {
                valores.tipo?.let { setSpinner(activity.spinner, it) }
            }
            activity.getSharedPreferences("tipo", Context.MODE_PRIVATE).edit()
                .putString("tipo", valores.tipo).apply()
            activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE).edit()
                .putString("dataSet", valores.dataSet).apply()
            activity.btAdicionar.text = "Atualizar"
            activity.btAdicionar.setOnClickListener {
                var tipo = ""
                var dataSet = ""
                var categoria = ""
                activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE)
                    .getString("dataSet", "")?.let { dataSet = it }
                activity.getSharedPreferences("tipo", Context.MODE_PRIVATE).getString("tipo", "")
                    ?.let { tipo = it }
                if (activity.spinner.isVisible) categoria = activity.spinner.selectedItem.toString() else categoria = "Receita"
                if(!(activity.txtNome.text.toString().isEmpty() && activity.txtValor.text.toString().isEmpty() && activity.txtData.text.toString().isEmpty() && activity.txtNote.text.toString().isEmpty() && dataSet.isEmpty() && categoria.isEmpty())) {
                    Chamada.chamarAtualizar(
                        valores.id.toString(),
                        activity.txtNome.text.toString(),
                        activity.txtValor.text.toString().toDouble(),
                        activity.txtData.text.toString(),
                        activity.txtNote.text.toString(),
                        "Orçamento",
                        dataSet,
                        categoria,
                        activity
                    )
                }else{
                    Toast.makeText(activity.applicationContext, "Dados em falta! Preencha-os", Toast.LENGTH_SHORT).show()
                }
            }
            activity.rdTipo.visibility = View.GONE
        }

        if(activity.intent.getStringExtra("tipo") == "Orçamento"){
            activity.rdTipo.visibility = View.GONE
            setSpinner(activity.spinner, "Orçamento")
            activity.btAdicionar.setOnClickListener {
                var dataSet = ""
                var categoria = ""
                activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE).getString("dataSet", "")
                    ?.let { dataSet = it }
                categoria = activity.spinner.selectedItem.toString()
                Chamada.chamarAdicionar(activity.txtNome.text.toString(), activity.txtValor.text.toString().toDouble(), activity.txtData.text.toString(), activity.txtNote.text.toString(), "Orçamento", dataSet, categoria, activity)
            }
        }
    }

    override fun setSpinner(spinner: Spinner, tipo: String) {
        if (tipo == "Dívida" || tipo == "Dívida Paga" || tipo == "Orçamento") {
            spinner.visibility = View.VISIBLE
            activity.categoria.visibility = View.VISIBLE
            ArrayAdapter.createFromResource(
                activity.applicationContext,
                R.array.options_array,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinner.adapter = adapter
            }
        } else {
            spinner.visibility = View.GONE
            activity.categoria.visibility = View.GONE
        }
    }

    override fun setRadioGroup(rd: RadioGroup) {
        rd.setOnCheckedChangeListener { group, checkedId ->
            val shared = activity.getSharedPreferences("tipo", Context.MODE_PRIVATE)
            shared.edit()
                .putString("tipo", activity.findViewById<RadioButton>(checkedId).text.toString())
                .apply()
            activity.getSharedPreferences("tipo", Context.MODE_PRIVATE).getString("tipo", "")
                ?.let { setSpinner(activity.spinner, it) }

        }
    }

    override fun setDate(date: TextView) {
        val calendar = Calendar.getInstance()
        val ano = calendar.get(Calendar.YEAR)
        val mes = calendar.get(Calendar.MONTH)

        val setListener =
            DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val calendario = Calendario()
                calendario.setMes(month + 1)
                val dia = if (dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
                val mes = if ((month + 1) < 10) "0${month + 1}" else "${month + 1}"
                date.text = "$dia/$mes/$year"
                val shared = activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE)
                shared.edit().putString("dataSet", "${calendario.mes}/$year").apply()
            }

        val datePickerDialog =
            DatePickerDialog(activity, AlertDialog.THEME_HOLO_LIGHT, setListener, ano, mes, 0)
        date.setOnClickListener {
            datePickerDialog.show()
        }
    }
}