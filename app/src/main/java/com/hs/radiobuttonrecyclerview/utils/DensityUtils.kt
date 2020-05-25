package com.hs.radiobuttonrecyclerview.utils

import android.content.Context
import android.util.TypedValue

class DensityUtils {

    companion object{

        fun dp2px(context: Context, dp: Float) : Int = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }
}