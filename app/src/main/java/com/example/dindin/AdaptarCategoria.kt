package com.example.dindin
/*
class AdaptarCategoria {
}



import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class Adapter (val ctx: Context, var resouces:Int, var colunas:List<Model_alunos>):
    ArrayAdapter<Model_alunos>(ctx, resouces, colunas) {
    override fun getView(ordem: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater: LayoutInflater = LayoutInflater.from(ctx)
        val view: View = layoutInflater.inflate(resouces, null)

        val codigoTextView: TextView = view.findViewById(R.id.id_cod_aluno)
        val nomeTextView: TextView = view.findViewById(R.id.id_nome)
        val cpfTextView: TextView = view.findViewById(R.id.id_cpf)
        val emailTextView: TextView = view.findViewById(R.id.id_e_mail)

        val campo:Model_alunos = colunas[ordem]

        codigoTextView.text = "Código: " + campo.cod.toString()
        nomeTextView.text = campo.nome
        cpfTextView.text = campo.cpf
        emailTextView.text = campo.email

        return view
    }
}
*/
