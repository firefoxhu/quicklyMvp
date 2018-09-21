package com.quickly.fragment.circle;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.quickly.R;
import com.quickly.common.BaseRecyclerAdapter;
import com.quickly.common.PhotoViewActivity;
import com.quickly.common.SmartViewHolder;
import com.quickly.bean.ArticleItem;
import com.quickly.fragment.circle.article.PictureArray;
import com.quickly.network.RequestCenter;
import com.quickly.okhttp.listener.DisposeDataListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import com.wx.goodview.GoodView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static android.view.View.OVER_SCROLL_NEVER;

public class ArticlePanel extends Fragment implements OnRefreshListener,OnRefreshLoadMoreListener, PictureArray.IScaleImageListener,AdapterView.OnItemClickListener {

    private RecyclerView mRecyclerView;

    private BaseRecyclerAdapter<ArticleItem.DataBean.ListBean> mUserBaseRecyclerAdapter;

    private RefreshLayout mRefreshLayout;

    private GoodView mGoodView;

    private Collection<ArticleItem.DataBean.ListBean> mListBeans = new ArrayList<>();


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
        if(mUserBaseRecyclerAdapter == null) {
            mRecyclerView.setAdapter(mUserBaseRecyclerAdapter = new BaseRecyclerAdapter<ArticleItem.DataBean.ListBean>(mListBeans, R.layout.circle_article_picture_item,this) {
                @Override
                protected void onBindViewHolder(SmartViewHolder holder, ArticleItem.DataBean.ListBean model, int position) {
                    ImageView profile = holder.itemView.findViewById(R.id.profile_image);
                    Glide.with(getContext()).load(model.getAvatar()).into(profile);
                    holder.text(R.id.nickname, model.getAuthor());
                    holder.expandTextView(R.id.etv,model.getContent(), position);

                    if (model.getPictures() != null) {
                        holder.cleanLinearLayoutContent(R.id.picture);
                        holder.linearLayoutContent(R.id.picture, new PictureArray(getContext(),model.getPictures(), ArticlePanel.this));
                    }
                    holder.text(R.id.source,model.getLocation());
                    holder.text(R.id.comment,model.getCommentsNumber()+"评论");
                    holder.text(R.id.zan,model.getFabulous()+"");
                    goodViewOperator(holder.itemView.findViewById(R.id.zan_icon),position);


                }
            });

        }
    }
    //
    private ArticleItem articleItem;

    private int position = 0;

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout){

        position = 0;

        RequestCenter.circleArticle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                articleItem = (ArticleItem)responseObj;
                mListBeans = articleItem.getData().getList();
                mUserBaseRecyclerAdapter.refresh(mListBeans);
                if(articleItem.getData().isHasNext()) {
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
    public  void onLoadMore(@NonNull RefreshLayout refreshLayout) {

        ++position;

        RequestCenter.circleArticle(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                articleItem = (ArticleItem)responseObj;
                mListBeans = articleItem.getData().getList();
                mUserBaseRecyclerAdapter.loadMore(mListBeans);
                if(articleItem.getData().isHasNext()) {
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

    @Override
    public void scale(List<String> pictures, int position) {

        if(pictures.size() <= 0) {
            return;
        }

        // 跳转页面
        Intent intent = new Intent(getActivity(),PhotoViewActivity.class);

        intent.putStringArrayListExtra(PhotoViewActivity.PHOTO_LIST, (ArrayList<String>) pictures);
        intent.putExtra(PhotoViewActivity.POINTER_POSITION,position);
        // 设置启动模式
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        // 跳转动画
        getActivity().overridePendingTransition(R.anim.anim_in,R.anim.anim_out);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getContext(), "当前点击的是:"+view.getId()+" position:"+position+" id:"+id, Toast.LENGTH_SHORT).show();
    }


    private void goodViewOperator(ImageView imageView,int position){

        imageView.setOnClickListener(v->{
            mGoodView = new GoodView(getContext());
            mGoodView.setText("+1");
            mGoodView.show(imageView);
        });

    }

}
