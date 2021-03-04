package com.example.financasapp.Recursos

import android.content.Context
import android.util.Log
import com.example.financasapp.Mapper.Calendario
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.UI.OrcamentoActivity

class Swiper(val activity: PrincipalActivity) {
    val time =
        activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE).getString("dataGeral", "")
            .toString()

    fun onSwipeRight() {
        var calendario = Calendario()
        var mes = calendario.getMes(time.substringBefore("/"))
        var ano = time.substringAfter("/").toInt()
        if (mes > 1) {
            mes -= 1
        } else {
            ano -= 1
            mes = 12
        }
        calendario.setMes(mes)
        calendario.ano = ano
        Log.d("teste", "${calendario.mes}/${calendario.ano}")
        activity.refresh("${calendario.mes}/${calendario.ano}")
        activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE).edit().putString("dataGeral", "${calendario.mes}/${calendario.ano}").apply()
    }

    fun onSwipeLeft() {
        var calendario = Calendario()
        var mes = calendario.getMes(time.substringBefore("/"))
        var ano = time.substringAfter("/").toInt()
        if (mes < 12) {
            mes += 1
        } else {
            ano += 1
            mes = 1
        }
        calendario.setMes(mes)
        calendario.ano = ano
        Log.d("teste", "${calendario.mes}/${calendario.ano}")
        activity.refresh("${calendario.mes}/${calendario.ano}")
        activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE).edit().putString("dataGeral", "${calendario.mes}/${calendario.ano}").apply()
    }
}

class OrcamentoSwiper(val activity: OrcamentoActivity) {
    val time =
        activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE).getString("dataGeral", "")
            .toString()

    fun onSwipeRight() {
        var calendario = Calendario()
        var mes = calendario.getMes(time.substringBefore("/"))
        var ano = time.substringAfter("/").toInt()
        if (mes > 1) {
            mes -= 1
        } else {
            ano -= 1
            mes = 12
        }
        calendario.setMes(mes)
        calendario.ano = ano
        Log.d("teste", "${calendario.mes}/${calendario.ano}")
        activity.refresh("${calendario.mes}/${calendario.ano}")
        activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE).edit().putString("dataGeral", "${calendario.mes}/${calendario.ano}").apply()
    }

    fun onSwipeLeft() {
        var calendario = Calendario()
        var mes = calendario.getMes(time.substringBefore("/"))
        var ano = time.substringAfter("/").toInt()
        if (mes < 12) {
            mes += 1
        } else {
            ano += 1
            mes = 1
        }
        calendario.setMes(mes)
        calendario.ano = ano
        Log.d("teste", "${calendario.mes}/${calendario.ano}")
        activity.refresh("${calendario.mes}/${calendario.ano}")
        activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE).edit().putString("dataGeral", "${calendario.mes}/${calendario.ano}").apply()
    }
}