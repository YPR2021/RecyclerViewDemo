package com.example.yang.recyclerviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
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
 * Created by ypr on 2016/6/25 15:28.
 * 描述:
 * TODO:
 */
public class GridViewActivity extends Activity {

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
		mDatas = new ArrayList<>();
		for (int i = 'A'; i < 'Z'; i++) {
			mDatas.add("" + (char) i);
		}

		//设置布局
		GridLayoutManager manager = new GridLayoutManager(this, 3);
		mRecyclerView.setLayoutManager(manager);
		//效率布局
		mRecyclerView.setHasFixedSize(true);
		//设置添加删除动画
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		//设置item分隔线
		mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
		//设置监听
		mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(GridViewActivity.this, "点击了" + position, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onItemLongClick(View view, int position) {
				Toast.makeText(GridViewActivity.this, "长按了" + position, Toast.LENGTH_SHORT).show();
			}
		}));
		//设置Adapter
		mRecyclerView.setAdapter(new RecyclerView.Adapter() {
			@Override
			public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
				View view = LayoutInflater.from(GridViewActivity.this).inflate(R.layout.item_list, null);
//				view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
				return new GridHolder(view);
			}

			@Override
			public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
				((GridHolder) holder).tv.setText(mDatas.get(position));
			}

			@Override
			public int getItemCount() {
				return mDatas.size();
			}

			class GridHolder extends RecyclerView.ViewHolder {
				private TextView tv;

				public GridHolder(View itemView) {
					super(itemView);
					tv = (TextView) itemView.findViewById(R.id.tv_text);
				}
			}
		});

	}
}
