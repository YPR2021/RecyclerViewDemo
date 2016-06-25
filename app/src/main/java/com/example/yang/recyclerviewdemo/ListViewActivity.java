package com.example.yang.recyclerviewdemo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ypr on 2016/6/25 15:27.
 * 描述:
 * TODO:
 */
public class ListViewActivity extends Activity {

	private RecyclerView mRecyclerView;
	private List<String> mDatas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
		initData();
	}

	private void initData() {
		// 模拟数据 TODO:
		mDatas = new ArrayList<>();
		for (int i = 'A'; i < 'Z'; i++) {
			mDatas.add("" + (char) i);
		}

		//设置item的排列方向,默认是垂直排列
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
		//如果item大小一样,设置这个可以提高效率
		mRecyclerView.setHasFixedSize(true);
		//设置增加和删除item的动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		//设置item分隔线
		mRecyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
		//设置adapter数据
		mRecyclerView.setAdapter(new MyListAdapter());
	}

	private class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

			MyViewHolder holder = new MyViewHolder(LayoutInflater.from(ListViewActivity.this).inflate(R.layout.item_list, parent, false));
			return holder;
		}

		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			holder.mText.setText(mDatas.get(position));
		}

		@Override
		public int getItemCount() {
			return mDatas.size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder {
			TextView mText;

			public MyViewHolder(View itemView) {
				super(itemView);
				mText = (TextView) itemView.findViewById(R.id.text);
			}
		}
	}


}
