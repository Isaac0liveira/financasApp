package com.example.financasapp.UI

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.RadioButton
import com.example.financasapp.Presenter.AdicionarPresenter
import com.example.financasapp.R
import kotlinx.android.synthetic.main.activity_adicionar.*

class AdicionarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adicionar)
        val presenter = AdicionarPresenter(this)
        presenter.setRadioGroup(rdTipo)
        presenter.setDate(txtData)
    }
}