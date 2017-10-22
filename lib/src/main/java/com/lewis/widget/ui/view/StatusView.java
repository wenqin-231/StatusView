package com.lewis.widget.ui.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AttrRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lewis.widget.ui.R;
import com.lewis.widget.ui.listener.OnRetryBtnClickListener;


/**
 * Created by Lewis on 2017/9/25.
 * Description: Display a view of different status that includes Loading, dialogLoading, empty, error,
 * normal and custom etc.
 */

public class StatusView extends FrameLayout {

	public enum Status {
		LOADING,
		DIALOG_LOADING,
		EMPTY,
		ERROR,
		NORMAL,
		OTHER
	}

	private static final int DEFAULT_DEALAY_TIME = 600;

	private Status mCurrentStatus = Status.NORMAL;

	private View mLoadingView, mDialogLoadingView, mEmptyView, mErrorView, mOtherView;

	private TextView mDialogText;
	private RelativeLayout mDialogParentLayout;
	private LinearLayout mDialogLayout;
	private ProgressBar mDialogProgressBar;

	private LinearLayout mLoadingLayout;
	private ProgressBar mLoadingProgress;
	private TextView mLoadingText;

	private LinearLayout mEmptyLayout;
	private ImageView mEmptyIcon;
	private TextView mEmptyTitle, mEmptySubheading;

	private LinearLayout mErrorLayout;
	private ImageView mErrorIcon;
	private TextView mErrorTitle, mErrorSubheading;
	private Button mErrorBtn;

	private OnRetryBtnClickListener mOnRetryBtnClickListener;

	private int mMarginTop, mMarginBottom, mMarginLeft, mMarginRight;

	private long mStartTime, mLoadingTime;

	private long mDelayMills = DEFAULT_DEALAY_TIME;

	private Handler mDelayHandler = new Handler(Looper.getMainLooper());

	public StatusView(@NonNull Context context) {
		this(context, null);
	}

	public StatusView(@NonNull Context context, @Nullable AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public StatusView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	public static StatusView initInFragment(Context context, View view) {
		return initInFragment(context, (ViewGroup) view.getParent());
	}

	public static StatusView initInFragment(Context context, ViewGroup parentView) {
		if (context == null)
			throw new RuntimeException("you can not init StatusView with null context");

		if (parentView == null)
			throw new RuntimeException("Can not get the parent of Fragment's view");

		StatusView statusView = new StatusView(context);
		parentView.addView(statusView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		return statusView;
	}

	public static StatusView initInActivity(Activity context) {
		if (context == null)
			throw new RuntimeException("you can not init StatusView with null context");

		ViewGroup contentView = context.getWindow().getDecorView().findViewById(android.R.id.content);
		StatusView statusView = new StatusView(context);
		contentView.addView(statusView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		return statusView;
	}


	private void init(Context context, AttributeSet attrs) {

		mLoadingView = inflate(context, R.layout.view_loading, null);
		mDialogLoadingView = inflate(context, R.layout.view_dialog_loading, null);
		mEmptyView = inflate(context, R.layout.view_empty, null);
		mErrorView = inflate(context, R.layout.view_error, null);

		mLoadingLayout = mLoadingView.findViewById(R.id.dialog_loading_layout);
		mLoadingText = mLoadingView.findViewById(R.id.dialog_loading_text);
		mLoadingProgress = mLoadingView.findViewById(R.id.dialog_loading_progress);

		mDialogParentLayout = mDialogLoadingView.findViewById(R.id.dialog_loading_content_layout);
		mDialogLayout = mDialogLoadingView.findViewById(R.id.dialog_loading_layout);
		mDialogProgressBar = mDialogLoadingView.findViewById(R.id.dialog_loading_progress);
		mDialogText = mDialogLoadingView.findViewById(R.id.dialog_loading_text);

		mEmptyLayout = mEmptyView.findViewById(R.id.empty_content);
		mEmptyIcon = mEmptyView.findViewById(R.id.empty_icon);
		mEmptySubheading = mEmptyView.findViewById(R.id.empty_text_subheading);
		mEmptyTitle = mEmptyView.findViewById(R.id.empty_text_title);

		mErrorLayout = mErrorView.findViewById(R.id.error_layout);
		mErrorIcon = mErrorView.findViewById(R.id.error_icon);
		mErrorBtn = mErrorView.findViewById(R.id.error_btn);
		mErrorTitle = mErrorView.findViewById(R.id.error_text_title);
		mErrorSubheading = mErrorView.findViewById(R.id.error_text_subheading);

		mErrorBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				if (mOnRetryBtnClickListener != null)
					mOnRetryBtnClickListener.onClick(view);
			}
		});

	}

	private void setProgressBarColor(ProgressBar dialogProgressBar, @ColorRes int resId) {
		if (dialogProgressBar.isIndeterminate()) {
			dialogProgressBar.getIndeterminateDrawable().setColorFilter(ContextCompat.getColor(
					getContext(), resId), PorterDuff.Mode.SRC_IN);
		} else {
			dialogProgressBar.getProgressDrawable().setColorFilter(ContextCompat.getColor(
					getContext(), resId), PorterDuff.Mode.SRC_IN);
		}
	}

	public void setStatus(Status status) {
		mCurrentStatus = status;
		mLoadingTime = 0;
		setDelayCall(status, new OnDelayCallBackListener() {
			@Override
			public void call(Status status) {
				// this call will setup the delayMills by the status
				removeAllViews();
				addStatusView(status);
			}
		});

	}

	private void addStatusView(Status status) {
		switch (status) {
			case LOADING:
				addView(mLoadingView);
				break;
			case DIALOG_LOADING:
				addView(mDialogLoadingView);
				break;
			case EMPTY:
				addView(mEmptyView);
				break;
			case ERROR:
				addView(mErrorView);
				break;
			case NORMAL:
				break;
			case OTHER:
				if (mOtherView == null) throw new RuntimeException("You can not set other view with a null custom view");
				addView(mOtherView);
				break;
		}
	}

	private boolean isLoadingStatus(Status status) {
		return status == Status.LOADING || status == Status.DIALOG_LOADING;
	}

	public Status getCurrentStatus() {
		return mCurrentStatus;
	}

	public void setLoadingView(View loadingView) {
		if (loadingView == null)
			throw new RuntimeException("loadingView can not be null in setLoadingView");
		mLoadingView = loadingView;
		mLoadingView.setClickable(true);
	}

	public void setDialogLoadingView(View dialogLoadingView) {
		if (dialogLoadingView == null)
			throw new RuntimeException("dialogLoadingView can not be null in setDialogLoadingView");
		mDialogLoadingView = dialogLoadingView;
		mDialogLoadingView.setClickable(true);
	}

	public void setEmptyView(View emptyView) {
		if (emptyView == null)
			throw new RuntimeException("emptyView can not be null in setEmptyView");
		mEmptyView = emptyView;
		mEmptyView.setClickable(true);
	}

	public void setErrorView(View errorView) {
		if (errorView == null)
			throw new RuntimeException("errorView can not be null in setErrorView");
		mErrorView = errorView;
		mErrorView.setClickable(true);
	}

	public void setOtherView(View otherView) {
		if (otherView == null)
			throw new RuntimeException("your custom view can not be null");
		mOtherView = otherView;
		mOtherView.setClickable(true);
	}

	public void addCustomView(View customView) {
		setOtherView(customView);
		setStatus(Status.OTHER);
	}

	public void setLoadingViewBackgroundColor(@ColorRes int resId) {
		if (mLoadingLayout != null)
			mDialogLayout.setBackgroundColor(ContextCompat.getColor(getContext(), resId));
	}

	public void setDialogLoadingViewBackgroundColor(@ColorRes int resId) {
		if (mDialogParentLayout != null)
			mDialogLayout.setBackgroundColor(ContextCompat.getColor(getContext(), resId));
	}

	public void setLoadingDialogBackground(@DrawableRes int resId) {
		if (mDialogLayout != null)
			mDialogLayout.setBackground(ContextCompat.getDrawable(getContext(), resId));
	}

	public void setEmptyViewBackgroundColor(@ColorRes int resId) {
		if (mEmptyLayout != null)
			mEmptyLayout.setBackgroundColor(ContextCompat.getColor(getContext(), resId));
	}

	public void setErrorViewBackgroundColor(@ColorRes int resId) {
		if (mErrorLayout != null)
			mErrorLayout.setBackgroundColor(ContextCompat.getColor(getContext(), resId));
	}

	public void setOnRetryBtnClickListener(OnRetryBtnClickListener onRetryBtnClickListener) {
		mOnRetryBtnClickListener = onRetryBtnClickListener;
	}

	public void setDialogLoadingText(@StringRes int resId) {
		setDialogLoadingText(getContext().getString(resId));
	}

	public void setDialogLoadingText(String text) {
		if (mDialogText != null)
			mDialogText.setText(text);
	}

	public void setDialogProgressBarColor(@ColorRes int resId) {
		if (mDialogProgressBar != null)
			setProgressBarColor(mDialogProgressBar, resId);

	}

	public void setLoadingText(String text) {
		if (mLoadingText != null)
			mLoadingText.setText(text);
	}

	public void setLoadingText(@StringRes int resId) {
		setLoadingText(getContext().getString(resId));
	}

	public void setProgressBarColor(@ColorRes int resId) {
		if (mLoadingProgress != null)
			setProgressBarColor(mLoadingProgress, resId);
	}

	public void setEmptyViewIcon(int resId) {
		if (mEmptyIcon != null)
			mEmptyIcon.setImageResource(resId);
	}

	public void setErrorViewIcon(int resId) {
		if (mErrorIcon != null)
			mErrorIcon.setImageResource(resId);
	}

	public void setRetryBtnBackground(@DrawableRes int resId) {
		if (mErrorBtn != null)
			mErrorBtn.setBackgroundResource(resId);
	}

	public void setDialogText(TextView dialogText) {
		mDialogText = dialogText;
	}

	public void setEmptyTitle(String emptyTitle) {
		if (mEmptyTitle != null)
			mEmptyTitle.setText(emptyTitle);
	}

	public void setEmptySubheading(String emptySubheading) {
		if (mEmptySubheading != null)
			mEmptySubheading.setText(emptySubheading);
	}

	public void setErrorTitle(String errorTitle) {
		if (mEmptyTitle != null)
			mErrorTitle.setText(errorTitle);
	}

	public void setErrorSubheading(String errorSubheading) {
		if (mErrorSubheading != null)
			mErrorSubheading.setText(errorSubheading);
	}

	public void setMargin(int left, int top, int right, int bottom) {
		LayoutParams layoutParams = (LayoutParams) getLayoutParams();
		if (layoutParams == null) throw new RuntimeException("StatusView is not added in a layout");
		layoutParams.setMargins(left, top, right, bottom);
		setLayoutParams(layoutParams);

		mMarginLeft = left;
		mMarginRight = right;
		mMarginTop = top;
		mMarginBottom = bottom;
	}

	public void setMarginTop(int top) {
		setMargin(mMarginLeft, top, mMarginRight, mMarginBottom);
	}

	public void setMarginBottom(int bottom) {
		setMargin(mMarginLeft, mMarginTop, mMarginRight, bottom);
	}

	public void setDelayMillsForLoading(long timeMills) {
		mDelayMills = timeMills;
	}

	public void resetDefaultDelayTime() {
		setDelayMillsForLoading(DEFAULT_DEALAY_TIME);
	}

	private void setDelayCall(final Status status, final OnDelayCallBackListener listener) {
		if (isLoadingStatus(status)) {
			// when is Loading status, init the start time
			mStartTime = System.currentTimeMillis();
		} else {
			// when is not loading status, init the loading time
			mLoadingTime = System.currentTimeMillis() - mStartTime;
		}

		mDelayHandler.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (listener != null) listener.call(status);
			}
		}, !isLoadingStatus(status) && mLoadingTime < mDelayMills ? mDelayMills : 0);
	}

	private interface OnDelayCallBackListener {
		void call(Status status);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		mDelayHandler.removeCallbacksAndMessages(null);
	}
}
