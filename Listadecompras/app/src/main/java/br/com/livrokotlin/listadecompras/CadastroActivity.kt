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

            // inserir apenas se não estiver vazia
            if( produto.isNotEmpty() ){
                // limpar o campo ao inserir
                txt_produto.text.clear()
            }
            // caso contrário, disparar um erro
            else {
                txt_produto.error = "Preencha um valor"
            }
        }
    }
}
