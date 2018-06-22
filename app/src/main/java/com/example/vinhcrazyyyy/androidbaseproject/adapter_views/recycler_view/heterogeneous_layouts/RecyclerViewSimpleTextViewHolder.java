package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view.heterogeneous_layouts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

class RecyclerViewSimpleTextViewHolder extends RecyclerView.ViewHolder {

    TextView label;

    public TextView getLabel() {
        return label;
    }

    public void setLabel(TextView label) {
        this.label = label;
    }

    public RecyclerViewSimpleTextViewHolder(View itemView) {
        super(itemView);
    }
}
