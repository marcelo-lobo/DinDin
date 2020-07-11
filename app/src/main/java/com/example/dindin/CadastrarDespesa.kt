package com.example.dindin

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
import com.example.dindin.model.ReceitaDespesa
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_cadastrar_despesa.*
import org.json.JSONException
import org.json.JSONObject

class CadastrarDespesa : AppCompatActivity() {

    //private lateinit var despesa: ReceitaDespesa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrar_despesa)


        val intent = getIntent()
        val pk= intent.getSerializableExtra("pk_categoria")
        val categoria= intent.getSerializableExtra("categoria")

      findViewById<TextView>(R.id.id_pk_categoria).text = pk.toString()
      findViewById<TextView>(R.id.id_campo_categoria).text = categoria.toString()

        val etDate =  findViewById<EditText>(R.id.data_despesa)
        etDate.addTextChangedListener(Mascara.Mask.insert("##/##/####", etDate));


    }


    fun cadastrarDespesa (view: View) {
        //tem uma Model de ReceitaDespesa (caso necessário)

        //tem q pegar o código do usuário também (vamos passar 5 valores para fazer o cadastro)
        val cod_categoria = findViewById<TextView>(R.id.id_pk_categoria).text.toString()
        val data_despesa = findViewById<EditText>(R.id.data_despesa).text.toString()
        val valor_despesa = findViewById<EditText>(R.id.valor_despesa).toString()
        val descricao_despesa = findViewById<EditText>(R.id.descricao).text.toString()




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

                params.put("valor_receita_despesa", valor_despesa)
                params.put("valor_receita_despesa", valor_despesa)
                params.put("valor_receita_despesa", valor_despesa)
                params.put("valor_receita_despesa", valor_despesa)
                params.put("descricao_receita_despesa", descricao_despesa)
                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)



    }






        //Toast.makeText(this, "campo: " + pk, Toast.LENGTH_LONG).show();




}
