package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view.heterogeneous_layouts;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.vinhcrazyyyy.androidbaseproject.R;

public class ViewHolder1 extends RecyclerView.ViewHolder {

    TextView label1, label2;

    public ViewHolder1(View itemView) {
        super(itemView);
        label1 = itemView.findViewById(R.id.text1);
        label2 = itemView.findViewById(R.id.text2);
    }

    public TextView getLabel1() {
        return label1;
    }

    public void setLabel1(TextView label1) {
        this.label1 = label1;
    }

    public TextView getLabel2() {
        return label2;
    }

    public void setLabel2(TextView label2) {
        this.label2 = label2;
    }
}
