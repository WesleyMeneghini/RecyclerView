package com.example.recyclerviewds3t.util

import android.graphics.Bitmap
import java.io.ByteArrayOutputStream
import android.util.Base64

fun bitmapToBase64(bitmap: Bitmap): String {

    // Criar um fluxo de saída - array de bytes
    val byteArrayOutputStream = ByteArrayOutputStream()

    // Comprimir o bitmap e colocar os bytes no fluxo de saída (byteArrayOutputStream)
    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

    // Converter fluxo de bytes em um array de bytes
    val imagemArray = byteArrayOutputStream.toByteArray()

    // Converter a imagem em base64
    val imagemBase64 = Base64.encodeToString(imagemArray, Base64.NO_WRAP)

    return imagemBase64
}