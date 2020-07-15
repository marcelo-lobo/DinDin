package com.example.dindin


object EndPoints {
   // private val URL_ROOT = "http://192.168.1.106/WebApi/v1/?op="

    private val URL_ROOT = "http://10.0.2.2:80/webservice/?op="
    //private val URL_ROOT = "http://aplicativodindin.000webhostapp.com/webservice/?op="
    // http://aplicativodindin.000webhostapp.com/webservice/arquivo.php

    val URL_ADD_RECEITA_DESPESA = URL_ROOT + "addreceitadespesa"
    val URL_GET_LISTAR_CATEGORIAS_DESPESAS = URL_ROOT + "listarcategoriasdespesas"
    val URL_GET_REFERENCIAS = URL_ROOT + "listarreferenciasanomes"
    val URL_GET_SOMA_RECEITAS_DESPESAS = URL_ROOT + "somareceitasdespesas"
    val URL_GET_DESPESAS = URL_ROOT + "despesas"
    val URL_GET_LISTAR_CATEGORIAS_RECEITAS =  URL_ROOT + "listarcategoriasreceitas"
    val URL_GET_RECEITAS = URL_ROOT + "receitas"


}