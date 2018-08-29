package br.com.livrokotlin.listadecompras

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

// armazena a lista de produtos em uma variável global
val produtosGlobal = mutableListOf<Produto>()

// transformar um bitmap em byteArray
fun Bitmap.toByteArray(): ByteArray{
    val stream = ByteArrayOutputStream()

    // comprimindo a imagem
    // o this neste contexto é o próprio objeto bitmap
    this.compress(Bitmap.CompressFormat.PNG, 0, stream)

    return stream.toByteArray()
}

// convertendo um byteArray para um bitmap
fun ByteArray.toBitmap() : Bitmap{
    // o this neste caso é o byteArray
    return BitmapFactory.decodeByteArray(this, 0, this.size)
}