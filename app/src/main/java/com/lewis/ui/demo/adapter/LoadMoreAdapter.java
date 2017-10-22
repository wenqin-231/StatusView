package com.lewis.ui.demo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lewis.ui.demo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lewis on 2017/10/10.
 * Description: LoadMore
 */

public class LoadMoreAdapter extends RecyclerView.Adapter<LoadMoreAdapter.ItemVh>{

	private LayoutInflater mInflater;
	private Context mContext;
	private List<String> mData;

	public LoadMoreAdapter(Context context) {
		mContext = context;
		mData = new ArrayList<>();
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public ItemVh onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ItemVh(mInflater.inflate(R.layout.adapter_item, parent, false));
	}

	@Override
	public void onBindViewHolder(ItemVh holder, int position) {
		holder.mTextView.setText(mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	class ItemVh extends RecyclerView.ViewHolder {

		private TextView mTextView;

		public ItemVh(View itemView) {
			super(itemView);
			mTextView = itemView.findViewById(R.id.item_text);
		}
	}

	public List<String> getData() {
		return mData;
	}
}
