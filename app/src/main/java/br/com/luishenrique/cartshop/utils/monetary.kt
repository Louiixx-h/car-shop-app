package br.com.luishenrique.cartshop.utils

import java.lang.NumberFormatException
import java.text.NumberFormat

fun String.convertToBRL(): String {
    val nf: NumberFormat = NumberFormat.getCurrencyInstance()

    return try {
        "R$ "+nf.format(this.toDouble() / 100)
    } catch (e: NumberFormatException) {
        ""
    }
}
