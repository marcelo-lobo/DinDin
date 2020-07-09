package com.example.dindin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView
import com.example.dindin.model.Categoria

class CategoriaDespesa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_despesa)


        listar_categoria_despesa ()
    }


    fun listar_categoria_despesa () {

        var listview = findViewById<ListView>(R.id.listView_categoria_despesa)


        //carregar da API as categorias de despesas - jogar os valores em um Array para depois adaptar em uma listview
        var lista = ArrayList<Categoria>()
        //val url  =" http://aplicativodindin.000webhostapp.com/webservice/getAllCategoriaDespesa.php"
        lista.add(Categoria("Lanches"))
        lista.add(Categoria("Transporte"))
        lista.add(Categoria("Academia"))



       val lista_adaptada = ArrayAdapter(this, R.layout.modelo_listview_categoria_receita_despesa, lista)
        listview.adapter = lista_adaptada


    }








}
