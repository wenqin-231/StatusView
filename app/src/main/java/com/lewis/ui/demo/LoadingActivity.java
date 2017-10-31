package com.lewis.ui.demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lewis.widget.ui.Status;
import com.lewis.widget.ui.base.BaseStatusActivity;
import com.lewis.widget.ui.listener.OnRetryBtnClickListener;

/**
 * Created by Lewis on 2017/9/26.
 * Description:
 */

public class LoadingActivity extends BaseStatusActivity {

	private TextView mLoadText;
	private int mLoadNum;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_test);

		setTitle("Loading in Activity");

		mLoadText = (TextView) findViewById(R.id.loaded_text);
		mStatusView.setOnRetryBtnClickListener(new OnRetryBtnClickListener() {
			@Override
			public void onClick(View view) {
				onNormalLoadingClick(view);
			}
		});
	}

	public void onNormalLoadingClick(View view) {
		mStatusView.setStatus(Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(Status.NORMAL);
				mLoadNum++;
				mLoadText.setText("Normal load text success \n The number of times to load success: " + mLoadNum);
			}
		}, 2000);
	}

	public void onDialogLoadingClick(View view) {
		mStatusView.setStatus(Status.DIALOG_LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(Status.NORMAL);
				mLoadNum++;
				mLoadText.setText("Dialog load text success \nThe number of times to load success: " + mLoadNum);
			}
		}, 2000);
	}

	public void onErrorLoadingClick(View view) {
		mStatusView.setStatus(Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(Status.ERROR);
			}
		}, 2000);
	}

	public void onEmptyLoadingClick(View view) {
		Intent intent = new Intent(this, EmptyActivity.class);
		startActivity(intent);
	}
}
