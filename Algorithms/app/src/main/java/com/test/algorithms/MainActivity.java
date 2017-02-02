package com.test.algorithms;

import android.os.Bundle;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //openFragment(SortFragment.get(), "SORT_FRAG");
        openFragment(RecursionFragment.get(), "RECURSION_TAG");
    }


    @Override
    public int getContentHolderId() {
        return R.id.container;
    }
}
