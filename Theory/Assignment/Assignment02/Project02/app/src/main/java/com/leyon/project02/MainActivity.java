package com.leyon.project02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager2 viewPager;
    public static PagerAdapter pagerAdapter; //this static to get data using its custom FindItemByID function

    //public static Fragment tab1 = new Tab1();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pager);

        pagerAdapter = new PagerAdapter(this);
        pagerAdapter.addFragment(new Tab1());
        pagerAdapter.addFragment(new Tab2());
        pagerAdapter.addFragment(new Tab3());

        viewPager.setAdapter(pagerAdapter);
        //viewPager.setOrientation(ViewPager2.ORIENTATION_VERTICAL);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                return;
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                return;
            }
        } );
    }

}