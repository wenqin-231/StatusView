package com.lewis.widget.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.lewis.widget.ui.view.DefaultToolbar;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/10/31.
 * Description: A utils for StatusView to create rich effect.
 */

public class StatusManager {

    private static final int LINE_VIEW_HEIGHT = 4;
    @ColorInt
    private static final int LINE_VIEW_COLOR = 0XDEDEDE;

    private View mLineView = null; // a line below of Toolbar for Android version < 21

    private Activity mActivity;
    private Fragment mV4Fragment;
    private android.app.Fragment mFragment;
    private Toolbar mToolbar;
    private View mContentView;
    private StatusView mStatusView;
    private boolean isAddStatusView = true;
    private boolean isAddToolbar;
    private boolean isStatusViewBelowToolBar;

    private StatusManager(Builder builder) {
        this.mActivity = builder.mActivity;
        this.mToolbar = builder.mToolbar;
        this.mContentView = builder.mContentView;
        this.mStatusView = builder.mStatusView;
        this.isAddStatusView = builder.isAddStatusView;
        this.isAddToolbar = builder.isAddToolbar;
        this.isStatusViewBelowToolBar = builder.isStatusViewBelowToolBar;
        this.mFragment = builder.mFragment;
        this.mV4Fragment = builder.mV4Fragment;
    }

    public static Builder get(Activity activity) {
        return new Builder(activity);
    }

    public static Builder get(Fragment fragment) {
        return new Builder(fragment);
    }

    public static Builder get(android.app.Fragment fragment) {
        return new Builder(fragment);
    }


    public static class Builder {
        private Activity mActivity;
        private Fragment mV4Fragment;
        private android.app.Fragment mFragment;
        private Toolbar mToolbar;
        private View mContentView;
        private StatusView mStatusView;
        private boolean isAddStatusView;
        private boolean isAddToolbar;
        private boolean isStatusViewBelowToolBar;

        public Builder(Activity context) {
            mActivity = context;
        }

        public Builder(Fragment v4Fragment) {
            mV4Fragment = v4Fragment;
        }

        public Builder(android.app.Fragment fragment) {
            mFragment = fragment;
        }

        private StatusManager build() {
            return new StatusManager(this);
        }

        public Builder setToolbar(Toolbar toolbar) {
            mToolbar = toolbar;
            return this;
        }

        public Builder setContentView(View contentView) {
            mContentView = contentView;
            return this;
        }

        public Builder setStatusView(StatusView statusView) {
            mStatusView = statusView;
            return this;
        }

        public Builder isAddStatusView(boolean addStatusView) {
            isAddStatusView = addStatusView;
            return this;
        }

        public Builder isAddToolbar(boolean addToolbar) {
            isAddToolbar = addToolbar;
            return this;
        }

        public Builder isStatusViewBelowToolBar(boolean statusViewBelowToolBar) {
            isStatusViewBelowToolBar = statusViewBelowToolBar;
            return this;
        }

        public StatusManager launch() {
            return build().launch();
        }
    }


    public StatusManager launch() {

        Context context;
        ViewGroup parentView;

        if (mActivity != null) {
            context = mActivity;
            parentView = mActivity.getWindow().getDecorView().findViewById(android.R.id.content);
            if (mContentView == null) {
                mContentView = parentView.getChildAt(0);
            }
        } else {
            if (mContentView == null)
                throw new RuntimeException("You should setContent with a not null ViewGroup in Fragment");
            context = mV4Fragment != null ? mV4Fragment.getActivity() : mFragment.getActivity();
            parentView = (ViewGroup) mContentView.getParent();
        }

        parentView.removeViews(0, parentView.getChildCount() - 1);
        if (isAddStatusView) {
            if (mStatusView == null) {
                mStatusView = new StatusView(context);
            }

            parentView.addView(mStatusView);
        }

        if (isAddToolbar) {
            if (mToolbar == null) {
                mToolbar = new DefaultToolbar(context);
            }
            parentView.addView(mToolbar);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                mLineView = getLineView(context);
                parentView.addView(mLineView);
            }

            mContentView.post(new Runnable() {
                @Override
                public void run() {
                    float toolBarHeight = getToolbarHeight(mToolbar);
                    setMarginTop(mContentView, toolBarHeight);

                    if (isStatusViewBelowToolBar && mStatusView != null) {
                        setMarginTop(mStatusView, toolBarHeight);
                    }

                    if (mLineView != null) {
                        setMarginTop(mLineView, mToolbar.getMeasuredHeight());
                    }
                }
            });
        }
        return this;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    public StatusView getStatusView() {
        return mStatusView;
    }

    private void setMarginTop(View contentView, float toolbarHeight) {
        FrameLayout.LayoutParams lp = (FrameLayout.LayoutParams) contentView.getLayoutParams();
        lp.setMargins(lp.leftMargin, (int) toolbarHeight + lp.topMargin, lp.rightMargin, lp.bottomMargin);
        contentView.setLayoutParams(lp);
    }


    public static float getToolbarHeight(Toolbar toolbar) {
        float toolbarElevation;
        float toolbarTranslationZ = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            toolbarElevation = toolbar.getElevation();
            toolbarTranslationZ = toolbar.getTranslationZ();
        } else {
            // the height of lineView
            toolbarElevation = LINE_VIEW_HEIGHT;
        }
        return toolbar.getMeasuredHeight() + toolbarElevation +
                toolbarTranslationZ;
    }

    /**
     * @return A line view for toolbar before sdk version 21
     */
    public static View getLineView(Context context) {
        View lineView = new View(context);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                LINE_VIEW_HEIGHT);
        lineView.setBackgroundColor(LINE_VIEW_COLOR);
        lineView.setLayoutParams(lp);
        return lineView;
    }
}
