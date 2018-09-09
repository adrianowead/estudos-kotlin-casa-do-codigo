package br.com.livrokotlin.calculadoradebitcoins

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.alert
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    val API_URL = "https://www.mercadobitcoin.net/api/BTC/ticker/"
    var cotacaoBitcoin: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buscarCotacao()
    }

    fun buscarCotacao(){
        // acessando de forma assíncrona, evitando travamento do app nesta linha
        // no caso do site demorar responder
        doAsync{
            // acessar api REST
            val resposta = URL(API_URL).readText()

            // recuperando o valor da última cotação dentro do objeto json
            cotacaoBitcoin = JSONObject(resposta).getJSONObject("ticker").getDouble("last")

            uiThread {
                alert("$cotacaoBitcoin").show()
            }
        }
    }
}
