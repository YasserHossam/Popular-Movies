package com.xware.task.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.xware.task.view.fragment.ReviewsFragment;
import com.xware.task.view.fragment.TrailersFragment;

/**
 * Created by yasser on 10/10/17.
 */

public class MovieViewPagerAdapter extends FragmentStatePagerAdapter {

    private int tabsCount;

    private final FragmentManager mFragmentManager;

    private final int movieId;

    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    public MovieViewPagerAdapter(FragmentManager fm, int tabsCount, int movieId) {
        super(fm);
        this.tabsCount = tabsCount;
        this.mFragmentManager = fm;
        this.movieId = movieId;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TrailersFragment.createInstance(movieId);
            case 1:
                return ReviewsFragment.createInstance(movieId);
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabsCount;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    public int x;

    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}
