package br.com.livrokotlin.listadecompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_cadastro.*

class CadastroActivity : AppCompatActivity() {

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

                val prod = Produto(produto, qtd.toInt(), valor.toDouble(), null)

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
    }
}
