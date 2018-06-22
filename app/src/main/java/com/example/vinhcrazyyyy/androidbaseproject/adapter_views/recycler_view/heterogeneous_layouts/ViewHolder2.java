package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view.heterogeneous_layouts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.vinhcrazyyyy.androidbaseproject.R;

public class ViewHolder2 extends RecyclerView.ViewHolder {

    private ImageView ivExample;

    public ViewHolder2(View itemView) {
        super(itemView);

        ivExample = itemView.findViewById(R.id.ivExample);
    }

    public ImageView getIvExample() {
        return ivExample;
    }
}
