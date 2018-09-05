package br.com.livrokotlin.listadecompras

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.rowParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onResume() {
        super.onResume()

        // recuperando o adapter
        val adapter = list_view_produtos.adapter as ProdutoAdapter

        // sempre zerar a lista antes de carregar os itens
        adapter.clear()

        // buscando os dados no banco de dados
        database.use {

            // esecutando o select
            select("produtos").exec {
                // criando o parser que montará o objeto do produto
                val parser = rowParser {
                        id: Int,
                        nome: String,
                        quantidade: Int,
                        valor: Double,
                        foto: ByteArray? ->
                                // colunas do banco de dados
                            // montagem do objeto produto, com as colunas do banco
                            Produto(id, nome, quantidade, valor, foto?.toBitmap())
                }

                // criando a lista de produtos com os dados do banco
                var listaProdutos = parseList(parser)

                // limpando dados da lista
                adapter.clear()

                // atribuindo a lista global com todos os itens
                adapter.addAll(listaProdutos)

                //efetuando a multiplicação e soma da quantidade e valor
                // somando todos os itens
                var soma = listaProdutos.sumByDouble { it.valor * it.quantidade }

                // formatando saida
                val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
                txt_total.text = "TOTAL: ${f.format(soma)}"
            }
        }
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

            // removendo do banco de dados
            deletarProduto(idProduto = item.id.toInt())

            // removendo da lista
            produtosAdapter.remove(item)

            toast("Item deletado com sucesso!")

            // retornando que o click foi realizado com sucesso
            true
        }
    }

    // função para remover um item do banco de dados
    fun deletarProduto(idProduto:Int){
        database.use {
            delete(
                    "produtos",
                    "id = {id}",
                    "id" to idProduto
            )
        }
    }
}
