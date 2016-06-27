package com.example.yang.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ypr on 2016/6/27 15:41.
 * 描述:处理RecyclerViewItem的点击事件
 * TODO:
 */
public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

	private OnItemClickListener mListener;
	private GestureDetector mGestureDetector;

	public interface OnItemClickListener {
		void onItemClick(View view, int position);

		void onItemLongClick(View view, int position);
	}

	public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener) {
		mListener = listener;
		// 识别并处理手势
		mGestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				return true;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				// 根据findChildViewUnder(float x, float y)来算出哪个item被选择了
				View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
				// 有item被选则且监听器不为空触发长按事件
				if (childView != null && mListener != null) {
					mListener.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView));
				}
			}
		});
	}

	@Override
	public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
		View childView = rv.findChildViewUnder(e.getX(), e.getY());
		if (childView != null && mListener != null && mGestureDetector.onTouchEvent(e)) {
			// 触发单击事件
			mListener.onItemClick(childView, rv.getChildAdapterPosition(childView));
			return true;
		}
		return false;
	}

	@Override
	public void onTouchEvent(RecyclerView rv, MotionEvent e) {

	}

	@Override
	public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

	}
}
