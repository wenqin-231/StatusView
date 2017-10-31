package com.lewis.ui.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.lewis.widget.ui.Status;
import com.lewis.widget.ui.base.BaseStatusActivity;

/**
 * Created by Lewis on 2017/10/9.
 * Description: 演示Delay时间的Activity
 */

public class DelayActivity extends BaseStatusActivity {

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_delay);

		setTitle("DelayTime");
		TextView delayTips = (TextView) findViewById(R.id.delay_tips);
		delayTips.setText("Loading time mills is 100ms. \n Delay time mills is 600ms.");
	}

	public void OnNormalDelayClick(View view) {
		// StatusView contains 400ms delay time by default. Here use setDelayMillsForLoading to show
		// no delay loading.
		mStatusView.setDelayMillsForLoading(0);
		toLoading();
	}

	public void onDelayTimeClick(View view) {
		mStatusView.resetDefaultDelayTime();
		toLoading();
	}

	private void toLoading() {
		mStatusView.setStatus(Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(Status.NORMAL);
			}
		}, 100);
	}
}
