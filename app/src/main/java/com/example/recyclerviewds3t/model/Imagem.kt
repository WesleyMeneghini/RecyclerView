package com.example.recyclerviewds3t.model

class Imagem {

    var fileName: String = ""
    var mimeType: String = ""
    var base64: String = ""

    override fun toString(): String {
        return "Imagem(fileName='$fileName', mimeType='$mimeType', base64='$base64')"
    }


}