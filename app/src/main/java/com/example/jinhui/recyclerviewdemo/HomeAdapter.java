package com.example.jinhui.recyclerviewdemo;

import java.util.List;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

    private List<String> mDatas;
    private LayoutInflater mInflater;

    public Context context;
    private int playPosition = -1;
    private int selectPosition = -1;

    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);

        void onItemClick(View itemView, int pos, String s);

        void onItemClick(MyViewHolder myViewHolder, int pos, String s);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public HomeAdapter(Context context, List<String> datas) {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.item_home, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));

        if (position < 4 && HomeActivity.isSelect){
            holder.imageView.setImageResource(R.drawable.btn_play_press);
            HomeActivity.isSelect = true;
        }else {
            holder.imageView.setImageResource(R.drawable.btn_stop_normal);
        }

        Log.e("测试局部刷新 = ", "测试局部刷新");

        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
//                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
//                    mOnItemClickLitener.onItemClick(holder.itemView, pos, mDatas.get(position));

                    mOnItemClickLitener.onItemClick(holder, pos, mDatas.get(position));
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void addData(int position) {
        mDatas.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    public void changed(int pos) {
        this.notifyItemChanged(pos);
    }

    class MyViewHolder extends ViewHolder {

        TextView tv;
        ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_num);
            imageView = view.findViewById(R.id.image);


        }
    }
}