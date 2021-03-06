package com.example.jinhui.recyclerviewdemo;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


/**
 * https://blog.csdn.net/lmj623565791/article/details/45059587
 *
 * recyclerViewDemo 学习！
 */

public class HomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    public static boolean isSelect = true;
    public static boolean isSelect1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_recyclerview);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mAdapter = new HomeAdapter(this, mDatas);

        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(this));
        // 设置item动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        initEvent();

    }

    private void initEvent() {
        mAdapter.setOnItemClickLitener(new HomeAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(HomeActivity.this, position + " click",
//                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(HomeActivity.this, position + " long click",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemClick(View itemView, int pos, String s) {
                Toast.makeText(HomeActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
//                mAdapter.changed(pos);
            }

            @Override
            public void onItemClick(HomeAdapter.MyViewHolder myViewHolder, int pos, String s) {
                if (pos < 4){
                    if (isSelect){
                        isSelect = false;
                        myViewHolder.imageView.setImageResource(R.drawable.btn_stop_normal);
//                        myViewHolder.imageView.setSelected(false);
                        mAdapter.notifyItemChanged(pos);
                    }else {
                        isSelect = true;
                        myViewHolder.imageView.setImageResource(R.drawable.btn_play_press);
//                        myViewHolder.imageView.setSelected(true);
                        mAdapter.notifyItemChanged(pos);
                    }
                }else {
                    if (isSelect1){
                        isSelect1 = false;
                        myViewHolder.imageView.setImageResource(R.drawable.btn_play_press);
                    }else {
                        isSelect1 = true;
                        myViewHolder.imageView.setImageResource(R.drawable.btn_stop_normal);
                    }

                }
                Toast.makeText(HomeActivity.this, "s = " + s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void initData() {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_change:
                Toast.makeText(this, "待开发", Toast.LENGTH_SHORT).show();
//                mAdapter.notifyItemChanged(1);
                break;
            case R.id.id_action_add:
                mAdapter.addData(1);
                break;
            case R.id.id_action_delete:
                mAdapter.removeData(1);
                break;
            case R.id.id_action_gridview:
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 4));
                break;
            case R.id.id_action_listview:
                mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
                break;
            case R.id.id_action_horizontalGridView:
                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(4,
                        StaggeredGridLayoutManager.HORIZONTAL));
                break;

            case R.id.id_action_staggeredgridview:
                Intent intent = new Intent(this, StaggeredGridLayoutActivity.class);
                startActivity(intent);
                break;
        }
        return true;
    }

}
