package com.example.vinhcrazyyyy.androidbaseproject.viewpager_tabs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinhcrazyyyy.androidbaseproject.R;

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[]{"Tab1", "Tab2", "Tab3"};

    //use this if you want yo use icons
//    private int imageResId[] = {R.drawable.ic_one,
//            R.drawable.ic_two,
//            R.drawable.ic_three};

    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //set null if you want to use icons instead
        return tabTitles[position];
    }

    //use this if you want to use tab title with icon
//    public CharSequence getPageTitle(int position) {
//        //Generate title based on item position
//        Drawable image = context.getResources().getDrawable(imageResId[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//
//        //Replace blank spaces with image icon
//        SpannableString sb = new SpannableString(" " + tabTitles[position]);
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
//    }

    //Use this if you want to set custom view to tab
//    public View getTabView(int position) {
//        //Given you have a custom layout in res/layout/custom_tab.xml with a TextView and ImageView
//        View v = LayoutInflater.from(context).inflate(R.layout.custom_tab, null);
//        TextView tv = v.findViewById(R.id.text_view);
//        tv.setText(tabTitles[position]);
//        ImageView img = v.findViewById(R.id.image_view);
//        img.setImageResource(imageResId[position]);
//        return v;
//    }
}
