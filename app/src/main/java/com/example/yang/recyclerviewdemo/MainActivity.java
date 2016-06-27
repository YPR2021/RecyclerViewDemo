package com.example.yang.recyclerviewdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

	private Button list, grid, staggered;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (Button) findViewById(R.id.btn_list);
		grid = (Button) findViewById(R.id.btn_grid);
		staggered = (Button) findViewById(R.id.btn_staggered);
//
		list.setOnClickListener(this);
		grid.setOnClickListener(this);
		staggered.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btn_list:
				startActivity(new Intent(this,ListViewActivity.class));
				break;
			case R.id.btn_grid:
				startActivity(new Intent(this,GridViewActivity.class));
				break;
			case R.id.btn_staggered:
				startActivity(new Intent(this,StaggeredViewActivity.class));
				break;
		}
	}
}
