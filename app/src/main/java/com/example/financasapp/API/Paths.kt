package com.example.financasapp.API


object Path {

    const val API_LISTAR = "http://192.168.1.5:8080/teste/obterLista/?dataSet={dataSet}"

    const val API_ADICIONAR = "http://192.168.1.5:8080/teste/?nome={nome}&valor={valor}&data={data}&note={note}&tipo={tipo}&dataSet={dataSet}"

    const val API_MARCAR_PAGO = "http://192.168.1.5:8080/teste/marcarPago/?meuID={id}"

}
