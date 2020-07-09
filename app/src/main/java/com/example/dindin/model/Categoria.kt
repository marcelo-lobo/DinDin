package com.example.dindin.model

class Categoria {

    var categoria ="";

    constructor(categoria : String) {
        this.categoria = categoria

    }

    constructor()

    override fun toString(): String {
        return this.categoria
    }


}