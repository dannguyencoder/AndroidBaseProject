package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view.heterogeneous_layouts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinhcrazyyyy.androidbaseproject.R;

import java.util.List;

public class ComplexRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int USER = 0, IMAGE = 1;
    //the items to display in your RecyclerView
    private List<Object> items;

    //Provide a suitable constructor (depends on the kind of dataset)
    public ComplexRecyclerViewAdapter(List<Object> items) {
        this.items = items;
    }

    //Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.items.size();
    }

    /*Now you need to override the getItemViewType method to tell RecyclerView
     * about the type of view to inflate based on the position.
     * We will return USER or IMAGE based on the type of object in the data we have*/
    //Returns the view type of the item at position for the purposes of view recycling
    @Override
    public int getItemViewType(int position) {
        if (items.get(position) instanceof User) {
            return USER;
        } else if (items.get(position) instanceof String) {
            return IMAGE;
        }
        return -1;
    }

    /*Next, you need to override the onCreateViewHolder method to tell
    the RecyclerView.Adapter about which RecyclerView.ViewHolder object to create
    based on the viewType returned. Create ViewHolder1 with view layout_viewholder1
    for ODD items and ViewHolder2 for view layout_viewholder2 for EVEN ones
    * */

    /**
     * This method creates different RecyclerView.ViewHolder objects based on\
     * the item view type
     *
     * @param parent   ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case USER:
                View v1 = inflater.inflate(R.layout.layout_viewholder1, parent, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case IMAGE:
                View v2 = inflater.inflate(R.layout.layout_viewholder2, parent, false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
                View v = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
                viewHolder = new RecyclerViewSimpleTextViewHolder(v);
                break;
        }
        return viewHolder;
    }

    /**Next, override the onBindViewHolder method to configure the ViewHolder with actual
     * data needs to be displayed. Distinguish the two different layouts and load them
     * with sample text and image as follows*/

    /**
     * This method internally calls onBindViewHolder(ViewHolder, int) to update
     * the RecyclerView.ViewHolder contents with the item at the given position
     * and also sets up some private fields to be used with RecyclerView
     *
     * @param holder   The type of RecyclerView.ViewHolder to populate
     * @param position Item position in the viewgroup
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case USER:
                ViewHolder1 vh1 = (ViewHolder1) holder;
                configureViewHolder1(vh1, position);
                break;
            case IMAGE:
                ViewHolder2 vh2 = (ViewHolder2) holder;
                configureViewHolder2(vh2, position);
                break;
            default:
                RecyclerViewSimpleTextViewHolder vh = (RecyclerViewSimpleTextViewHolder) holder;
                configureDefaultViewHolder(vh, position);
                break;
        }
    }

    /*The following method are used for configuring the individual RecyclerView.ViewHolder
    * objects*/
    private void configureDefaultViewHolder(RecyclerViewSimpleTextViewHolder vh, int position) {
        vh.getLabel().setText((CharSequence) items.get(position));
    }

    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        User user = (User) items.get(position);
        if (user != null) {
            vh1.getLabel1().setText("Name: "+  user.name);
            vh1.getLabel2().setText("Movie: " + user.movie);
        }
    }

    private void configureViewHolder2(ViewHolder2 vh2, int position) {
        vh2.getIvExample().setImageResource(R.drawable.sample_golden_state);
    }

}
