package br.com.livrokotlin.listadecompras

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

// Access property for Context
val Context.database: ListaComprasDatabase
    get() = ListaComprasDatabase.getInstance(getApplicationContext())

// extendendo a classe principal
class ListaComprasDatabase(context: Context): ManagedSQLiteOpenHelper(
        ctx = context,
        name = "listaCompras.db",
        version =1
) {

    // método obrigatório, é chamado ao inicializar a classe
    override fun onCreate(db: SQLiteDatabase) {
        // criando as tabelas do banco
        db.createTable(
                "produtos",
                true,
                // colunas
                "id" to INTEGER + PRIMARY_KEY + UNIQUE,
                "nome" to TEXT,
                "quantidade" to INTEGER,
                "valor" to REAL,
                "foto" to BLOB
        )
    }

    // método obrigatório, é chamado ao incrementar a versão do banco de dados
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented")
    }

    // criando um singleton da classe
    companion object {
        private var instance: ListaComprasDatabase? = null

        // a notação Synchronized protege conta chamadas concorrentes e multi thread
        @Synchronized
        fun getInstance(ctx: Context): ListaComprasDatabase{
            if(instance == null){
                instance = ListaComprasDatabase(ctx.applicationContext)
            }

            return instance!! // as duas exclamações retorna um valor não nulo
            // caso seja nulo, uma exception é chamada
            // null-safe
        }
    }
}