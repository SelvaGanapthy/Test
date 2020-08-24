package com.example.test.helpers

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView


class CustomTextView : AppCompatTextView {

    private var textStyle: Int = 0
    private var customFont: Typeface? = null

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
//        applyCustomFont(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
//        applyCustomFont(context, attrs)
    }

    private fun applyCustomFont(context: Context, attrs: AttributeSet) {
        textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", Typeface.NORMAL)
//        customFont = selectTypeface(context, textStyle)
//        typeface = customFont
    }

    private fun selectTypeface(context: Context, textStyle: Int): Typeface {
        when (textStyle) {

            Typeface.BOLD // bold
            -> return TypeFaceProvider.getFontForTextView(
                context,
                "fonts/Montserrat-Regular.otf"
            )
            Typeface.NORMAL // regular
            -> return TypeFaceProvider.getFontForTextView(context, "fonts/Montserrat-Light.otf")
            else -> return TypeFaceProvider.getFontForTextView(
                context,
                "fonts/Montserrat-Light.otf"
            )

        }
    }

    companion object {
        val ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android"
    }


}
