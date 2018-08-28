package br.com.livrokotlin.listadecompras

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

    // codigo da imagem
    // na verdade é um código qualquer, para identificar o retorno do intent
    val COD_IMAGE: Int = 101

    // variável que armazena o bitmap da imagem
    var imageBitmap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // ao clicar no botão de inserir
        btn_inserir.setOnClickListener {
            val produto = txt_produto.text.toString()
            val qtd = txt_qtd.text.toString()
            val valor = txt_valor.text.toString()

            // inserir apenas se não estiver vazia
            if( produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty() ){

                val prod = Produto(produto, qtd.toInt(), valor.toDouble(), imageBitmap)

                // adicionando na variável global
                produtosGlobal.add(prod)

                // limpar o campo ao inserir
                txt_produto.text.clear()
                txt_qtd.text.clear()
                txt_valor.text.clear()
            }
            // caso contrário, disparar um erro
            else {
                txt_produto.error = if(produto.isNotEmpty()) "Preencha o nome do produto" else null

                txt_qtd.error = if(qtd.isNotEmpty()) "Preencha a quantidade" else null

                txt_valor.error = if(valor.isNotEmpty()) "Preencha o valor" else null
            }
        }

        // ao clicar no icone da imagem
        img_foto_produto.setOnClickListener{
            abrirGaleria()
        }
    }

    // função para o usuário selecionar uma imagem na galeria
    private fun abrirGaleria(){
        // definindo a ação de conteúdo
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // definindo um filtro para imagens
        intent.type = "image/*" // qualquer tipo de imagem

        // inicializando a activity, mas esperando algum retorno
        startActivityForResult(Intent.createChooser(intent, "Selecione uma imagem"), COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // caso seja o retorno da seleção de imagem
        if( requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK ){
            if( data != null ){
                // lendo a URI da imagem
                val inputStream = contentResolver.openInputStream(data.getData())

                // transformando o resultado em bitmap
                imageBitmap = BitmapFactory.decodeStream(inputStream)

                // exibindo a imagem na tela
                img_foto_produto.setImageBitmap(imageBitmap)
            }
        }
    }
}
