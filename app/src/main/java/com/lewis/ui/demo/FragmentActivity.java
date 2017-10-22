package com.lewis.ui.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.lewis.widget.ui.base.BaseStatusActivity;

/**
 * Created by Lewis on 2017/9/25.
 * Description:
 */

public class FragmentActivity extends BaseStatusActivity {

	private LoadingFragment loadingFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fragment);

		if (mStatusView == null) {
			Log.i("LogTag", "StatusView is not added");
		}

		loadingFragment = new LoadingFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.content_layout, loadingFragment, loadingFragment.getClass().getName());
		ft.commitAllowingStateLoss();
	}

	@Override
	protected boolean isAddStatusView() {
		return false;
	}

	public void onNormalLoadingClick(View view) {
		loadingFragment.onNormalLoadingClick(view);
	}

	public void onDialogLoadingClick(View view) {
		loadingFragment.onDialogLoadingClick(view);
	}

	public void onErrorLoadingClick(View view) {
		loadingFragment.onErrorLoadingClick(view);
	}

	public void onEmptyLoadingClick(View view) {
		loadingFragment.onEmptyLoadingClick(view);
	}

	@Override
	protected boolean isAddToolBar() {
		return false;
	}
}
