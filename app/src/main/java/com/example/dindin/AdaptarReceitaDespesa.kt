

package com.example.dindin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.dindin.model.Categoria
import com.example.dindin.model.ReceitaDespesa

class AdaptarReceitaDespesa  (val ctx: Context, var resouces:Int, var colunas:List<ReceitaDespesa>):
    ArrayAdapter<ReceitaDespesa>(ctx, resouces, colunas) {
    override fun getView(ordem: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resouces, null)

        val categoria = view.findViewById<TextView>(R.id.id_card_nome_categoria)
        val data: TextView = view.findViewById(R.id.id_card_data)
        val valor = view.findViewById<TextView>(R.id.id_card_valor)
        val descricao: TextView = view.findViewById(R.id.id_card_descricao)

        val campo:ReceitaDespesa = colunas[ordem]

        categoria.text = campo.categoria.toString()
        data.text = campo.data
        valor.text = campo.valor
        descricao.text = campo.descricao

        return view
    }
}