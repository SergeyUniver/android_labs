package google.com.univer.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import google.com.univer.R;

/**
 * Created by Sergey on 19.10.2016.
 */

public class ViewHelper {

    public static void showFragmentLeftRight(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, String backStackKey) {
        FragmentTransaction transaction =
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right)
                        .replace(R.id.body_fragment, fragment);
        if (addToBackStack)
            transaction.addToBackStack(backStackKey);
        transaction.commitAllowingStateLoss();
    }

    public static void showFirstFragment(FragmentManager fragmentManager, Fragment fragment) {
        showFragmentWithoutAnimation(fragmentManager, fragment, false, null);
    }

    public static void showFragmentWithoutAnimation(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, String backStackKey) {
        FragmentTransaction transaction =
                fragmentManager
                        .beginTransaction()
                        .add(R.id.body_fragment, fragment);
        if (addToBackStack)
            transaction.addToBackStack(backStackKey);
        transaction.commit();
    }

}
