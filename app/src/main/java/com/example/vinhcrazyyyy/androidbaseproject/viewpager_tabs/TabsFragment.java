package com.example.vinhcrazyyyy.androidbaseproject.viewpager_tabs;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;

public class TabsFragment extends BaseFragment {

    //to save the state of the current oepning tab when configuration change
    public static String POSITION = "POSITION";

    //use this if you want yo use icons
//    private int imageResId[] = {R.drawable.ic_one,
//            R.drawable.ic_two,
//            R.drawable.ic_three};


    //save current tab position
//    @Override
//    public void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putInt(POSITION, tabLayout.getSelectedTabPosition());
//    }

//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
//    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = getView().findViewById(R.id.viewpager);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(((AppCompatActivity) view.getContext()).getSupportFragmentManager(), getActivity()));

        TabLayout tabLayout = getView().findViewById(R.id.sliding_tabs);
//        use this if you want to use icons only
//        setIcons(tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        //restore state of currently opening tab when configuration change
//        viewPager.setCurrentItem(savedInstanceState.getInt(POSITION));
    }

//    private void setIcons(TabLayout tabLayout) {
//        for (int i = 0; i < imageResId.length; i++) {
//            tabLayout.getTabAt(i).setIcon(imageResId[i]);
//        }
//    }

    //use this if you want to set custom view to tab
//    private void setCustomView(TabLayout tabLayout) {
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            TabLayout.Tab tab = tabLayout.getTabAt(i);
//            tab.setCustomView(pagerAdapter.getTabView(i));
//        }
//    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_tabs;
    }
}
