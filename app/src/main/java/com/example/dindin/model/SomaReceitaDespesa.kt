package com.example.dindin.model

class SomaReceitaDespesa {

    var fk_tipo_registro = 0;
    var soma ="";

    constructor(fk_tipo_registro : Int, soma : String) {
        this.fk_tipo_registro = fk_tipo_registro
        this.soma = soma

    }
    constructor()

    override fun toString(): String {
        return "${soma}" + "--" + "\"${fk_tipo_registro}\""
    }
}