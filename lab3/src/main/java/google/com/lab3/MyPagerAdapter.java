package google.com.lab3;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sergey on 12.12.2016.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"Image loader", "UI elements", "Random animation"};

    MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ImageLoaderFragment();
            case 1:
                return new SuperAwesomeCardFragment();
            case 2:
                return new AnimationFragment();
            default:
                return null;
        }
    }
}
