package br.com.livrokotlin.listadecompras

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onResume() {
        super.onResume()

        // recuperando o adapter
        val adapter = list_view_produtos.adapter as ProdutoAdapter

        // sempre zerar a lista antes de carregar os itens
        adapter.clear()

        // atribuindo a lista global com todos os itens
        adapter.addAll(produtosGlobal)

        // somando todos os itens
        var soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }

        // formatando saida
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
        txt_total.text = "TOTAL: ${f.format(soma)}"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // definindo o adaptador para armazenar as informações da lista
        val produtosAdapter = ProdutoAdapter(this)

        // ao clicar no botão de adicionar
        btn_adicionar.setOnClickListener {
            // acessando a tela de cadastro
            startActivity<CadastroActivity>()
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
