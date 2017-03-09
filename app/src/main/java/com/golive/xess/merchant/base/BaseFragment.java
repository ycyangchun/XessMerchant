package com.golive.xess.merchant.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.golive.xess.merchant.view.MainActivity;

/**
 * Created by YangChun .
 * on 2017/3/6.
 */

public abstract class BaseFragment extends Fragment {
    public MainActivity activity;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        activity = (MainActivity)getActivity();
        super.onActivityCreated(savedInstanceState);
    }
}
