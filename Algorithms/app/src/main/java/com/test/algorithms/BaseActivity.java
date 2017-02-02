package com.test.algorithms;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by eyablonskaya on 31-Jan-17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    public FragmentTransaction getFragmentTransaction() {
        return getSupportFragmentManager().beginTransaction();
    }
    public abstract int getContentHolderId();

    public void add(Fragment fragment, int container) {
        getFragmentTransaction()
                .add(container, fragment, fragment.getClass().getSimpleName())
                .commitAllowingStateLoss();
    }

    public void replaceInBackStack(Fragment fragment, int container, String tag) {
        getFragmentTransaction()
                .replace(container, fragment,
                        tag)
                .addToBackStack(tag)
                .commitAllowingStateLoss();
    }


    protected void openFragment(Fragment fragment, String fragmentTag) {
        if (fragment.isAdded()) {
            replaceInBackStack(fragment, getContentHolderId(), fragmentTag);
        } else {
            add(fragment, getContentHolderId());
        }
    }

}
