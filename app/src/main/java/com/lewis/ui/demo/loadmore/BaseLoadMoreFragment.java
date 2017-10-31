package com.lewis.ui.demo.loadmore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.lewis.widget.ui.base.BaseStatusFragment;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

/**
 * Created by Lewis on 2017/10/10.
 * Description:
 */

public class BaseLoadMoreFragment extends BaseStatusFragment {

    protected SpringView mSpringView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (isAddSpringView()) {
            mSpringView = new SpringView(getActivity());

            mSpringView.setListener(new SpringView.OnFreshListener() {
                @Override
                public void onRefresh() {
                    onSpringViewRefresh();
                }

                @Override
                public void onLoadMore() {
                    onSpringViewLoadMore();
                }
            });
        }
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected View buildFragmentView(View view) {
        if (!isAddSpringView()) {
            return view;
        }
        ViewGroup parentView = (ViewGroup) view.getParent();
        if (parentView == null) throw new RuntimeException("the parent of view can not be null");
        parentView.removeView(view);
        mSpringView.setContentView(view);
        mSpringView.setHeader(new DefaultHeader(getActivity()));
        mSpringView.setFooter(new DefaultFooter(getActivity()));
        parentView.addView(mSpringView);
        return mSpringView;
    }

    protected boolean isAddSpringView() {
        return true;
    }

    protected void onSpringViewRefresh() {

    }

    protected void onSpringViewLoadMore() {

    }
}
