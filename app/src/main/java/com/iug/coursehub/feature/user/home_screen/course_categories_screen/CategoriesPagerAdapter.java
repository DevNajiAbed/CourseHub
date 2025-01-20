package com.iug.coursehub.feature.user.home_screen.course_categories_screen;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.iug.coursehub.R;
import com.iug.coursehub.feature.user.home_screen.course_categories_screen.course_list_screen.CourseListFragment;

import java.util.ArrayList;
import java.util.List;

public class CategoriesPagerAdapter extends FragmentPagerAdapter {

    private final String[] categories;
    private final List<Fragment> fragments;
    private final List<String> titles;

    public CategoriesPagerAdapter(FragmentActivity activity, FragmentManager fm) {
        super(fm);
        categories = activity.getResources().getStringArray(R.array.course_categories);

        fragments = new ArrayList<>();
        titles = new ArrayList<>();

        for (int i = 1; i < categories.length; i++) {
            fragments.add(CourseListFragment.newInstance(categories[i]));
            titles.add(categories[i]);
        }
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
