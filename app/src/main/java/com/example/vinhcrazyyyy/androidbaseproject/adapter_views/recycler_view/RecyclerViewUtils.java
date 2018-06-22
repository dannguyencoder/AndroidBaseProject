package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.recycler_view;

import android.content.Context;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MotionEvent;

import java.util.List;

import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class RecyclerViewUtils {

    Context context;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<Contact> contacts;

    public RecyclerViewUtils(RecyclerView recyclerView, Adapter adapter, List<Contact> contacts) {
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.contacts = contacts;
    }

    /*If you are inserting elements to the front of the list and wish to maintain the position
    * at the top, we can set the scroll position to the 1st elemtn*/
    private void scrollToTop() {
        adapter.notifyItemInserted(0);
        recyclerView.scrollToPosition(0);
    }

    /*If we are adding items to the end and wish to scroll to the bottom as items are added,
    * we can notify the adapter that an additional element has been added and call
    * smoothScrollToPostition() on the RecyclerView*/
    private void scrollToBottom() {
        adapter.notifyItemInserted(contacts.size() -  1);
        recyclerView.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    /*We can also enable optimizations if the items are static and will not change
    * for significantly smoother scrolling*/
    private void optimizeForFixedSize() {
        recyclerView.setHasFixedSize(true);
    }

    /*Layouts
    * The positioning of the items is configured using the layout manager.
    * By default, we can choose between LinearLayoutManager, GridLayoutManager,
    * and StaggeredGridLayoutManager. Linear displays items either vertically
    * or horizontally:*/
    private void changeLayoutOfRecyclerView(RecyclerView recyclerView
            , LayoutManager layoutManager) {
        // Setup layout manager for items with orientation
        // Also supports `LinearLayoutManager.HORIZONTAL`
        LinearLayoutManager linearLayoutManagerLayoutManager =
                new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,
                        false);
        // Optionally customize the position you want to default scroll to
        linearLayoutManagerLayoutManager.scrollToPosition(0);
        // Attach layout manager to the RecyclerView
        recyclerView.setLayoutManager(linearLayoutManagerLayoutManager);

        /*Displaying items in a grid or staggered grid works similarly:*/
        // First param is number of columns and second param is orientation
        // i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(2,
                        StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    /*Decorations

    We can decorate the items using various decorators attached to the recyclerview
    such as the DividerItemDecoration:*/
    private void addItemDecoration() {
        RecyclerView.ItemDecoration itemDecoration = new
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
    }

    /*Animators
    * RecyclerView supports custom animations as they enter, move, or get deleted
    * using ItemAnimator. The default animation effects is defined by "DefaultItemAnimator",
    * and the complex imlementation (see source code) shows that the logic neccessary
    * to ensure that animation effects are performed in a specific sequence (remove, move
    * and add).
    *
    * Currently, the fastest way to implement animations with RecyclerView is to use
    * third-party library. The third-party recyclerview_animators-library contains
    * a lot of animations that you can use without needing to build your own.
    * Simply edit your app/build.gradle to add library
    * */
    private void setAnimatorsForRecylerView() {
        recyclerView.setItemAnimator(new SlideInUpAnimator());
    }

    private void handleTouchEvent() {
        recyclerView.addOnItemTouchListener(new OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                //handle touch event here
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }

    /*Snap to center effect*/
    /*In certain cases, we might want a horizontal RecyclerView that allows the user
    * to scroll through a list of items. As the user scrolls, we might want items
    * to "snap to center" as they are revealed. Such as in this example*/

    //1. LinearSnapHelper
    //To achieve this snapping to center effect as user scrolls, starting with
    //support library 24.2.0 and greater, we can use the built-in LinearSnapHelper
    //as follows
    private void addSnapToCenterEffect() {
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    //Using SnappyRecyclerView
    private void usingSnappyRecyclerView() {
        SnappyRecyclerView snappyRecyclerView = new SnappyRecyclerView(context);

        snappyRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        snappyRecyclerView.setAdapter(adapter);

        //You can access the currently "snapped" item position with
        snappyRecyclerView.getFirstVisibleItemPosition();
    }
}
