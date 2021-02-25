package com.example.financasapp.Contrato

import android.app.Activity
import android.view.MenuItem
import android.view.View
import android.widget.RadioGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.financasapp.Mapper.Valores

interface Contrato {

    interface PrincipalPresenter {

        fun changeActivity(startActivity: Activity, changeActivity: Activity)


        fun setAdapter(list: RecyclerView, valores: MutableList<Valores>)

        fun setDate(view: TextView): String

        fun setButtons()

        fun getLista(dataSet: String)

        fun getDespesas(valores: MutableList<Valores>)

    }

    interface AdicionarPresenter{

        fun setRadioGroup(rd: RadioGroup)

        fun setDate(date: TextView)

    }

}