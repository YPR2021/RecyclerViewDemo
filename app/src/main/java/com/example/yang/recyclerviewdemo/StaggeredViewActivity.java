package com.example.yang.recyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ypr on 2016/6/25 15:29.
 * 描述:
 * TODO:
 */
public class StaggeredViewActivity extends Activity {

	private RecyclerView mRecyclerView;
	private List<String> mDatas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recylerview);
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
		initData();
	}

	private void initData() {
		mDatas = new ArrayList<>();
		for (int i = 'A'; i < 'z'; i++) {
			mDatas.add("" + (char) i);
		}
		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL));
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {

			}

			@Override
			public void onItemLongClick(View view, int position) {

			}
		}));

		mRecyclerView.setAdapter(new StaggeredAdaoter());
		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(StaggeredViewActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(StaggeredViewActivity.this, "长按了" + position, Toast.LENGTH_SHORT).show();
			}
		}));
	}

	private class StaggeredAdaoter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
		private List<Integer> mHeights;

		public StaggeredAdaoter() {
			mHeights = new ArrayList<>();
			for (int i = 0; i < mDatas.size(); i++) {
				mHeights.add((int) (100 + Math.random() * 300));
			}
		}

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

			View view = LayoutInflater.from(StaggeredViewActivity.this).inflate(R.layout.item, null);
//				view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			return new ViewHolder(view);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			//模拟瀑布流
			ViewGroup.LayoutParams lp = ((ViewHolder) holder).tv.getLayoutParams();
			lp.height = mHeights.get(position);
			((ViewHolder) holder).tv.setLayoutParams(lp);

			((ViewHolder) holder).tv.setText(mDatas.get(position));
		}

		@Override
		public int getItemCount() {
			return mDatas.size();
		}

		class ViewHolder extends RecyclerView.ViewHolder {
			private TextView tv;

			public ViewHolder(View itemView) {
				super(itemView);
				tv = (TextView) itemView.findViewById(R.id.tv_text);
			}
		}
	}
}
