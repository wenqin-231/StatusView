package com.lewis.ui.demo;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lewis.ui.demo.adapter.LoadMoreAdapter;
import com.lewis.ui.demo.loadmore.BaseLoadMoreActivity;
import com.lewis.widget.ui.Status;

/**
 * Created by Lewis on 2017/10/10.
 * Description: refresh and loadMore Activity
 */

public class LoadMoreActivity extends BaseLoadMoreActivity {

	private LoadMoreAdapter mAdapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_more);

		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		setTitle("LoadMore");

		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		mAdapter = new LoadMoreAdapter(this);
		recyclerView.setAdapter(mAdapter);

		initData();
	}

	private void initData() {
		mStatusView.setStatus(Status.LOADING);
		for (int i = 0; i < 10; i++) {
			mAdapter.getData().add("Item " + i);
		}
		mAdapter.notifyDataSetChanged();
		mStatusView.setStatus(Status.NORMAL);
	}

	@Override
	public void onSpringLoadMore() {
		super.onSpringLoadMore();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
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

	@Override
	public void onSpringRefresh() {
		super.onSpringRefresh();
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
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
}
