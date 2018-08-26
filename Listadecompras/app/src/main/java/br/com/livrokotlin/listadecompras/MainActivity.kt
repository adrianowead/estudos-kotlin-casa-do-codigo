package br.com.livrokotlin.listadecompras

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // definindo o adaptador para armazenar as informações da lista
        val produtosAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_expandable_list_item_1
        )

        // ao clicar no botão de adicionar
        btn_adicionar.setOnClickListener {
            // cirando uma Intent explícita para abrir a tela de cadastro
            val intent = Intent(this, CadastroActivity::class.java)

            // iniciando a atividade
            startActivity(intent)
        }

        // ligando o adaptador no elemento de tela
        list_view_produtos.adapter = produtosAdapter

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
