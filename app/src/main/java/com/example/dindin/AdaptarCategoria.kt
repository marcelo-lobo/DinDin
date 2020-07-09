package com.example.dindin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.dindin.model.Categoria

class AdaptarCategoria  (val ctx: Context, var resouces:Int, var colunas:List<Categoria>):
    ArrayAdapter<Categoria>(ctx, resouces, colunas) {
    override fun getView(ordem: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resouces, null)

        val categoriaTextView: TextView = view.findViewById(R.id.id_nome_categoria)

        val campo:Categoria = colunas[ordem]

        categoriaTextView.text = campo.categoria

        return view
    }
}

