package com.example.dindin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }



     fun logar(view: View) {

        val usuario = findViewById<EditText>(R.id.login).text.toString()
        val senha = findViewById<EditText>(R.id.senha).text.toString()
/*
        if  (usuario!="" && senha!="") {

                //Chamar a API para validar o login do usuário
            if (ChamarAPI.validarLogin(usuario,senha)) {
                intent = Intent(this, Totalizador::class.java)
                startActivity(intent)

            }else {
                val alerta = AlertDialog.Builder(this)
                alerta.setTitle("Usuário")
                alerta.setMessage("Usuário não cadastrado ou senha incorreta")
                alerta.show()
            }

        } else {
            val alerta2 = AlertDialog.Builder(this)
            alerta2.setTitle("Preencher")
            alerta2.setMessage("Preencha usuário e senha")
            alerta2.show()

        }
*/

         val intent = Intent(this, Totalizador::class.java)
         startActivity(intent);
         overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }



}
