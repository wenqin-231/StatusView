package com.lewis.ui.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import com.lewis.ui.demo.loadmore.BaseLoadMoreActivity;

/**
 * Created by Lewis on 2017/10/10.
 * Description:
 */

public class LoadMoreFragmentActivity extends BaseLoadMoreActivity{


	private LoadMoreFragment mLoadMoreFragment;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_fragment);

		mLoadMoreFragment = new LoadMoreFragment();
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.content_layout, mLoadMoreFragment, mLoadMoreFragment.getClass().getName());
		ft.commitAllowingStateLoss();
	}

	@Override
	protected boolean isAddSpringView() {
		return false;
	}

	@Override
	protected boolean isAddStatusView() {
		return false;
	}
}
