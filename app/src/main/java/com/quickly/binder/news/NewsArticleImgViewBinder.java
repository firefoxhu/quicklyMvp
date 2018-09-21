package com.quickly.binder.news;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding2.view.RxView;
import com.quickly.IntentAction;
import com.quickly.R;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.fragment.home.HomeArticleDetailActivity;
import com.quickly.module.content.NewsContentActivity;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import me.drakeet.multitype.ItemViewBinder;

public class NewsArticleImgViewBinder extends ItemViewBinder<NewsArticleItem.DataBean.ListBean,NewsArticleImgViewBinder.ViewHolder>{

    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_news_article_img,parent,false));
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull NewsArticleItem.DataBean.ListBean item) {
        final Context context = holder.itemView.getContext();

        Glide.with(context).load(item.getImages().get(0)).thumbnail(0.1f).into( holder.iv_image);
        Glide.with(context).load(item.getImages().get(0)).thumbnail(0.1f).into( holder.iv_media);
        holder.tv_title.setText(item.getTitle());
        holder.tv_extra.setText(item.getResource() + " - " + item.getViews() +"浏览");
        RxView.clicks(holder.itemView)
        .throttleFirst(3, TimeUnit.SECONDS)
        .subscribe(o -> NewsContentActivity.launch(item));

        holder.iv_dots.setOnClickListener(view->{
            PopupMenu popupMenu = new PopupMenu(context,holder.iv_dots, Gravity.END,0,R.style.MyPopupMenu);
            popupMenu.inflate(R.menu.menu_share);
            popupMenu.setOnMenuItemClickListener(menu->{
                int itemId = menu.getItemId();
                if (itemId == R.id.action_share) {
                    IntentAction.send(context, item.getTitle() + "\n");
                }
                return false;
            });
            popupMenu.show();
        });


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView iv_media;

        private TextView tv_extra;

        private ImageView iv_dots;

        private TextView tv_title;

        private ImageView iv_image;

        ViewHolder(View itemView) {
            super(itemView);
            this.iv_media = itemView.findViewById(R.id.iv_media);
            this.tv_extra = itemView.findViewById(R.id.tv_extra);
            this.iv_dots = itemView.findViewById(R.id.iv_dots);
            this.tv_title = itemView.findViewById(R.id.tv_title);
            this.iv_image = itemView.findViewById(R.id.iv_image);
        }
    }
}
