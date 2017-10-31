package com.lewis.ui.demo;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.lewis.widget.ui.Status;
import com.lewis.widget.ui.listener.OnRetryBtnClickListener;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/27.
 * Description:
 */

public class NormalActivity extends AppCompatActivity{

	private TextView mLoadText;
	private Toolbar mToolbar;

	private int mLoadNum;
	private StatusView mStatusView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_normal);
		mStatusView = StatusView.initInActivity(this);

		mLoadText = (TextView) findViewById(R.id.loaded_text);
		mToolbar = (Toolbar) findViewById(R.id.toolbar);

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			mToolbar.setTranslationZ(8f);
		}
		mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
		mToolbar.setBackgroundColor(ContextCompat.getColor(this, android.R.color.white));

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
				mLoadNum ++;
				mLoadText.setText("Normal load text success \n The number of times to load success: " + mLoadNum);
			}
		}, 2000);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);

		if (hasFocus) {
			int toolbarTranslationZ = 0;
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				toolbarTranslationZ = (int) mToolbar.getTranslationZ();
			}
			mStatusView.setMarginTop(mToolbar.getHeight() + toolbarTranslationZ);
		}
	}

	public void onDialogLoadingClick(View view) {
		mStatusView.setStatus(Status.DIALOG_LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(Status.NORMAL);
				mLoadNum ++;
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
		mStatusView.setStatus(Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(Status.EMPTY);
			}
		}, 2000);
	}
}
