package com.lewis.widget.ui.view;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewis.widget.ui.R;


/**
 * Created by Lewis on 2017/9/27.
 * Description:
 */

public class DefaultToolbar extends Toolbar {

	private TextView mCenterTitle;

	public DefaultToolbar(Context context) {
		this(context, null);
	}

	public DefaultToolbar(Context context, @Nullable AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {

		mCenterTitle = inflate(getContext(), R.layout.toolbar_center_title, this)
				.findViewById(R.id.center_text);

		LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		setLayoutParams(layoutParams);

		setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));


		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			setElevation(6f);
		}

		setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
		if (getContext() instanceof Activity) {
			final Activity activity = (Activity) getContext();
			setNavigationOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					activity.onBackPressed();
				}
			});
		}
	}

	public void setCenterTitle(String text) {
		mCenterTitle.setText(text);
		if (getContext() instanceof AppCompatActivity) {
			AppCompatActivity compatActivity = (AppCompatActivity) getContext();
			if (compatActivity.getSupportActionBar() != null) {
				compatActivity.getSupportActionBar().setTitle("");
			}
		}
		setTitle("");
	}

	public void setCenterTitle(@StringRes int resId) {
		setCenterTitle(getContext().getString(resId));
	}

	public void setCenterTitleTextColor(@ColorRes int resId) {
		mCenterTitle.setTextColor(ContextCompat.getColor(getContext(), resId));
	}
}
