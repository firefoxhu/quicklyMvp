package com.quickly.fragment.home;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.quickly.R;

public class LoadMoreListView extends ListView implements AbsListView.OnScrollListener {

    private View footer;

    private int mTotalItemCount;


    private boolean isLoading = false;

    private ILoadMoreListener mILoadMoreListener;

    public LoadMoreListView(Context context) {
        super(context);
        initView(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        footer  = inflater.inflate(R.layout.load_more,null,false);
        footer.setVisibility(GONE);
        this.addFooterView(footer);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        this.mTotalItemCount=totalItemCount;
    }

    @Override
    public void onScrollStateChanged(AbsListView listView, int scrollState) {

        int lastVisibleIndex=listView.getLastVisiblePosition();
        if (!isLoading && scrollState == OnScrollListener.SCROLL_STATE_IDLE//停止滚动
                && lastVisibleIndex ==mTotalItemCount-1) {//滑动到最后一项

            isLoading=true;

            if (mILoadMoreListener != null) {
                isLoading = true;
                footer.setVisibility(VISIBLE);
                //加载跟多的数据
                this.mILoadMoreListener.onLoad();
            }
        }
    }


    public void loadComplete(){
        isLoading = false;
        footer.setVisibility(GONE);
    }


    public void setILoadMoreListener(ILoadMoreListener ILoadMoreListener) {
        mILoadMoreListener = ILoadMoreListener;
    }

    public interface ILoadMoreListener{
        void onLoad();
    }


}
