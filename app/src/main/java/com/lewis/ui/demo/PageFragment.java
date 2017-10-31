package com.lewis.ui.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lewis.widget.ui.Status;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/9/28.
 * Description:
 */

public class PageFragment extends Fragment{

	private static final String KEY_PAGE_INDEX = "key_page_index";
	private int mPageIndex;

	private TextView mCenterText;
	private RelativeLayout mContentLayout;
	private StatusView mStatusView;

	public static PageFragment newInstance(int pageIndex) {

		Bundle args = new Bundle();
		args.putInt(KEY_PAGE_INDEX, pageIndex);
		PageFragment fragment = new PageFragment();
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mPageIndex = getArguments().getInt(KEY_PAGE_INDEX);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_page, container, false);
		mCenterText = view.findViewById(R.id.page_text);
		mContentLayout = view.findViewById(R.id.content_layout);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		mStatusView = StatusView.initInFragment(getActivity(), mContentLayout);

		switch (mPageIndex) {
			case 0:
				mCenterText.setText("HOME");
				break;
			case 1:
				mCenterText.setText("MESSAGE");
				break;
			case 2:
				mCenterText.setText("MINE");
				break;
		}

		mStatusView.setStatus(Status.LOADING);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				mStatusView.setStatus(Status.NORMAL);
			}
		}, 2000);
	}
}
