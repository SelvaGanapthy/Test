package com.example.test.helpers

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet

class CustomButton : androidx.appcompat.widget.AppCompatButton {
    constructor(cxt: Context) : super(cxt)
    constructor(cxt: Context, attrs: AttributeSet) : super(cxt, attrs) {
//        setFont(cxt)
    }

    private fun setFont(cxt: Context) {
        val font = Typeface.createFromAsset(cxt.assets, "fonts/Montserrat-Regular.otf")
        setTypeface(font, Typeface.NORMAL)
    }

}


