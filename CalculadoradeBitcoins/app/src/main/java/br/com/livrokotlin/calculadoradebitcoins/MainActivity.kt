package br.com.livrokotlin.calculadoradebitcoins

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.bloco_cotacao.*
import kotlinx.android.synthetic.main.bloco_entrada.*
import kotlinx.android.synthetic.main.bloco_entrada_bitcoin.*
import kotlinx.android.synthetic.main.bloco_saida.*
import kotlinx.android.synthetic.main.bloco_saida_reais.*
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val API_URL = "https://www.mercadobitcoin.net/api/BTC/ticker/"
    var cotacaoBitcoin: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscarCotacao()

        // listener do botão calcular btc
        btn_calcular.setOnClickListener{
            calcular()
        }

        // listener do botão calcular reais
        btn_calcular_real.setOnClickListener{
            calcularReais()
        }
    }

    fun buscarCotacao(){
        // acessando de forma assíncrona, evitando travamento do app nesta linha
        // no caso do site demorar responder
        doAsync{
            // acessar api REST
            val resposta = URL(API_URL).readText()

            // recuperando o valor da última cotação dentro do objeto json
            cotacaoBitcoin = JSONObject(resposta).getJSONObject("ticker").getDouble("last")

            // formatação em moeda
            val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            val cotacaoFormatada = f.format(cotacaoBitcoin)

            uiThread {
                txt_cotacao.setText("$cotacaoFormatada")
            }
        }
    }

    fun calcular(){
        if(txt_valor.text.isEmpty()){
            txt_valor.error = "Preencha um valor"

            return
        }

        // valor digitado pelo usuário
        val valor_digitado = txt_valor.text.toString()
                .replace(",",".")
                .toDouble()

        // calculando a divisão, se a cotação for zero, manter zero
        val resultado = if(cotacaoBitcoin > 0) valor_digitado / cotacaoBitcoin else 0.0

        // atualizando quantidade de bitcoins com oito casas decimais
        txt_qtd_bitcoins.text = "%.8f".format(resultado)
    }

    fun calcularReais(){
        if(txt_valor_btc.text.isEmpty()){
            txt_valor_btc.error = "Preencha um valor"

            return
        }

        // valor digitado pelo usuário
        val valor_digitado = txt_valor_btc.text.toString()
                .replace(",",".")
                .toDouble()

        // calculando a multiplicacao, se a cotação for zero, manter zero
        val resultado = if(cotacaoBitcoin > 0) valor_digitado * cotacaoBitcoin else 0.0

        // atualizando quantidade de bitcoins com oito casas decimais
        txt_qtd_reais.text = "%.2f".format(resultado)
    }
}
