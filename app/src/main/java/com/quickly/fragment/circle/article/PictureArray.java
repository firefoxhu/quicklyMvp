package com.quickly.fragment.circle.article;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.quickly.R;
import com.quickly.utils.DensityUtil;

import java.util.List;

public class PictureArray extends LinearLayout  implements View.OnClickListener {

    private List<String> mUrls;

    private Context mContext;

    private IScaleImageListener mIScaleImageListener;

    public PictureArray(Context context, List<String> picture, IScaleImageListener iScaleImage) {
        super(context);
        this.mContext = context;
        this.mUrls = picture;
        this.mIScaleImageListener = iScaleImage;
        this.initView();

    }

    private void initView() {

        if(mUrls.size() == 0){
            return;
        }

        View inflate =  LayoutInflater.from(mContext).inflate(R.layout.linear_layout_view,this);

        LinearLayout imageContent = inflate.findViewById(R.id.linear_content);

        LinearLayout imageContent_01 = null;
        LinearLayout imageContent_02 = null;
        LinearLayout imageContent_03 = null;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        if(mUrls.size() <= 3){
            imageContent_01 = new LinearLayout(mContext);
        }else if(mUrls.size() <= 6){
            imageContent_01 = new LinearLayout(mContext);
            imageContent_02 = new LinearLayout(mContext);
        }else if(mUrls.size() <= 9){
            imageContent_01 = new LinearLayout(mContext);
            imageContent_02 = new LinearLayout(mContext);
            imageContent_03 = new LinearLayout(mContext);
        }

        if(imageContent_01 != null) {
            imageContent_01.setLayoutParams(layoutParams);
            imageContent_01.setOrientation(HORIZONTAL);
        }
        if(imageContent_02 != null) {
            imageContent_02.setLayoutParams(layoutParams);
            imageContent_02.setOrientation(HORIZONTAL);
        }

        if(imageContent_03 != null) {
            imageContent_03.setLayoutParams(layoutParams);
            imageContent_03.setOrientation(HORIZONTAL);
        }


        for(int i=0;i<mUrls.size();i++){
            LinearLayout.LayoutParams tvLayoutParams = null;
            ImageView imageView = new ImageView(mContext);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

            switch (mUrls.size()){
                case 1:
                    tvLayoutParams = new LinearLayout.LayoutParams(0, DensityUtil.dp2px(160), 1.0f);
                    break;
                case 2:
                    tvLayoutParams = new LinearLayout.LayoutParams(0, DensityUtil.dp2px(140), 1.0f);
                    if(i == 1)
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(10);
                    break;
                case 3:
                    tvLayoutParams = new LinearLayout.LayoutParams(0, DensityUtil.dp2px(120), 1.0f);
                    if(i >= 1)
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(5);
                    break;
                case 4: case 5:case 6:
                    tvLayoutParams = new LinearLayout.LayoutParams(0, DensityUtil.dp2px(100), 1.0f);
                    tvLayoutParams.topMargin = DensityUtil.dp2px(2);
                    if((i >= 1 && i <= 2) || (i >= 4 && i <= 5 ))
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(5);
                    break;
                case 7: case 8: case 9:
                    tvLayoutParams = new LinearLayout.LayoutParams(0,DensityUtil.dp2px(80), 1.0f);
                    tvLayoutParams.topMargin = DensityUtil.dp2px(2);
                    if((i >= 1 && i <= 2) || (i >= 4 && i <= 5 ) || (i >= 7 && i <= 8))
                        tvLayoutParams.leftMargin = DensityUtil.dp2px(5);
                    break;
                default:
                     break;
            }


            imageView.setLayoutParams(tvLayoutParams);
            imageView.setId(i);

            Glide.with(mContext).load(mUrls.get(i)).into(imageView);

            imageView.setOnClickListener(this);
            if(mUrls.size() <= 3)
                imageContent_01.addView(imageView);
            if(mUrls.size() > 3 && mUrls.size() <= 6)
                if(i < 3){
                    imageContent_01.addView(imageView);
                }else {
                    imageContent_02.addView(imageView);
                }
            if(mUrls.size() > 6 && mUrls.size() <= 9)
                if(i < 3){
                    imageContent_01.addView(imageView);
                }else if(i >= 3 && i < 6){
                    imageContent_02.addView(imageView);
                }else if(i >= 6 && i< 9){
                    imageContent_03.addView(imageView);
                }

        }
        if(imageContent_01 != null) {
            imageContent.addView(imageContent_01);
        }
        if(imageContent_02 != null) {
            imageContent.addView(imageContent_02);
        }
        if(imageContent_03 != null) {
            imageContent.addView(imageContent_03);
        }


    }

    @Override
    public void onClick(View v) {
        mIScaleImageListener.scale(mUrls,v.getId());
    }

    /**
     * 点击小图 跳转到预览界面监听器
     */
    public interface IScaleImageListener{
        void  scale(List<String> pictures,int position);
    }

}
