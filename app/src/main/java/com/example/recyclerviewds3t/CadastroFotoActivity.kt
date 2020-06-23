package com.example.recyclerviewds3t

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.example.recyclerviewds3t.http.HttpHelper
import com.example.recyclerviewds3t.model.Imagem
import com.example.recyclerviewds3t.util.bitmapToBase64
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_cadastro_foto.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast

class CadastroFotoActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_foto)

        //Toast.makeText(this, "Abriu", Toast.LENGTH_SHORT).show()
        toast("Abriu")

        faFoto.setOnClickListener(this)
        buttonSalvar.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val id = view.id

        if (id == R.id.faFoto){
            abrirGaleriaDeFotos()
        } else if (id == R.id.buttonSalvar){
            enviarFoto()
        }

    }

    fun enviarFoto(){

        val imagem = Imagem()
        imagem.fileName = editNomeArquivo.text.toString()
        imagem.mimeType = "image/jpg"
        imagem.base64 = bitmapToBase64(imageFoto.drawable.toBitmap())

        // Converter o objeto imagem em Json
        val gson = Gson()
        val imagemJson = gson.toJson(imagem)

        // Enviar a foto para o servidor Firebase
        doAsync {
            var http = HttpHelper()
            http.enviarImagem(imagemJson)
        }

    }

    private fun abrirGaleriaDeFotos() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Selecionar Imagem"), 4587)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (resultCode == -1 && data != null){
            val input = contentResolver.openInputStream(data.data!!)
            var bitmap = BitmapFactory.decodeStream(input)
            imageFoto.setImageBitmap(bitmap)
        }

        super.onActivityResult(requestCode, resultCode, data)
    }
}
