package com.quickly.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.List;

public class PhotoViewPagerAdapter extends PagerAdapter implements View.OnClickListener{

    private List<String> mPhotoUrls;

    private Context mContext;

    private IClickOnceCloseListener mIClickOnceCloseListener;

    public PhotoViewPagerAdapter(List<String> mPhotoUrls, Context mContext,IClickOnceCloseListener mIClickOnceCloseListener) {
        this.mPhotoUrls = mPhotoUrls;
        this.mContext = mContext;
        this.mIClickOnceCloseListener = mIClickOnceCloseListener;
    }

    @Override
    public int getCount() {
        return mPhotoUrls.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        ImageView photo = new PhotoView(mContext);
        photo.setOnClickListener(this);

        Glide.with(mContext).load(mPhotoUrls.get(position)).into(photo);

        container.addView(photo,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);


        return photo;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }

    @Override
    public void onClick(View v) {
        mIClickOnceCloseListener.close();
    }

    /**
     * 单击图片关闭预览页监听器
     */
    public interface IClickOnceCloseListener{
        void close();
    }


}