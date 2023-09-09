package com.gradu.lookthat.views.closet

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ClosetRVItemDecoration(private val spanCount: Int, private val horizontal: Int, private val vertical: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left = column * horizontal / spanCount
        outRect.right = horizontal - (column + 1) * horizontal / spanCount
        outRect.bottom = vertical

    }
}