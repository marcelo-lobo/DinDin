package com.example.dindin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Totalizador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_totalizador)
    }


    fun categoriaReceita(view: View) {
/*
        val receita = Intent(this, CategoriaReceita::class.java);
        startActivity(receita);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
*/

    }
    fun categoriaDespesa(view: View) {

        val despesa = Intent(this, CategoriaDespesa::class.java);
        startActivity(despesa);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }


    fun listarReceitas (view: View) {


    }


    fun listarDespesas (view: View) {


    }


    fun sair(view: View) {

        val sair = Intent(this, MainActivity::class.java);
        startActivity(sair);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }

}
