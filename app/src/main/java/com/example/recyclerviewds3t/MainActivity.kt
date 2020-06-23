package com.example.recyclerviewds3t

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerviewds3t.adapter.DentistaRecyclerAdapter
import com.example.recyclerviewds3t.http.HttpHelper
import com.example.recyclerviewds3t.model.Dentista
import kotlinx.android.synthetic.main.activity_cadastro_foto.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var dentistaRecyclerAdapter: DentistaRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inicializarRecyclerView()

        faCadastro.setOnClickListener(this)

        doAsync {
            val http = HttpHelper()
            http.getDentistas()
        }

    }

    override fun onClick(view: View) {

        val id = view.id

        if (id == R.id.faCadastro){
            var intent = Intent(this, CadastroFotoActivity::class.java)
            startActivity(intent)
        }

    }

    private fun inicializarRecyclerView(){

        doAsync {
            var http = HttpHelper()
            var lista = http.getDentistas()

            uiThread {
                rvDentistas.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                dentistaRecyclerAdapter = DentistaRecyclerAdapter(lista)
                rvDentistas.adapter = dentistaRecyclerAdapter
            }
        }



    }
}
