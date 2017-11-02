package com.lewis.widget.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.widget.ui.StatusManager;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/25.
 * Description: you can extends this Activity or set the same code in your BaseActivity to use StatusView easily~
 */

public class BaseStatusActivity extends AppCompatActivity {

	protected StatusView mStatusView;
	protected Toolbar mToolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		initStatusView();
	}

	private void initStatusView() {

		ViewGroup parentView = getWindow().getDecorView().findViewById(android.R.id.content);
		View contentView = buildContentView(parentView.getChildAt(0));

		StatusManager statusManager = StatusManager.get(this)
				.setContentView(contentView)
				.isAddToolbar(isAddToolBar())
				.isAddStatusView(isAddStatusView())
				.setToolbar(onCreateToolbar())
				.launch();

		mToolbar = statusManager.getToolbar();
		mStatusView = statusManager.getStatusView();
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
		if (mToolbar == null || !isAddToolBar())
			throw new RuntimeException("You can not set a title with a null toolbar in this Activity");
		if (getSupportActionBar() != null) {
			getSupportActionBar().setTitle(title);
		} else {
			mToolbar.setTitle(title);
		}
	}

	/**
	 * Set your custom ContentView by using default ContentView.
	 * You can see the usage in BaseLoadMoreActivity in my demo.
	 */
	protected View buildContentView(View view) {
		return view;
	}
}
