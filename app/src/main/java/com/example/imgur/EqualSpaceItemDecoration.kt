package com.example.imgur

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class EqualSpaceItemDecoration : RecyclerView.ItemDecoration() {
    var mSpaceHeight = 20
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = mSpaceHeight
    }
}