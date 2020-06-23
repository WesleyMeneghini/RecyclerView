package com.example.recyclerviewds3t.http

import com.example.recyclerviewds3t.model.Dentista
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.Request
import org.json.JSONArray

class HttpHelper {

    fun getDentistas(): ArrayList<Dentista>{

        // determinar a URL do endpoint no servidor
        val URL = "http://192.168.15.12:8080/odonto/dentistas"

        // Criar um cliente Http
        val client = OkHttpClient()

        // Construir a requisição http para o servidor
        val request = Request.Builder().url(URL).get().build()

        // Criar a resposta do servidor
        val response = client.newCall(request).execute()

        // Extrair o json do body da resposta
        val responseBody = response.body()

        // Criar uma coleção de dentistas
        var listaDentistas = ArrayList<Dentista>()

        if (responseBody != null){
            var dentistasJson = responseBody.string()
            var dentistasArray = JSONArray(dentistasJson)

            for (i in 0 until dentistasArray.length()){
                val dentistaJson = dentistasArray.getJSONObject(i)

                val dentista = Dentista(
                    dentistaJson.optInt("codigo"),
                    dentistaJson.optString("nome"),
                    dentistaJson.optString("cro"),
                    dentistaJson.optString("email"),
                    dentistaJson.optString("telefone")
                )

                listaDentistas.add(dentista)
            }
        }
        return listaDentistas
    }

    fun enviarImagem(json: String) : String {

        // determinar a URL do endpoint no servidor
        val URL = "http://192.168.15.12:8080/odonto/upload"

        // definir o cabeçalho da requisição
        val headerHttp = MediaType.parse("application/json; charset=utf-8")

        // Criar um cliente Http
        var client = OkHttpClient()

        // Body da requisição
        val body = RequestBody.create(headerHttp, json)

        // Construir a requisição http para o servidor
        var request = Request.Builder().url(URL).post(body).build()

        // Criar a resposta do servidor
        var response = client.newCall(request).execute()

        // retorna a resposta do servidor para o cliente
        return response.body().toString()

    }
}