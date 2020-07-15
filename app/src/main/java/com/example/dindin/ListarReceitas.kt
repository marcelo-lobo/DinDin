package com.example.dindin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.example.dindin.model.ReceitaDespesa
import org.json.JSONException
import org.json.JSONObject

class ListarReceitas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_receitas)

        val intent = getIntent()
        val ano= intent.getSerializableExtra("ano").toString()
        val mes= intent.getSerializableExtra("mes").toString()

        listar_receitas(mes,ano)

    }

    fun listar_receitas(mes:String, ano:String) {

        var listview = findViewById<ListView>(R.id.listView_listar_receitas)


        var arraylista = ArrayList<ReceitaDespesa>()

        val stringRequest = object : StringRequest(
            Request.Method.POST, EndPoints.URL_GET_RECEITAS,
            Response.Listener<String> { response ->
                try {
                    val obj = JSONObject(response)

                    ///

                    if (!obj.getBoolean("error")) {
                        val array = obj.getJSONArray("receitas")

                        for (i in 0..array.length() - 1) {
                            val objectReceita = array.getJSONObject(i)
                            val receita = ReceitaDespesa(
                                objectReceita.getString("categoria"),
                                objectReceita.getString("data"),
                                objectReceita.getString("valor"),
                                objectReceita.getString("descricao")

                            )
                            arraylista!!.add(receita)
                            val lista_adaptada = AdaptarReceitaDespesa(this, R.layout.modelo_listview_listar_receitas_despesas, arraylista)
                            listview.adapter = lista_adaptada

                        }


                    } else {
                        Toast.makeText(
                            getApplicationContext(),
                            obj.getString("message"),
                            Toast.LENGTH_LONG
                        ).show()
                    }


                    ////

                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            },
            object : Response.ErrorListener {
                override fun onErrorResponse(volleyError: VolleyError) {
                    Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG)
                        .show()
                }
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()

                params.put("ano", ano)
                params.put("mes", mes)

                return params
            }
        }

        //adding request to queue
        VolleySingleton.instance?.addToRequestQueue(stringRequest)

    }



    fun voltar(view: View) {
        val intent = Intent(this, Totalizador::class.java)
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }



}
