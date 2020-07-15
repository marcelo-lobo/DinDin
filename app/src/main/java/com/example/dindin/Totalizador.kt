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

    //Populando o spinner de referências - mes/ano / Recupera as somas de receitas e despesas do Mês/Ano selecionado no Spinner
    fun popularSpinner()  {

        var arraylista = ArrayList<String>()

            val stringRequest = StringRequest(
                Request.Method.GET,
                EndPoints.URL_GET_REFERENCIAS,
                Response.Listener<String> { s ->
                    try {
                        val obj = JSONObject(s)
                        if (!obj.getBoolean("error")) {
                            val array = obj.getJSONArray("referencias")

                            for (i in 0..array.length() - 1) {
                                val objectReferencia = array.getJSONObject(i)

                                val valor = objectReferencia.getString("ano_mes")

                                arraylista.add(valor)

                            }



                            val referencias = findViewById<Spinner>(R.id.mes_ano)
                            val adapter = ArrayAdapter<String>(this,android.R.layout.select_dialog_item, arraylista)
                            referencias.adapter = adapter




                        } else {
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }, Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })

            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add<String>(stringRequest)


        val referencias2 = findViewById<Spinner>(R.id.mes_ano)
        referencias2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                //val mes_ano = mes_ano_selecionado.selectedItem.toString()

                val ano_mes = arraylista[position]
                val ano = substring(ano_mes,3,7)
                val mes = substring(ano_mes,0,2)



                var arraysomas = ArrayList<SomaReceitaDespesa>()
                val stringRequest = object : StringRequest(
                    Request.Method.POST, EndPoints.URL_GET_SOMA_RECEITAS_DESPESAS,
                    Response.Listener<String> { response ->
                        try {
                            val obj = JSONObject(response)

                                ///

                            if (!obj.getBoolean("error")) {
                                val array = obj.getJSONArray("soma")

                                for (i in 0..array.length() - 1) {
                                    val objectSoma = array.getJSONObject(i)
                                    val cod_soma = SomaReceitaDespesa(
                                        objectSoma.getInt("fk_tipo_registro"),
                                        objectSoma.getString("soma_mes_ano")
                                    )
                                    arraysomas!!.add(cod_soma)

                                }

                                var soma_receita = ""
                                var soma_despesa = ""
                                if (arraysomas[0].fk_tipo_registro == 1){
                                    soma_receita = arraysomas[0].soma
                                    soma_despesa = arraysomas[1].soma
                                } else {
                                    soma_despesa = arraysomas[0].soma
                                    soma_receita = arraysomas[1].soma
                                }

                                id_texto_total_receitas.text = "R$ "+soma_receita
                                id_texto_total_despesas.text = "R$ "+soma_despesa
                                id_texto_saldo.text = "R$ "+(soma_receita.toDouble()-soma_despesa.toDouble()).toString()

                            } else {
                                Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show()
                            }


                            ////

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
                VolleySingleton.instance?.addToRequestQueue(stringRequest)


            }

        }

    }





    fun categoriaReceita(view: View) {

     val receita = Intent(this, CategoriaReceita::class.java);
        startActivity(receita);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }
    fun categoriaDespesa(view: View) {

        val despesa = Intent(this, CategoriaDespesa::class.java);
        startActivity(despesa);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);


    }


    fun listarReceitas (view: View) {
        val receita = Intent(this, ListarReceitas::class.java);

        val mes_ano = findViewById<Spinner>(R.id.mes_ano).selectedItem.toString()
        val ano = substring(mes_ano,3,7)
        val mes = substring(mes_ano,0,2)


        receita.putExtra("mes",mes)
        receita.putExtra("ano",ano)


        startActivity(receita);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

    }


    fun listarDespesas (view: View) {

        val despesa = Intent(this, ListarDespesas::class.java);

        val mes_ano = findViewById<Spinner>(R.id.mes_ano).selectedItem.toString()
        val ano = substring(mes_ano,3,7)
        val mes = substring(mes_ano,0,2)



        despesa.putExtra("mes",mes)
        despesa.putExtra("ano",ano)



        startActivity(despesa);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

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
