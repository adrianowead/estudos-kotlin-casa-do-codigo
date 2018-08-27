package br.com.livrokotlin.listadecompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.*

// criando um adaptador customizado
class ProdutoAdapter(contexto: Context): ArrayAdapter<Produto>(contexto, 0){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val v:View

        if( convertView != null ){
            // utilizando o layout já criado
            v = convertView
        }
        else{
            // criando (inflando) o layout
            // o parent é a ListView que estamos trabalhando
            v = LayoutInflater.from(context).inflate(
                    R.layout.list_view_item,
                    parent,
                    false
            )
        }

        // carregando as informações do item
        val txt_produto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txt_qtd = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txt_valor = v.findViewById<TextView>(R.id.txt_item_valor)
        val img_produto = v.findViewById<ImageView>(R.id.img_item_foto)

        val item = getItem(position)

        // preenvhendo os valores
        txt_qtd.text = item.quantidade.toString()
        txt_produto.text = item.nome

        // carregando instância do objeto de formatação
        // para formatar a moeda
        val f = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        txt_valor.text = f.format(item.valor)

        // se tiver definido a foto
        if(item.foto != null){
            img_produto.setImageBitmap(item.foto)
        }

        return v
    }

}