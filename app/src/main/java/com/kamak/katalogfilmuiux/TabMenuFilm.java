package com.kamak.katalogfilmuiux;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kamak.katalogfilmuiux.NowPlaying.NowPlaying;
import com.kamak.katalogfilmuiux.Upcoming.Upcoming;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabMenuFilm extends Fragment {
    public static TabLayout tabLayout;
    public static int items=2;
    public static ViewPager viewPager;


    public TabMenuFilm() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_tab_menu_film, container, false);
        tabLayout=v.findViewById(R.id.tabs);
        viewPager=v.findViewById(R.id.viewpager);
        viewPager.setAdapter(new GrafikTabAdapter(getChildFragmentManager()));

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);

            }
        });
        return v;
    }


     class GrafikTabAdapter extends FragmentPagerAdapter {
        public GrafikTabAdapter(FragmentManager childFragmentManager) {
            super(childFragmentManager);
        }

         @Override
         public Fragment getItem(int i) {
            switch (i){
                case 0:
                    return new NowPlaying();

                case 1:
                    return new Upcoming();
            }
             return null;
         }

         @Override
         public int getCount() {

             return items;
         }

         @Nullable
         @Override
         public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return "Now Playing";
                case 1:
                    return "Upcoming";
            }

             return null;
         }
     }
}
