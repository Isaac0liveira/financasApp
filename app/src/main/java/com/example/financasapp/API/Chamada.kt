package com.example.financasapp.API

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.financasapp.Mapper.Teste
import com.example.financasapp.PrincipalActivity
import com.example.financasapp.UI.AdicionarActivity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Single
import org.json.JSONArray
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList


object Chamada {

    fun chamarAdicionar(nome: String, valor: Double, data: String, note: String, tipo: String, dataSet: String, categoria: String, activity: AdicionarActivity) {
        return Rx2AndroidNetworking.post(Path.API_ADICIONAR).addPathParameter("nome", nome)
            .addPathParameter("valor", valor.toString())
            .addPathParameter("data", data)
            .addPathParameter("note", note)
            .addPathParameter("tipo", tipo)
            .addPathParameter("dataSet", dataSet)
            .addPathParameter("categoria", categoria).setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Toast.makeText(
                        activity.applicationContext,
                        response?.get("message") as String,
                        Toast.LENGTH_SHORT
                    ).show()
                    activity.finish()
                }

                override fun onError(erro: ANError?) {
                    Toast.makeText(activity.applicationContext, erro?.message, Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

    fun chamarLista(dataSet: String): Single<Teste> {
        return Rx2AndroidNetworking.get(Path.API_LISTAR).addPathParameter("dataSet", dataSet)
            .setPriority(Priority.MEDIUM).build().getParseSingle(object : TypeToken<Teste>() {})
    }

    fun chamarMarcarPago(id: String, activity: PrincipalActivity) {
        return Rx2AndroidNetworking.post(Path.API_MARCAR_PAGO).addPathParameter("id", id)
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Toast.makeText(
                        activity.applicationContext,
                        response?.get("message") as String,
                        Toast.LENGTH_SHORT
                    ).show()
                    activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE)
                        .getString("dataGeral", "")?.let { activity.refresh(it) }
                }

                override fun onError(erro: ANError?) {
                    Toast.makeText(activity.applicationContext, erro?.message, Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

    fun chamarAtualizar(id: String, nome: String, valor: Double, data: String, note: String, tipo: String, dataSet: String, categoria: String, activity: AdicionarActivity) {
        return Rx2AndroidNetworking.put(Path.API_ATUALIZAR).addPathParameter("id", id)
            .addPathParameter("nome", nome)
            .addPathParameter("valor", valor.toString())
            .addPathParameter("data", data)
            .addPathParameter("note", note)
            .addPathParameter("tipo", tipo)
            .addPathParameter("dataSet", dataSet)
            .addPathParameter("categoria", categoria).setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Toast.makeText(
                        activity.applicationContext,
                        response?.get("message") as String,
                        Toast.LENGTH_SHORT
                    ).show()
                    activity.finish()
                }

                override fun onError(erro: ANError?) {
                    Toast.makeText(activity.applicationContext, erro?.message, Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

    fun chamarExcluir(id: String, activity: PrincipalActivity) {
        return Rx2AndroidNetworking.delete(Path.API_EXCLUIR).addPathParameter("id", id)
            .setPriority(Priority.MEDIUM).build()
            .getAsJSONObject(object : JSONObjectRequestListener {
                override fun onResponse(response: JSONObject?) {
                    Toast.makeText(
                        activity.applicationContext,
                        response?.get("message") as String,
                        Toast.LENGTH_SHORT
                    ).show()
                    activity.getSharedPreferences("dataGeral", Context.MODE_PRIVATE)
                        .getString("dataGeral", "")?.let { activity.refresh(it) }
                }

                override fun onError(erro: ANError?) {
                    Toast.makeText(activity.applicationContext, erro?.message, Toast.LENGTH_SHORT)
                        .show()
                }

            })
    }

}