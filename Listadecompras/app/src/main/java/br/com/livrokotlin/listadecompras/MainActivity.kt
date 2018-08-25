package br.com.livrokotlin.listadecompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // definindo o adaptador para armazenar as informações da lista
        val produtosAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1
        )

        // ligando o adaptador no elemento de tela
        list_view_produtos.adapter = produtosAdapter

        // ao clicar no botão de inserir
        btn_inserir.setOnClickListener {
            val produto = txt_produto.text.toString()

            // inserir apenas se não estiver vazia
            if( produto.isNotEmpty() ){
                produtosAdapter.add(produto)

                // limpar o campo ao inserir
                txt_produto.text.clear()
            }
            // caso contrário, disparar um erro
            else {
                txt_produto.error = "Preencha um valor"
            }
        }

        // ao tocar e segurar em um item por alguns segundos
        list_view_produtos.setOnItemLongClickListener {
            parent: AdapterView<*>?, view: View?, position: Int, id: Long ->

            // buscando o item clicado
            val item = produtosAdapter.getItem(position)

            // removendo da lista
            produtosAdapter.remove(item)

            // retornando que o click foi realizado com sucesso
            true
        }
    }
}
