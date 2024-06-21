package com.msaggik.common_util

import android.text.InputFilter
import android.text.Spanned

class InputFilterCyrillic : InputFilter {
    override fun filter(
        p0: CharSequence?,
        p1: Int,
        p2: Int,
        p3: Spanned?,
        p4: Int,
        p5: Int
    ): CharSequence? {
        var input: String? = ""
        for (i in p1..<p2) {
            val symbol = p0?.get(i)
            if(symbol != null) {
                val isCyrillic = Character.UnicodeBlock.of(symbol) == Character.UnicodeBlock.CYRILLIC
                if(isCyrillic || symbol == ' ' || symbol == '-') input = null
            }
        }
        return input
    }
}