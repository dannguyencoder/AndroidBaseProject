package com.example.vinhcrazyyyy.androidbaseproject.adapter_views.cardview;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;

/*Android 5.0 introduces a new widget called CardViewFragment which essentially can be thought
* of as a FrameLayout with rounded corners and shadow based on its elevation. Note that
* a CardViewFragment */
public class CardViewFragment extends BaseFragment {
    @Override
    protected void setup(View view) {
        /*Note that the card_view:cardElevation is used to determine the size ans softness
        * of the shadow so as to realistically depict the depth. These properties
        * can be set programmatically as well*/
        CardView cardView = view.findViewById(R.id.card_view);
        cardView.setCardBackgroundColor(Color.parseColor("#E6E6E6"));
        cardView.setMaxCardElevation((float) 0.0);
        cardView.setRadius((float) 5.0);

    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_cardview;
    }
}
