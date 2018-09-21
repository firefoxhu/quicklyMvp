package com.quickly.fragment.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.R;
import com.quickly.application.QuicklyApplication;
import com.quickly.bean.CategoryBean;
import com.quickly.module.content.NewsContentActivity;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeCategoryView extends LinearLayout {

    private Context mContext;

    private List<CategoryBean.DataBean.ListBean>  mCategoryItems;

    private View mRootView;

    public HomeCategoryView(Context context, List<CategoryBean.DataBean.ListBean> data) {
        super(context);
        mContext = context;
        this.mCategoryItems = data;
        this.initView();
    }

    public void initView(){

        mRootView = LayoutInflater.from(mContext).inflate(R.layout.linear_layout_view,this);
        LinearLayout linearLayout = mRootView.findViewById(R.id.linear_content);

        LayoutParams layoutParams = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        GridView gridView = new GridView(mContext);
        gridView.setLayoutParams(layoutParams);
        gridView.setNumColumns(4);
        gridView.setAdapter(new GridAdapter(mContext,mCategoryItems));
        linearLayout.addView(gridView);
    }

    class GridAdapter extends BaseAdapter{

        private Context mContext;

        private List<CategoryBean.DataBean.ListBean>  mListBeans;

        private LayoutInflater mInflater;

        public GridAdapter(Context context,List<CategoryBean.DataBean.ListBean> data){
            this.mContext = context;
            this.mListBeans = data;
            this.mInflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return mListBeans.size();
        }

        @Override
        public Object getItem(int position) {
            return mListBeans.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            Holder holder;

            if(convertView == null) {
                holder = new Holder();
                convertView = mInflater.inflate(R.layout.home_category_item, null);
                holder.iv = convertView.findViewById(R.id.iv);
                holder.tv = convertView.findViewById(R.id.tv);
                convertView.setTag(holder);

                RxView.clicks(convertView)
                        .throttleFirst(3, TimeUnit.SECONDS)
                        .subscribe(o -> Toast.makeText(mContext, ""+mListBeans.get(position).getTitle(), Toast.LENGTH_SHORT).show());
            }else {
                holder = (Holder) convertView.getTag();
            }

            Glide.with(mContext).load(mListBeans.get(position).getImgUrl()).thumbnail(0.1f).into(holder.iv);
            holder.tv.setText(mListBeans.get(position).getTitle());

            return convertView;
        }

        private class Holder{
            ImageView iv;

            TextView tv;
        }
    }

}
