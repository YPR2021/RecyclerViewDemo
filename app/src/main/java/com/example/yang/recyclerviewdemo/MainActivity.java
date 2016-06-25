package com.example.yang.recyclerviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private Button list, grid, staggered;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		list = (Button) findViewById(R.id.btn_list);
		grid = (Button) findViewById(R.id.btn_grid);
		staggered = (Button) findViewById(R.id.btn_staggered);

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
//				startActivity(new Intent(this,GridViewActivity.class));
				break;
			case R.id.btn_staggered:
//				startActivity(new Intent(this,StaggeredViewActivity.class));
				break;
		}
	}
}
