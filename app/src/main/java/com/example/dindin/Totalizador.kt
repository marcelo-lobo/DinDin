package com.example.dindin

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.substring
import android.view.View
import android.widget.*
import com.android.volley.AuthFailureError

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.dindin.model.Categoria
import com.example.dindin.model.Referencia
import com.example.dindin.model.SomaReceitaDespesa
import kotlinx.android.synthetic.main.activity_totalizador.*
import org.json.JSONException
import org.json.JSONObject

class Totalizador : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_totalizador)


        popularSpinner()

    }

    //Populando o spinner de referências - mes/ano
    fun popularSpinner()  {
/*
        var arraylista = ArrayList<String>()

            val stringRequest = StringRequest(
                Request.Method.GET,
                EndPoints.URL_GET_REFERENCIAS,
                Response.Listener<String> { s ->
                    try {
                        val obj = JSONObject(s)
                        if (!obj.getBoolean("error")) {
                            val array = obj.getJSONArray("ano_mes")

                            for (i in 0..array.length() - 1) {
                                val objectReferencia = array.getJSONObject(i)

                                val valor = objectReferencia.getString("ano_mes")

                                arraylista!!.add(valor)

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

        //return arraylista


        val referencias = findViewById<Spinner>(R.id.mes_ano)
        val adapter = ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, arraylista)
        referencias.adapter = adapter

*/

        val array_referencias = arrayOf("02-2020","05-2015")


        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,array_referencias)
        val referencias = findViewById<Spinner>(R.id.mes_ano)

        referencias.adapter = adapter

        referencias.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                //val mes_ano = mes_ano_selecionado.selectedItem.toString()

                val ano_mes = array_referencias[position]
                val ano = substring(ano_mes,3,7)
                val mes = substring(ano_mes,0,2)

                var arraysomas = ArrayList<SomaReceitaDespesa>()

                val stringRequest = object : StringRequest(
                    Request.Method.POST, EndPoints.URL_ADD_RECEITA_DESPESA,
                    Response.Listener<String> { response ->
                        try {
                            val obj = JSONObject(response)

                            if (!obj.getBoolean("error")) {
                                val array = obj.getJSONArray("categorias")

                                for (i in 0..array.length() - 1) {
                                    val objectSoma = array.getJSONObject(i)
                                    val cod_soma = SomaReceitaDespesa(
                                        objectSoma.getInt("fk_tipo_registro"),
                                        objectSoma.getString("soma_mes_ano")
                                    )
                                    arraysomas!!.add(cod_soma)

                                }
                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show()
                            }


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


                        params.put("ano", ano)
                        params.put("mes", mes)

                        return params
                    }
                }

                //adding request to queue
                //VolleySingleton.instance?.addToRequestQueue(stringRequest)

                val requestQueue = Volley.newRequestQueue(this)
                requestQueue.add<String>(stringRequest)


                //fim Volley

            }

        }




    }


    fun mostrarSomas() {

        val mes_ano_selecionado = findViewById<Spinner>(R.id.mes_ano)

        mes_ano_selecionado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                //val mes_ano = mes_ano_selecionado.selectedItem.toString()
                val texto_mes_ano = findViewById<TextView>(R.id.id_texto_mes_ano)
                    texto_mes_ano.text = mes_ano_selecionado.selectedItem.toString()
            }

        }



      //  Toast.makeText(this, "campo: " + mes_ano, Toast.LENGTH_LONG).show();




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

    fun showDialog(title : String,Message : String){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

}
