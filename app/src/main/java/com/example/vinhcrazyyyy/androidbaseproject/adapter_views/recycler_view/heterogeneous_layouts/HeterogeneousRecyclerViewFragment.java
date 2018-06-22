package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view.heterogeneous_layouts;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;

import java.util.ArrayList;

public class HeterogeneousRecyclerViewFragment extends BaseFragment {

    RecyclerView recyclerView;

    private ArrayList<Object> getSampleArrayList() {
        ArrayList<Object> items = new ArrayList<>();
        items.add(new User("Vinh", "Men In Black"));
        items.add(new User("Bin", "Men in Greeen"));
        items.add("image");
        items.add(new User("Bong", "Girl in Red"));
        items.add("image");
        items.add(new User("Thuan", "Girl in Grey"));
        return items;
    }

    @Override
    protected void setup(View view) {
        bindDataToAdapter();
    }

    private void bindDataToAdapter() {
        //Bind adapter to recyclerview view object
        recyclerView.setAdapter(new ComplexRecyclerViewAdapter(getSampleArrayList()));
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
