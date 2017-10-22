package com.lewis.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.lewis.widget.ui.base.BaseStatusActivity;

public class DemoMainActivity extends BaseStatusActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_demo_main);
	}

	@Override
	protected boolean isAddToolBar() {
		return false;
	}

	public void onActivityLoadingClick(View view) {
		Intent intent = new Intent(this, LoadingActivity.class);
		startActivity(intent);
	}

	public void onFragmentLoadingClick(View view) {
		Intent intent = new Intent(this, FragmentActivity.class);
		startActivity(intent);
	}

	public void onNormalActivityClick(View view) {
		Intent intent = new Intent(this, NormalActivity.class);
		startActivity(intent);
	}

	public void onNavigationActivityClick(View view) {
		Intent intent = new Intent(this, NavigationActivity.class);
		startActivity(intent);
	}

	public void onDelayActivityClick(View view) {
		Intent intent = new Intent(this, DelayActivity.class);
		startActivity(intent);
	}

	public void onLoadMoreActivityClick(View view) {
		Intent intent = new Intent(this, LoadMoreActivity.class);
		startActivity(intent);
	}

	public void onLoadMoreFragmentClick(View view) {
		Intent intent = new Intent(this, LoadMoreFragmentActivity.class);
		startActivity(intent);
	}
}
