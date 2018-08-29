package br.com.livrokotlin.listadecompras

import android.graphics.Bitmap

// criando uma classe de dados
data class Produto(
        val id:Int,
        val nome:String,
        val quantidade: Int,
        val valor: Double,
        val foto: Bitmap? = null // ? permite receber valor nulo, e torna o campo opcional
)