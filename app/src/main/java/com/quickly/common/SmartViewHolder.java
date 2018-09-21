package com.quickly.common;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import jaydenxiao.com.expandabletextview.ExpandableTextView;

public class SmartViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private final AdapterView.OnItemClickListener mOnItemClickListener;

    private int mPosition = -1;

    public SmartViewHolder(@NonNull View itemView, AdapterView.OnItemClickListener onItemClickListener) {
        super(itemView);
        mOnItemClickListener = onItemClickListener;
        itemView.setOnClickListener(this);

        // 设置水波纹背景

        if(itemView.getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            Resources.Theme theme = itemView.getContext().getTheme();
            int top = itemView.getPaddingTop();
            int bottom = itemView.getPaddingBottom();
            int left = itemView.getPaddingLeft();
            int right = itemView.getPaddingRight();
            if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
                itemView.setBackgroundResource(typedValue.resourceId);
            }
            itemView.setPadding(left, top, right, bottom);
        }
    }

    public void setPosition(int position) {
        mPosition = position;
    }

    @Override
    public void onClick(View v) {

        if(mOnItemClickListener != null) {
            int position = getAdapterPosition();
            if(position >= 0) {
                mOnItemClickListener.onItemClick(null,v,position,getItemId());
            }else if(mPosition > -1) {
                mOnItemClickListener.onItemClick(null,v,mPosition,getItemId());
            }
        }
    }

    private View findViewById(int id) {
        return id == 0 ? itemView : itemView.findViewById(id);
    }

    public SmartViewHolder text(int id, CharSequence sequence) {
        View view = findViewById(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(sequence);
        }
        return this;
    }

    public SmartViewHolder text(int id,@StringRes int stringRes) {
        View view = findViewById(id);
        if (view instanceof TextView) {
            ((TextView) view).setText(stringRes);
        }
        return this;
    }

    public SmartViewHolder image(int id, int imageId) {
        View view = findViewById(id);
        if (view instanceof ImageView) {
            ((ImageView) view).setImageResource(imageId);
        }
        return this;
    }
    // 图文相关拓展
    public SmartViewHolder expandTextView(int id,String text,int position) {
        View view = findViewById(id);
        if (view instanceof ExpandableTextView) {
            ((ExpandableTextView) view).setText(text,position);
        }
        return this;
    }

    public SmartViewHolder linearLayoutContent(int id,LinearLayout linearLayout){
        View view = findViewById(id);
        if (view instanceof LinearLayout) {
            ((LinearLayout) view).addView(linearLayout);
        }
        return this;
    }

    public SmartViewHolder cleanLinearLayoutContent(int id){
        View view = findViewById(id);
        if (view instanceof LinearLayout) {
            ((LinearLayout) view).removeAllViews();
        }
        return this;
    }

}