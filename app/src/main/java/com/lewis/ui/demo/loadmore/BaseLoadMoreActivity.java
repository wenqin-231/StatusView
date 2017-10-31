package com.lewis.ui.demo.loadmore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.widget.ui.base.BaseStatusActivity;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Lewis on 2017/10/10.
 * Description:
 */

public class BaseLoadMoreActivity extends BaseStatusActivity {


	protected SpringView mSpringView;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mSpringView = new SpringView(this);

		mSpringView.setListener(new SpringView.OnFreshListener() {
			@Override
			public void onRefresh() {
				onSpringRefresh();
			}

			@Override
			public void onLoadMore() {
				onSpringLoadMore();
			}
		});
	}

	public void onSpringRefresh() {

	}

	public void onSpringLoadMore() {

	}

	@Override
	protected View buildContentView(View view) {
		if (!isAddSpringView()) return view;

		ViewGroup parentView = getWindow().getDecorView().findViewById(android.R.id.content);
		parentView.removeAllViews();
		ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		view.setLayoutParams(lp);

		mSpringView.setContentView(view);
		mSpringView.setHeader(new DefaultHeader(this));
		mSpringView.setFooter(new DefaultFooter(this));
		parentView.addView(mSpringView);

		return mSpringView;
	}

	protected boolean isAddSpringView() {
		return true;
	}
}
