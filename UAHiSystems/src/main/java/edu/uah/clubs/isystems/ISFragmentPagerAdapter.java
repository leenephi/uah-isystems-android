package edu.uah.clubs.isystems;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by smithsonln on 12/16/13.
 */
public class ISFragmentPagerAdapter extends FragmentPagerAdapter {

    public ISFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
//                return new ISDegreeFragment();
            case 1:
//                return new ISCoursesFragment();
            case 2:
//                return new ISFacultyFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
