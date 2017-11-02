package com.lewis.widget.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.lewis.widget.ui.StatusManager;
import com.lewis.widget.ui.view.DefaultToolbar;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/25.
 * Description: You can extend this Fragment or copy the code in your BaseFragment to use StatusView easily~
 */

public class BaseStatusFragment extends Fragment {

    protected StatusView mStatusView;
    protected Toolbar mToolbar;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initStatusView(view);
    }

    private void initStatusView(View view) {
        StatusManager statusManager = StatusManager
                .get(this) // should be this and not set getActivity in it.
                .setContentView(buildFragmentView(view)) // set your ContentView and it is usually the view that created in OnCreateView
                .setToolbar(onCreateToolbar()) // set your own Toolbar and if you set isAddToolbar to be true, there will be set a DefaultToolbar in it.
                .isAddStatusView(isAddStatusView()) // set true to add a StatusView in your view and your set false to avoid loading the unnecessary setting.
                .isAddToolbar(isAddToolBar()) // set true to addToolbar and if your set false the setToolbar will be invalid.
                .launch();

        mToolbar = statusManager.getToolbar();
        mStatusView = statusManager.getStatusView();
    }

    protected boolean isAddStatusView() {
        return true;
    }

    protected boolean isAddToolBar() {
        return false;
    }

    protected Toolbar onCreateToolbar() {
        return null;
    }

    protected DefaultToolbar getDefaultToolBar() {
        if (mToolbar instanceof DefaultToolbar) {
            return (DefaultToolbar) mToolbar;
        }
        return null;
    }

    protected View buildFragmentView(View view) {
        return view;
    }
}
