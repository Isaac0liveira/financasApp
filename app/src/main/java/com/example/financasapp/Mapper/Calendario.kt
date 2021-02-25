package com.example.financasapp.Mapper

import android.util.Log

class Calendario {

    var mes: String? = null

    var ano: Int? = null

    fun setMes(mes: Int) {
        when (mes) {
            1 -> this.mes = "Janeiro"
            2 -> this.mes = "Fevereiro"
            3 -> this.mes = "Março"
            4 -> this.mes = "Abril"
            5 -> this.mes = "Maio"
            6 -> this.mes = "Junho"
            7 -> this.mes = "Julho"
            8 -> this.mes = "Agosto"
            9 -> this.mes = "Setembro"
            10 -> this.mes = "Outubro"
            11 -> this.mes = "Novembro"
            12 -> this.mes = "Dezembro"
            else -> Log.d("Erro", "Erro ao usar o setter")
        }
    }
}