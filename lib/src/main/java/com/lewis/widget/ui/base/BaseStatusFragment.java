package com.lewis.widget.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

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
        ViewGroup parentView = (ViewGroup) view.getParent();
        View fragmentView = buildFragmentView(view);

        StatusManager statusManager = StatusManager.get(getActivity())
                .setParentView(parentView)
                .setContentView(fragmentView)
                .setToolbar(onCreateToolbar())
                .isAddStatusView(isAddStatusView())
                .isAddToolBar(isAddToolBar())
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
