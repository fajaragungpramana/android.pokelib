package com.github.fajaragungpramana.pokelib.extension

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

fun Number.asDigit(
    groupSeparator: Char = '.',
    decimalSeparator: Char = ',',
    prefix: String = "",
    suffix: String = ""
): String {
    val decimalFormat = DecimalFormat().apply {
        decimalFormatSymbols = DecimalFormatSymbols.getInstance().apply {
            this.groupingSeparator = groupSeparator
            this.decimalSeparator = decimalSeparator
        }
    }
    return "$prefix ${decimalFormat.format(this)} $suffix"
}