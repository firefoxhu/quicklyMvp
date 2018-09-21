package com.quickly.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.quickly.R;
import com.zhouwei.mzbanner.holder.MZViewHolder;

public class HomeBannerViewHolder implements MZViewHolder<String> {

    private ImageView mImageView;

    @Override
    public View createView(Context context) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.home_banner_item, null);

        mImageView =  inflate.findViewById(R.id.banner_image);

        return inflate;
    }

    @Override
    public void onBind(Context context, int i, String s) {

        Glide.with(context).load(s).into(mImageView);
    }
}
