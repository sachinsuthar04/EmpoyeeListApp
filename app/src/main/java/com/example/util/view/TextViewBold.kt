package com.example.util.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import com.example.empoyeelistapp.R


@SuppressLint("AppCompatCustomView")
class TextViewBold : TextView {

    constructor(context: Context) : super(context) {
        val face = Typeface.createFromAsset(
            context.assets,
            context.resources.getString(R.string.font_bold)
        )

        this.typeface = face
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        val face = Typeface.createFromAsset(
            context.assets,
            context.resources.getString(R.string.font_bold)
        )
        this.typeface = face
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        val face = Typeface.createFromAsset(
            context.assets,
            context.resources.getString(R.string.font_bold)
        )
        this.typeface = face
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
    }


}