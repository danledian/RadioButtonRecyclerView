package com.hs.radiobuttonrecyclerview.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hs.radiobuttonrecyclerview.R

class DividerItemDecoration(private val context: Context): RecyclerView.ItemDecoration() {

    private var drawable: Drawable? = ColorDrawable(context.resources.getColor(R.color.aquamarine))
    private val dividerWidth = DensityUtils.dp2px(context, 1.5f)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        if(parent.childCount == 0){
            return
        }

        drawLines(c, parent, getSpanCount(parent.layoutManager))
    }

    private fun drawLines(c: Canvas, parent: RecyclerView, spanCount: Int) {

        for (index in 0 until parent.childCount){
            val childView = parent.getChildAt(index)
            if(index%spanCount == 0){
                drawLeftLine(c, childView)
            }
            if(index < spanCount){
                drawTopLine(c, childView)
            }
            drawRightLine(c, childView)
            drawBottomLine(c, childView)
        }
    }

    private fun drawLeftLine(c: Canvas, childView: View) {
        drawable?.bounds = Rect(childView.left, childView.top, childView.left + dividerWidth, childView.bottom)
        drawable?.draw(c)
    }

    private fun drawTopLine(c: Canvas, childView: View) {
        drawable?.bounds = Rect(childView.left, childView.top, childView.right, childView.top + dividerWidth)
        drawable?.draw(c)
    }

    private fun drawRightLine(c: Canvas, childView: View) {
        val right = childView.right - dividerWidth
        drawable?.bounds = Rect(right, childView.top, right + dividerWidth, childView.bottom)
        drawable?.draw(c)
    }

    private fun drawBottomLine(c: Canvas, childView: View) {
        drawable?.bounds = Rect(childView.left, childView.bottom, childView.right, childView.bottom+dividerWidth)
        drawable?.draw(c)
    }

    /**
     * 获取块数
     */
    private fun getSpanCount(layoutManager: RecyclerView.LayoutManager?): Int {
        return when (layoutManager) {
            is GridLayoutManager -> {
                layoutManager.spanCount
            }
            is StaggeredGridLayoutManager -> {
                layoutManager.spanCount
            }
            else -> {
                1
            }
        }
    }
}