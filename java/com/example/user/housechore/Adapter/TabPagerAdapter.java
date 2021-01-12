package com.example.user.housechore.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.user.housechore.Fragment.Tab2Fragment;
import com.example.user.housechore.Fragment.Tab3Fragment;
import com.example.user.housechore.Fragment.Tab4Fragment;
import com.example.user.housechore.Fragment.Tab5Fragment;
import com.example.user.housechore.Fragment.Tab1Fragment;

public class TabPagerAdapter extends FragmentStatePagerAdapter {

    int tabCount;

    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                Tab1Fragment tab1 = new Tab1Fragment(); //Measure
                return tab1;
            case 1:
                Tab2Fragment tab2 = new Tab2Fragment(); //Recipe
                return tab2;
            case 2:
                Tab3Fragment tab3 = new Tab3Fragment(); //Bowls
                return tab3;
            case 3:
                Tab4Fragment tab4 = new Tab4Fragment();//Favorite Recipes
                return tab4;
            case 4:
                Tab5Fragment tab5 = new Tab5Fragment(); //Setting
                return tab5;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Override
    public int getItemPosition(Object item) {
            return POSITION_NONE; //이거 해줘야 list가 refresh 됨!!

    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}

