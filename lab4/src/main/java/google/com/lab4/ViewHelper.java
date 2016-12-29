package google.com.lab4;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by Sergey on 18.12.2016.
 */

public class ViewHelper {

    public static void showFragmentLeftRight(FragmentManager fragmentManager, Fragment fragment, boolean addToBackStack, String backStackKey) {
        FragmentTransaction transaction =
                fragmentManager
                        .beginTransaction()
                        .setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_left, R.animator.slide_in_right, R.animator.slide_out_right)
                        .replace(R.id.container, fragment);
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
                        .add(R.id.container, fragment);
        if (addToBackStack)
            transaction.addToBackStack(backStackKey);
        transaction.commit();
    }

}

