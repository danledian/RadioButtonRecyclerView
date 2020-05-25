package com.hs.radiobuttonrecyclerview.utils

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class FullDividerItemDecoration(private val context: Context): RecyclerView.ItemDecoration() {

    private var drawable: Drawable? = ColorDrawable(context.resources.getColor(android.R.color.holo_blue_bright))
    private val dividerWidth = 2

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {

        if(parent.childCount == 0){
            return
        }
        val spanCount = getSpanCount(parent.layoutManager)

        drawHorizontalLine(c, parent, spanCount)
        drawVerticalLine(c, parent, spanCount)
    }

    /**
     * 绘制水平方向分割线
     */
    private fun drawHorizontalLine(c: Canvas, parent: RecyclerView, spanCount: Int) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        val lineCount = parent.childCount/spanCount + (if(parent.childCount%spanCount>0) 1 else 0) + 1
        for (index in 0 until lineCount){
            val top = if (index == 0){
                parent.paddingTop
            } else {
                val view = parent.getChildAt((index-1)*spanCount)
                val layoutParams = view.layoutParams as RecyclerView.LayoutParams
                layoutParams.bottomMargin + view.bottom
            }
            drawable?.bounds = Rect(left, top, right, top + dividerWidth)
            drawable?.draw(c)
        }
    }

    /**
     * 绘制垂直方向分割线
     */
    private fun drawVerticalLine(c: Canvas, parent: RecyclerView, spanCount: Int) {

        val top = parent.paddingTop
        val bottom  = parent.getChildAt(parent.childCount-1).bottom

        Log.i("test", "spanCount:$spanCount")

        for (index in 0 until spanCount+1){
            val right = if(index == 0){
                parent.paddingLeft
            }else{
                val view = parent.getChildAt(index-1)
                Log.i("test", "view right is ${view.right}")
                val layoutParams = view.layoutParams as RecyclerView.LayoutParams
                layoutParams.rightMargin + view.right - dividerWidth
            }
            Log.i("test", "margin:$right")
            drawable?.bounds = Rect(right, parent.paddingTop, right + dividerWidth, bottom)
            drawable?.draw(c)
        }
    }

    /**
     * 获取块数
     */
    private fun getSpanCount(layoutManager: RecyclerView.LayoutManager?): Int {
        return when (layoutManager) {
            is GridLayoutManager -> {
                (layoutManager as GridLayoutManager).spanCount
            }
            is StaggeredGridLayoutManager -> {
                (layoutManager as StaggeredGridLayoutManager).spanCount
            }
            else -> {
                1
            }
        }
    }
}