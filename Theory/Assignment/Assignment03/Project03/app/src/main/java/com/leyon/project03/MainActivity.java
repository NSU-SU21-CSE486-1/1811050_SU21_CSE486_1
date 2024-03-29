package com.leyon.project03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    //these variables used when replying via intent in Tab3->Submit()
    public static final String USERDATA = "USERDATA";
    public static final String UNIVERSITYDATA = "UNIVERSITYDATA";

    TabLayout tabLayout;
    ViewPager2 viewPager;
    public static TabPagerAdapter tabPagerAdapter; //this static to get data using its custom FindItemByID function

    //public static Fragment tab1 = new Tab1();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.pager);

        tabPagerAdapter = new TabPagerAdapter(this);
        tabPagerAdapter.addFragment(new Tab1());
        tabPagerAdapter.addFragment(new Tab2());
        tabPagerAdapter.addFragment(new Tab3());

        viewPager.setAdapter(tabPagerAdapter);

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

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (position == 0) {
                    tab.setText("Personal");
                } else if (position == 1) {
                    tab.setText("University");
                }else if (position == 2) {
                    tab.setText("Contact");
                }
            }
        });
        tabLayoutMediator.attach();
    }


}