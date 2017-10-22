package com.lewis.ui.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.ui.demo.adapter.LoadMoreAdapter;
import com.lewis.ui.demo.loadmore.BaseLoadMoreFragment;
import com.lewis.widget.ui.view.StatusView;

/**
 * Created by Lewis on 2017/10/10.
 * Description:
 */

public class LoadMoreFragment extends BaseLoadMoreFragment{

	private LoadMoreAdapter mAdapter;
	private int index;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
							 @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_load_more, container, false);

		RecyclerView recyclerView = view.findViewById(R.id.recycler_view);

		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mAdapter = new LoadMoreAdapter(getActivity());
		recyclerView.setAdapter(mAdapter);
		return view;
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		initData();
	}

	private void initData() {
		mStatusView.setStatus(StatusView.Status.LOADING);
		for (int i = 0; i < 10; i++) {
			mAdapter.getData().add("Item " + i);
		}
		mAdapter.notifyDataSetChanged();
		mStatusView.setStatus(StatusView.Status.NORMAL);
	}

	@Override
	protected void onSpringViewRefresh() {
		super.onSpringViewRefresh();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				index = 0;
				refresh();
				mSpringView.onFinishFreshAndLoad();
			}
		}, 1000);
	}

	private void refresh() {
		mAdapter.getData().clear();
		for (int i = 0; i < 10; i++) {
			mAdapter.getData().add("Refreshed Item" + i);
		}
		mAdapter.notifyDataSetChanged();
	}

	@Override
	protected void onSpringViewLoadMore() {
		super.onSpringViewLoadMore();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
//				if (index < 2) {
//					loadMore();
//				} else {
//					mSpringView.setEnable(false);
//				}
				index ++;
				loadMore();
				mSpringView.onFinishFreshAndLoad();
			}
		}, 1000);
	}

	private void loadMore() {
		for (int i = 0; i < 10; i++) {
			mAdapter.getData().add("LoadMore Item" + i);
		}
		mAdapter.notifyDataSetChanged();
	}
}
