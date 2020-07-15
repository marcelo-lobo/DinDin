package com.example.dindin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.dindin.model.Categoria
import org.json.JSONException
import org.json.JSONObject

class CategoriaDespesa : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoria_despesa)


        listar_categoria_despesa ()
    }


    fun listar_categoria_despesa () {

        var listview = findViewById<ListView>(R.id.listView_categoria_despesa)


        var arraylista = ArrayList<Categoria>()

            val stringRequest = StringRequest(
                Request.Method.GET,
                EndPoints.URL_GET_LISTAR_CATEGORIAS_DESPESAS,
                Response.Listener<String> { s ->
                    try {
                        val obj = JSONObject(s)
                        if (!obj.getBoolean("error")) {
                            val array = obj.getJSONArray("categorias")

                            for (i in 0..array.length() - 1) {
                                val objectCategoria = array.getJSONObject(i)
                                val despesa = Categoria(
                                    objectCategoria.getInt("pk_tipo_receita_despesa"),
                                    objectCategoria.getString("tipo_receita_despesa")
                                )
                                arraylista!!.add(despesa)


                                val lista_adaptada = AdaptarCategoria(this, R.layout.modelo_listview_categoria_receita_despesa, arraylista)
                                listview.adapter = lista_adaptada


                            }
                        } else {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add<String>(stringRequest)




        listview.setOnItemClickListener { parent, view, position, id ->
            val element = listview.getItemAtPosition(position)

            val cadastrar_despesa_intent = Intent(this, CadastrarDespesa::class.java);

            cadastrar_despesa_intent .putExtra("pk_categoria",arraylista.get(position).pk_categoria)
            cadastrar_despesa_intent .putExtra("categoria",arraylista.get(position).categoria)

            startActivity(cadastrar_despesa_intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        }



    }




    fun voltar(view: View) {
        val intent = Intent(this, Totalizador::class.java)
        startActivity(intent);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }



}
