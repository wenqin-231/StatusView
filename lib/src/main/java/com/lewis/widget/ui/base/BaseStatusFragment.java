package com.lewis.widget.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lewis.widget.ui.ToolBarUtils;
import com.lewis.widget.ui.view.DefaultToolbar;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/25.
 * Description: You can extend this Fragment or copy the code in your BaseFragment to use StatusView easily~
 */

public class BaseStatusFragment extends Fragment{

	protected StatusView mStatusView;
	protected Toolbar mToolbar;

	private LinearLayout mContentView; // the view is used if add toolbar

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		ViewGroup parentView = (ViewGroup) view.getParent();
		View fragmentView = buildFragmentView(view);

		if (isAddToolBar()) { // add a contentView of LinearLayout in Fragment's parent view to contain toolbar
			mToolbar = onCreateToolbar();
			if (mToolbar == null) {
				mToolbar = new DefaultToolbar(getActivity());
			}

			mContentView = new LinearLayout(getActivity());
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			mContentView.setLayoutParams(lp);
			mContentView.setOrientation(LinearLayout.VERTICAL);

			parentView.removeAllViews();
			parentView.addView(mContentView);
			mContentView.addView(mToolbar);
			// add a line view of toolbar if the sdk code < 21
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
				mContentView.addView(ToolBarUtils.getLineView(getContext()));
			}
			mContentView.addView(fragmentView);

			mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					getActivity().onBackPressed();
				}
			});
		}

		if (isAddStatusView()) {
			mStatusView = StatusView.initInFragment(getActivity(), parentView);

			if (isAddToolBar()) {
				mContentView.post(new Runnable() {
					@Override
					public void run() {
						if (mStatusView != null)
							mStatusView.setMarginTop((int) ToolBarUtils.getToolbarHeight(mToolbar));
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
