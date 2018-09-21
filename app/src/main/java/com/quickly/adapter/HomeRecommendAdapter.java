package com.quickly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.quickly.R;
import com.quickly.bean.news.NewsArticleItem;

import java.util.List;

public class HomeRecommendAdapter extends BaseAdapter{

    private List<NewsArticleItem.DataBean.ListBean> mListBeans;

    private LayoutInflater mInflater;

    private Context mContext;


    public HomeRecommendAdapter(Context context,List<NewsArticleItem.DataBean.ListBean> data){
        this.mInflater = LayoutInflater.from(context);
        this.mListBeans = data;
        this.mContext = context;
    }

    public void onDateChange(List<NewsArticleItem.DataBean.ListBean> changeData) {
        this.mListBeans.addAll(changeData);
        this.notifyDataSetChanged();  //当有数据变化的时候，自动刷新界面
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


        ViewHolder holder;
        if(convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.home_recommend_item, null);

            holder.image = convertView.findViewById(R.id.image);
            holder.title = convertView.findViewById(R.id.title);
            holder.views = convertView.findViewById(R.id.views);
            holder.source = convertView.findViewById(R.id.source);
            holder.zan = convertView.findViewById(R.id.zan);

            convertView.setTag(holder);
        }else {
            holder = (ViewHolder)convertView.getTag();
        }

        Glide.with(mContext).load(mListBeans.get(position).getImages().get(0)).thumbnail(0.1f).into(holder.image);




        holder.title.setText(mListBeans.get(position).getTitle());
        holder.source.setText(mListBeans.get(position).getResource());
        holder.views.setText(mListBeans.get(position).getViews()+"浏览");
        holder.zan.setText(mListBeans.get(position).getFabulous()+"赞");
        return convertView;
    }

    private class ViewHolder{

         ImageView image;

         TextView title;

         TextView source;

         TextView views;

         TextView zan;

    }

}
