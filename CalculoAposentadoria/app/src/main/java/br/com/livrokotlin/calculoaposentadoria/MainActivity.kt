package br.com.livrokotlin.calculoaposentadoria

import android.app.Activity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : Activity(){
    // sobrescrevendo a função onCreate da Activity original
    override fun onCreate(savedInstanceState: Bundle?) {
        // retormando ou inicializando a instância desta tela / activity
        super.onCreate(savedInstanceState)

        // definindo o arquivo de layout
        setContentView(R.layout.activity_main)

        // selecionando um objeto no layout, com base no seu id
        var spn_sexo = findViewById<Spinner>(R.id.spn_sexo)
        val txt_idade = findViewById<EditText>(R.id.txt_idade)
        val txt_resultado = findViewById<TextView>(R.id.txt_resultado)
        val btn_calcular = findViewById<Button>(R.id.btn_calcular)

        // adicionando os valores ao select "spinner"
        spn_sexo.adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, listOf("Masculino","Feminino"))

        // escutando o clique no botão
        btn_calcular.setOnClickListener {
            // definindo os valores padrões pré preenchidos
            val sexo = spn_sexo.selectedItem as String // definindo o tipo de retorno
            val idade = txt_idade.text.toString().toInt() // necessário converter o objeto retornado para string e depois para inteiro
            var resultado = 0

            // e aqui vai o código ao clicar no botão
            if(sexo == "Masculino"){
                resultado = 65 - idade
            }
            else{
                resultado = 60 - idade
            }

            // atualizando saida
            txt_resultado.text = "Faltam $resultado anos para você se aposentar!"
        }
    }
}