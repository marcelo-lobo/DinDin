package com.example.dindin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import org.json.JSONException
import org.json.JSONObject

class CadastrarReceita : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_receita)

        val intent = getIntent()
        val pk= intent.getSerializableExtra("pk_categoria")
        val categoria= intent.getSerializableExtra("categoria")

        findViewById<TextView>(R.id.id_pk_categoria).text = pk.toString()
        findViewById<TextView>(R.id.id_campo_categoria).text = categoria.toString()

        val etDate =  findViewById<EditText>(R.id.data_receita)
        etDate.addTextChangedListener(Mascara.Mask.insert("##/##/####", etDate));



    }


    fun cadastrarReceita (view: View) {


        //tem q pegar o código do usuário também (vamos passar 5 valores para fazer o cadastro)
        val cod_categoria = findViewById<TextView>(R.id.id_pk_categoria).text.toString()
        val data_receita = findViewById<EditText>(R.id.data_receita).text.toString()
        val valor_receita = findViewById<EditText>(R.id.valor_receita).text.toString()
        val descricao_receita = findViewById<EditText>(R.id.descricao).text.toString()




        val ano = data_receita.substring(6,10)
        val mes = data_receita.substring(3,5)
        val dia = data_receita.substring(0,2)
        val data_receita2 = ano+"-"+mes+"-"+dia


        //creating volley string request
        val stringRequest = object : StringRequest(
            Request.Method.POST, EndPoints.URL_ADD_RECEITA_DESPESA,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)
                    Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()

                params.put("fk_usuario", "1")
                params.put("fk_tipo_receita_despesa", cod_categoria)
                params.put("data_receita_despesa", data_receita2)
                params.put("valor_receita_despesa", valor_receita)
                params.put("descricao_receita_despesa", descricao_receita)
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)

        val intent = Intent(this, Totalizador::class.java)
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }





    fun voltar(view: View) {

        val intent = Intent(this, CategoriaReceita::class.java)
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }


}
