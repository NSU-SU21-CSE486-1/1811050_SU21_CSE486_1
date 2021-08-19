package com.leyon.project02;

import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends FragmentStateAdapter {

    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position > fragmentList.size()) {
            return null;
        } else {
            return fragmentList.get(position);
        }
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragmentPage) {
        //order of fragment entry is important for it to work as expected in tablayout
        fragmentList.add(fragmentPage);
    }

    public ArrayList<Fragment> getFragments() {
        return (ArrayList<Fragment>) fragmentList;
    }

    /*public View findItemByID(@IdRes int id) {
        View item = null; //result

        for (Fragment tab: fragmentList) {
            View tabView = tab.getView();
            item = tabView.findViewById(id);
            break;
        }

        return item; //the function that called this should cast to proper class
    }*/

    public Fragment getFragment(int pageNumber) {
        return fragmentList.get(pageNumber-1); //index for arraylist start at zero
    }
}
