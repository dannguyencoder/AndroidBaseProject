package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view.item_decorations;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.State;
import android.view.View;

/*Decorator which adds spacing around the tiles in a Grid layout RecyclerView.
* Apply to a RecyclerView with:
*
* SpacesItemDecoration decoration = new SpacesItemDecoration(16)
* mRecyclerview.addItemDecoration(decoration)
* */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private final int mSpace;

    public SpacesItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = mSpace;
        outRect.right = mSpace;
        outRect.bottom = mSpace;
        //Add top margin only to the first item to avoid double space between items
        if (parent.getChildAdapterPosition(view) == 0)
            outRect.top = mSpace;
    }
}
