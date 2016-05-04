package com.mybest.dessertmaker.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.inthecheesefactory.thecheeselibrary.view.SlidingTabLayout;
import com.mybest.dessertmaker.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MoreInfoFrament extends Fragment {
    ViewPager viewPager;
    SlidingTabLayout slidingTabLayout;

    public MoreInfoFrament() {
        super();
    }

    public static MoreInfoFrament newInstance() {
        MoreInfoFrament fragment = new MoreInfoFrament();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fagment_more_info, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // init instance with rootView.findViewById here
        //setRetainInstance(true);
        viewPager = (ViewPager)rootView.findViewById(R.id.viewPager);
        viewPager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return MoreInfoMainFrament.newInstance();
            }

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public CharSequence getPageTitle(int position) {

                switch (position) {
                    case  0:
                        return "Main";
                    case  1:
                        return  "Ingredients";
                    case  2:
                        return  "Directions";
                }
                return  "";
            }
        });



        slidingTabLayout = (SlidingTabLayout)rootView.findViewById(R.id.slidingTabLayout);
        slidingTabLayout.setViewPager(viewPager);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

}
