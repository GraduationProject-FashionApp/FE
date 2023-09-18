package com.gradu.lookthat.views.search

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SearchResultRVItemDecoration(
    private val spanCount: Int,
    private val horizontalSpacing: Int,
    private val verticalSpacing: Int,
    private val edgeSpacing: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount // Current column in grid

        // Left spacing
        outRect.left = if (column == 0) edgeSpacing else horizontalSpacing / 2

        // Right spacing
        outRect.right = if (column == spanCount - 1) edgeSpacing else horizontalSpacing / 2

        // Top spacing
        if (position < spanCount) { // first row
            outRect.top = edgeSpacing + 20 // 36dp - 16dp
        } else {
            outRect.top = verticalSpacing / 2
        }

        // Bottom spacing
        if (position >= state.itemCount - spanCount) { // last row
            outRect.bottom = edgeSpacing + 16 // 48dp - 32dp
        } else {
            outRect.bottom = verticalSpacing / 2
        }
    }
}
