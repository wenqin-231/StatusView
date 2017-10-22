package com.lewis.widget.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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

public class BaseStatusActivity extends AppCompatActivity {

	protected StatusView mStatusView;
	protected Toolbar mToolbar;

	protected LinearLayout mToolbarLayout;
	protected ViewGroup parentView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		parentView = getWindow().getDecorView().findViewById(android.R.id.content);

		if (isAddStatusView())
			mStatusView = new StatusView(this);

		if (isAddToolBar()){
			mToolbar = onCreateToolbar();
			if (mToolbar == null) {
				mToolbar = new DefaultToolbar(this);
			}

			mToolbarLayout = new LinearLayout(this);
			LinearLayout.LayoutParams contentLayoutParams = new LinearLayout.LayoutParams(
					ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			mToolbarLayout.setLayoutParams(contentLayoutParams);
			mToolbarLayout.setOrientation(LinearLayout.VERTICAL);

			parentView.addView(mToolbarLayout);
			mToolbarLayout.addView(mToolbar);
			setSupportActionBar(mToolbar);

			mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					onBackPressed();
				}
			});
		}
	}

	@Override
	public void setContentView(@LayoutRes int layoutResID) {
		setContentView(getLayoutInflater().inflate(layoutResID, null));
	}

	@Override
	public void setContentView(View view) {
		setContentView(view, view.getLayoutParams());
	}

	@Override
	public void setContentView(View view, ViewGroup.LayoutParams params) {

		if (params == null) params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		View contentView = buildContentView(view);
		if (isAddToolBar()) {
			mToolbarLayout.addView(contentView, params);
		} else {
			parentView.addView(contentView, params);
		}

		if (isAddStatusView()) {
			parentView.addView(mStatusView);
		}
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus && isAddToolBar()) {
			int toolbarTranslationZ = 0;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				toolbarTranslationZ = (int) mToolbar.getTranslationZ();
			}
			if (mStatusView != null)
				mStatusView.setMarginTop(mToolbar.getHeight() +  toolbarTranslationZ);
		}
	}

	protected boolean isAddStatusView() {
		return true;
	}

	protected boolean isAddToolBar() {
		return true;
	}

	protected Toolbar onCreateToolbar() {
		return null;
	}

	protected void setTitle(String title) {
		if (mToolbar == null) return;
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(title);
		} else {
			mToolbar.setTitle(title);
		}
	}

	protected View buildContentView(View view) {
		return view;
	}
}
