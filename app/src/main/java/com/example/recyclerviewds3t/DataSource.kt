package com.example.recyclerviewds3t

import com.example.recyclerviewds3t.http.HttpHelper
import com.example.recyclerviewds3t.model.Dentista
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DataSource {

    companion object {
        fun createDataset() {

            doAsync {
                var http = HttpHelper()
                var lista = http.getDentistas()

                uiThread {
                    println("XXXXXXXXXX ---- $lista")
                }
            }


        }
    }


}