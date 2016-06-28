package com.example.yang.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ypr on 2016/6/27 16:07.
 * 描述:
 * TODO:
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

	private List<String> mList;  //用户列表
	private Context mContext;

	//自定义item点击事件
	protected interface OnItemClickListener {
		void OnItemClick(View v, int position);

		void OnItemLongClick(View v, int position);
	}

	private OnItemClickListener listener;

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}

	public RecyclerViewAdapter(Context context, List list) {
		this.mContext = context;
		this.mList = list;
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null);
		view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		return new MyViewHolder(view);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
		((MyViewHolder) holder).mText.setText(mList.get(position));
		((MyViewHolder)holder).itemView.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (listener != null) {
					listener.OnItemClick(v, position);
				}
			}
		});
		((MyViewHolder)holder).itemView.setOnLongClickListener(new View.OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				if (listener != null) {
					listener.OnItemLongClick(v, position);
					return true;
				}
				return false;
			}
		});
	}

	@Override
	public int getItemCount() {
		return mList.size();
	}

	class MyViewHolder extends RecyclerView.ViewHolder {
		TextView mText;
		public View itemView;

		public MyViewHolder(View itemView) {
			super(itemView);
			this.itemView = itemView;
			mText = (TextView) itemView.findViewById(R.id.tv_text);
		}
	}
}
