package com.quickly.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.quickly.utils.DensityUtil;
import com.zhouwei.mzbanner.holder.MZViewHolder;

public class BannerAdapter implements MZViewHolder<String> {

        private ImageView imageView;

        @Override
        public View createView(Context context) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, DensityUtil.dp2px(140));
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setLayoutParams(params);
            return imageView;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            Glide.with(context).load(s).into(imageView);
        }
}