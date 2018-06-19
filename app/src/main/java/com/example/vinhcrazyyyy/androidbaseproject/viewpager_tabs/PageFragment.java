package com.example.vinhcrazyyyy.androidbaseproject.viewpager_tabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.vinhcrazyyyy.androidbaseproject.BaseFragment;
import com.example.vinhcrazyyyy.androidbaseproject.R;

public class PageFragment extends BaseFragment {



    public static final String ARB_PAGE = "ARG_PAGE";

    private int mPage;

    public static Fragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARB_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARB_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText("Fragment #" + mPage);
        return  view;
    }

    @Override
    public int getFragmentLayout() {
        return 0;
    }
}
