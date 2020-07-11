package com.example.dindin.model

class ReceitaDespesa {
    var cod_usuario = 0
    var cod_receita_despesa = 0
    var data =""
    var valor= ""
    var descricao = ""



    constructor(cod_usuario: Int, cod_receita_despesa: Int, data : String, valor : String, descricao : String) {
        this.cod_usuario = cod_usuario
        this.cod_receita_despesa = cod_receita_despesa
        this.data = data
        this.valor = valor
        this.descricao = descricao
    }

    override fun toString(): String {
        return "Receita/Despesa(Cod. Usuário='${cod_usuario}', Cod. Rec/Des=${cod_receita_despesa}, Data='$data', Valor='$valor', Descricao='$descricao')"
    }

}