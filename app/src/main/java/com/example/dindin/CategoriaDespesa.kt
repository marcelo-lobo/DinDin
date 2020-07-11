package com.example.dindin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        var arraylista = ArrayList<Categoria>()
        //val url  =" http://aplicativodindin.000webhostapp.com/webservice/getAllCategoriaDespesa.php"



        arraylista.add(Categoria(1,"Lanches"))
        arraylista.add(Categoria(5,"Educação"))
        arraylista.add(Categoria(4,"Transporte"))



        val lista_adaptada = AdaptarCategoria(this, R.layout.modelo_listview_categoria_receita_despesa, arraylista)
        listview.adapter = lista_adaptada


        listview.setOnItemClickListener { parent, view, position, id ->
            val element = listview.getItemAtPosition(position)

            val cadastrar_despesa_intent = Intent(this, CadastrarDespesa::class.java);

            cadastrar_despesa_intent .putExtra("pk_categoria",arraylista.get(position).pk_categoria)
            cadastrar_despesa_intent .putExtra("categoria",arraylista.get(position).categoria)

            startActivity(cadastrar_despesa_intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        }



    }








}
