package com.quickly.fragment.circle;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.quickly.R;
import com.quickly.common.BaseRecyclerAdapter;
import com.quickly.common.SmartViewHolder;
import com.quickly.bean.VideoItem;
import com.quickly.network.RequestCenter;
import com.quickly.okhttp.listener.DisposeDataListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.Collection;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import static android.view.View.OVER_SCROLL_NEVER;

public class VideoPanel extends Fragment implements OnRefreshListener,OnRefreshLoadMoreListener,AdapterView.OnItemClickListener {

    private RecyclerView mRecyclerView;

    private BaseRecyclerAdapter<VideoItem.DataBean.ListBean> mVideoBaseRecyclerAdapter;

    private RefreshLayout mRefreshLayout;

    private JZVideoPlayerStandard jzVideoPlayerStandard;


    private Collection<VideoItem.DataBean.ListBean> mListBeans = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        if(mRefreshLayout == null) {
            // 创建刷新容器
            mRefreshLayout = new SmartRefreshLayout(inflater.getContext());

            // 设置头部尾部刷新样式
            mRefreshLayout.setRefreshHeader(new ClassicsHeader(inflater.getContext()));
            mRefreshLayout.setRefreshFooter(new ClassicsFooter(inflater.getContext()));

            // 设置刷新内容
            mRefreshLayout.setRefreshContent(mRecyclerView = new RecyclerView(inflater.getContext()));
            mRefreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
            mRefreshLayout.setDragRate(1);
            mRefreshLayout.setHeaderMaxDragRate(1.2f);
            mRefreshLayout.setEnableNestedScroll(false);

            // 设置监听事件
            mRefreshLayout.setOnRefreshLoadMoreListener(this);

            mRecyclerView.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
            mRecyclerView.setOverScrollMode(OVER_SCROLL_NEVER);
            mRecyclerView.setItemAnimator(new DefaultItemAnimator());
            mRecyclerView.addItemDecoration(new DividerItemDecoration(inflater.getContext(),DividerItemDecoration.VERTICAL));
        }
        return mRefreshLayout.getLayout();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //初始化自动刷新一次
        mRefreshLayout.autoRefresh();

        if(mVideoBaseRecyclerAdapter == null) {
            mRecyclerView.setAdapter(mVideoBaseRecyclerAdapter = new BaseRecyclerAdapter<VideoItem.DataBean.ListBean>(mListBeans, R.layout.video_item) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, VideoItem.DataBean.ListBean model, int position) {
                    jzVideoPlayerStandard =  holder.itemView.findViewById(R.id.video_player);
                    jzVideoPlayerStandard.setUp(model.getUrl(), JZVideoPlayer.SCREEN_WINDOW_LIST,model.getTitle());
                    Glide.with(getContext()).load(model.getPic()).into(jzVideoPlayerStandard.thumbImageView);
                }
            });
        }
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "当前点击的是:"+view.getId()+" position:"+position+" id:"+id, Toast.LENGTH_SHORT).show();
    }

    //
    private VideoItem mVideoItem;

    private int position = 0;


    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        position = 0;
        RequestCenter.circleVideo(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mVideoItem = (VideoItem)responseObj;
                mListBeans = mVideoItem.getData().getList();
                mVideoBaseRecyclerAdapter.refresh(mListBeans);
                if(mVideoItem.getData().isHasNext()) {
                    refreshLayout.finishRefresh();
                }else {
                    refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                    refreshLayout.setNoMoreData(false);
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
            }
        },position);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        ++position;
        RequestCenter.circleVideo(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                mVideoItem = (VideoItem)responseObj;
                mListBeans = mVideoItem.getData().getList();
                mVideoBaseRecyclerAdapter.loadMore(mListBeans);
                if(mVideoItem.getData().isHasNext()) {
                    refreshLayout.finishLoadMore();
                }else {
                    refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
                    refreshLayout.setNoMoreData(false);
                }
            }
            @Override
            public void onFailure(Object reasonObj) {
            }
        },position);
    }
}
