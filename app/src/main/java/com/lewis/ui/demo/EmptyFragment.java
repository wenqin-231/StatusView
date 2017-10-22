package com.lewis.ui.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.widget.ui.base.BaseStatusFragment;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/28.
 * Description:Empty View in Fragment
 */

public class EmptyFragment extends BaseStatusFragment{

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.activity_fragment, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mStatusView.setStatus(StatusView.Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(StatusView.Status.EMPTY);
			}
		}, 2000);

		if (getDefaultToolBar() != null) {
			getDefaultToolBar().setCenterTitle("Fragment EmptyView");
		}
	}

	@Override
	protected boolean isAddToolBar() {
		return true;
	}
}
