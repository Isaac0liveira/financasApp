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
import androidx.core.widget.doOnTextChanged
import com.example.financasapp.API.Chamada
import com.example.financasapp.Contrato.Contrato
import com.example.financasapp.Mapper.Calendario
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.UI.AdicionarActivity
import kotlinx.android.synthetic.main.activity_adicionar.*
import java.util.*

class AdicionarPresenter(val activity: AdicionarActivity): Contrato.AdicionarPresenter {

    init {
        activity.title = "Adicionar"
        activity.btAdicionar.setOnClickListener{
            var tipo = ""
            var dataSet = ""
            activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE).getString("dataSet", "")?.let{dataSet = it}
            activity.getSharedPreferences("tipo", Context.MODE_PRIVATE).getString("tipo", "")?.let{tipo= it}
            Chamada.chamarAdicionar(activity.txtNome.text.toString(), activity.txtValor.text.toString().toDouble(), activity.txtData.text.toString(), activity.txtNote.text.toString(), tipo, dataSet, activity.applicationContext)
            activity.finish()
        }
    }

    override fun setRadioGroup(rd: RadioGroup) {
        rd.setOnCheckedChangeListener{ group, checkedId ->
            val shared = activity.getSharedPreferences("tipo", Context.MODE_PRIVATE)
            shared.edit().putString("tipo", activity.findViewById<RadioButton>(checkedId).text.toString()).apply()
        }
    }

    override fun setDate(date: TextView) {
        val calendar = Calendar.getInstance()
        val ano = calendar.get(Calendar.YEAR)
        val mes = calendar.get(Calendar.MONTH)

        val setListener = DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val calendario = Calendario()
            calendario.setMes(month + 1)
            val dia = if(dayOfMonth < 10) "0$dayOfMonth" else "$dayOfMonth"
            val mes = if((month+1) < 10) "0${month+1}" else "${month+1}"
            date.text = "$dia/$mes/$year"
            val shared = activity.getSharedPreferences("dataSet", Context.MODE_PRIVATE)
            shared.edit().putString("dataSet", "${calendario.mes}/$year").apply()
        }

        val datePickerDialog = DatePickerDialog(activity, AlertDialog.THEME_HOLO_LIGHT, setListener, ano, mes, 0)
        date.setOnClickListener{
            datePickerDialog.show()
        }
    }
}