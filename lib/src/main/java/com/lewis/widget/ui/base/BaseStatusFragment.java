package com.lewis.widget.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lewis.widget.ui.view.DefaultToolbar;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/25.
 * Description:
 */

public class BaseStatusFragment extends Fragment{

	protected StatusView mStatusView;
	protected Toolbar mToolbar;

	protected LinearLayout mContentView;
	private ViewGroup mParentView;

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		mParentView = (ViewGroup) view.getParent();
		View fragmentView = buildFragmentView(view);

		if (isAddToolBar()) {
			mToolbar = onCreateToolbar();
			if (mToolbar == null) {
				mToolbar = new DefaultToolbar(getActivity());
			}

			mContentView = new LinearLayout(getActivity());
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			mContentView.setLayoutParams(lp);
			mContentView.setOrientation(LinearLayout.VERTICAL);

			mParentView.removeAllViews();
			mParentView.addView(mContentView);
			mContentView.addView(mToolbar);
			mContentView.addView(fragmentView);

			mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					getActivity().onBackPressed();
				}
			});
		}

		if (isAddStatusView()) {
			mStatusView = StatusView.initInFragment(getActivity(), mParentView);

			if (isAddToolBar()) {
				mContentView.post(new Runnable() {
					@Override
					public void run() {
						int toolbarTranslationZ = 0;
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
							toolbarTranslationZ = (int) mToolbar.getTranslationZ();
						}
						mStatusView.setMarginTop(toolbarTranslationZ + mToolbar.getHeight());
					}
				});
			}
		}
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

	protected DefaultToolbar getDefaultToolBar () {
		if (mToolbar instanceof DefaultToolbar) {
			return (DefaultToolbar) mToolbar;
		}
		return null;
	}

	protected View buildFragmentView(View view) {
		return view;
	}
}
