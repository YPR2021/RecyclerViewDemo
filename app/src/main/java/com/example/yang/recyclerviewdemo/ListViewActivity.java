package com.example.yang.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
		setContentView(R.layout.activity_recylerview);
		mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
		initData();
	}

	private void initData() {
		// 模拟数据 TODO:
		mDatas = new ArrayList<>();
		for (int i = 'A'; i < 'Z'; i++) {
			mDatas.add("" + (char) i);
		}
		LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(ListViewActivity.this);
		mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);//默认是垂直
		//设置item的排列方向,默认是垂直排列
		mRecyclerView.setLayoutManager(mLinearLayoutManager);
		//如果item大小一样,设置这个可以提高效率
		mRecyclerView.setHasFixedSize(true);
		//设置item分隔线
		mRecyclerView.addItemDecoration(new DividerItemDecoration(ListViewActivity.this,DividerItemDecoration.VERTICAL_LIST));
		//设置增加和删除item的动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		//给item设置监听
		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this,mRecyclerView,mItemClickListener));
		//设置adapter数据
		mRecyclerView.setAdapter(new MyListAdapter());
		//第二种给item监听的方法
//		RecyclerViewAdapter myListAdapter = new RecyclerViewAdapter(this,mDatas);
//		myListAdapter.setOnItemClickListener(mListener);
//		mRecyclerView.setAdapter(myListAdapter);
	}

	private RecyclerItemClickListener.OnItemClickListener mItemClickListener = new RecyclerItemClickListener.OnItemClickListener() {
		@Override
		public void onItemClick(View view, int position) {
			Toast.makeText(ListViewActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void onItemLongClick(View view, int position) {
			Toast.makeText(ListViewActivity.this, "长按了"+position, Toast.LENGTH_SHORT).show();
		}
	};

	private class MyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

		@Override
		public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
//			View view = View.inflate(parent.getContext(),R.layout.item,null);
			//一定要设置这个,不然数据不会居中
			view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			return new MyViewHolder(view);
		}

		@Override
		public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
			((MyViewHolder) holder).mText.setText(mDatas.get(position));
		}

		@Override
		public int getItemCount() {
			return mDatas.size();
		}

		class MyViewHolder extends RecyclerView.ViewHolder {
			TextView mText;

			public MyViewHolder(View itemView) {
				super(itemView);
				mText = (TextView) itemView.findViewById(R.id.tv_text);
			}
		}
	}

	private RecyclerViewAdapter.OnItemClickListener mListener = new RecyclerViewAdapter.OnItemClickListener() {
		@Override
		public void OnItemClick(View v, int position) {
			Toast.makeText(ListViewActivity.this, "点击"+position, Toast.LENGTH_SHORT).show();
		}

		@Override
		public void OnItemLongClick(View v, int position) {
			Toast.makeText(ListViewActivity.this, "长按"+position, Toast.LENGTH_SHORT).show();
//			return true;
		}
	};
}
