package com.lewis.widget.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorInt;
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

    private Context mContext;
    private Toolbar mToolbar;
    private ViewGroup mParentView;
    private View mContentView;
    private StatusView mStatusView;
    private boolean isAddStatusView;
    private boolean isAddToolBar;
    private boolean isStatusViewBelowToolBar;

    private StatusManager(Context context, Builder builder) {
        this.mContext = context;
        this.mToolbar = builder.mToolbar;
        this.mParentView = builder.mParentView;
        this.mContentView = builder.mContentView;
        this.mStatusView = builder.mStatusView;
        this.isAddStatusView = builder.isAddStatusView;
        this.isAddToolBar = builder.isAddToolBar;
        this.isStatusViewBelowToolBar = builder.isStatusViewBelowToolBar;
    }

    public static Builder get(Activity context) {
        return new Builder(context);
    }


    public static class Builder {
        private Activity mContext;
        private Toolbar mToolbar;
        private ViewGroup mParentView;
        private View mContentView;
        private StatusView mStatusView;
        private boolean isAddStatusView;
        private boolean isAddToolBar;
        private boolean isStatusViewBelowToolBar;

        public Builder(Activity context) {
            mContext = context;
        }

        private StatusManager build() {
            return new StatusManager(mContext, this);
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

        public Builder isAddToolBar(boolean addToolBar) {
            isAddToolBar = addToolBar;
            return this;
        }

        public Builder isStatusViewBelowToolBar(boolean statusViewBelowToolBar) {
            isStatusViewBelowToolBar = statusViewBelowToolBar;
            return this;
        }

        public Builder setParentView(ViewGroup parentView) {
            mParentView = parentView;
            return this;
        }

        public StatusManager launch() {
            return build().launch();
        }
    }


    public StatusManager launch() {

        if (mParentView == null)
            throw new RuntimeException("You should setParent with a not null ViewGroup");
        if (mContentView == null)
            throw new RuntimeException("You should setContent with a not null ViewGroup");

        mParentView.removeViews(0, mParentView.getChildCount() - 1);
        if (isAddStatusView) {
            if (mStatusView == null) {
                mStatusView = new StatusView(mContext);
            }

            mParentView.addView(mStatusView);
        }

        if (isAddToolBar) {
            if (mToolbar == null) {
                mToolbar = new DefaultToolbar(mContext);
            }
            mParentView.addView(mToolbar);

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                mLineView = getLineView(mContext);
                mParentView.addView(mLineView);
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
