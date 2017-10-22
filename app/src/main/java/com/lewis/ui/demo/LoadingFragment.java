package com.lewis.ui.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewis.widget.ui.base.BaseStatusFragment;
import com.lewis.widget.ui.listener.OnRetryBtnClickListener;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/25.
 * Description:
 */

public class LoadingFragment extends BaseStatusFragment {


	private TextView mLoadText;
	private int mLoadNum;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.load_test, container, false);
		mLoadText = view.findViewById(R.id.loaded_text);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mStatusView.setOnRetryBtnClickListener(new OnRetryBtnClickListener() {
			@Override
			public void onClick(View view) {
				onNormalLoadingClick(view);
			}
		});

		if (getDefaultToolBar() != null) {
			getDefaultToolBar().setCenterTitle("Fragment Loading");
		}
	}

	public void onNormalLoadingClick(View view) {
		mStatusView.setStatus(StatusView.Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(StatusView.Status.NORMAL);
				mLoadNum ++;
				mLoadText.setText("Normal load text success \n The number of times to load success: " + mLoadNum);
			}
		}, 2000);
	}

	public void onDialogLoadingClick(View view) {
		mStatusView.setStatus(StatusView.Status.DIALOG_LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(StatusView.Status.NORMAL);
				mLoadNum ++;
				mLoadText.setText("Dialog load text success \nThe number of times to load success: " + mLoadNum);
			}
		}, 2000);
	}

	public void onErrorLoadingClick(View view) {
		mStatusView.setStatus(StatusView.Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(StatusView.Status.ERROR);
			}
		}, 2000);
	}

	public void onEmptyLoadingClick(View view) {
		EmptyFragment emptyFragment = new EmptyFragment();
		getFragmentManager().beginTransaction()
				.replace(R.id.content_layout, emptyFragment)
				.addToBackStack(null)
				.commitAllowingStateLoss();
	}

	@Override
	protected boolean isAddToolBar() {
		return true;
	}
}
