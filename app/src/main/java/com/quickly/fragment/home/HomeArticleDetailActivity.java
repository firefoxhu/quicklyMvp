package com.quickly.fragment.home;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.quickly.Constant;
import com.quickly.R;
import com.quickly.api.INewsApi;
import com.quickly.common.BaseRecyclerAdapter;
import com.quickly.common.SmartViewHolder;
import com.quickly.bean.news.NewsCommentItem;
import com.quickly.bean.news.NewsDetail;
import com.quickly.network.RequestCenter;
import com.quickly.okhttp.listener.DisposeDataListener;
import com.quickly.utils.RetrofitFactory;
import com.quickly.utils.SettingUtil;

import java.util.ArrayList;
import java.util.Collection;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class HomeArticleDetailActivity extends AppCompatActivity {

    public static final String NEWS_ID = "newsId";

    private NestedScrollView mNestedScrollView;

    private TextView mTitle,mSource;

    private WebView mWebView;

    private RecyclerView mRecyclerView;

    private BaseRecyclerAdapter<NewsCommentItem.DataBean.ListBean> mNewsCommetAdapter;

    private Collection<NewsCommentItem.DataBean.ListBean> mListBeans = new ArrayList<>();


    @Override
    protected void onNewIntent(Intent intent) {
        this.setIntent(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_article_detail_layout);
        this.initData();
        mNestedScrollView = findViewById(R.id.nested_view);

        mTitle = findViewById(R.id.title);

        mSource = findViewById(R.id.source);

        mWebView = findViewById(R.id.web_view);

        mRecyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        settings.setBuiltInZoomControls(false);
        // 缓存
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);


        mRecyclerView.setAdapter(mNewsCommetAdapter = new BaseRecyclerAdapter<NewsCommentItem.DataBean.ListBean>(mListBeans,R.layout.comment_item) {
            @Override
            protected void onBindViewHolder(SmartViewHolder holder, NewsCommentItem.DataBean.ListBean model, int position) {

                //CircleImageView imageView =  holder.itemView.findViewById(R.id.header);
                //Glide.with(HomeArticleDetailActivity.this).load(model.getId()).into(imageView);
                holder.text(R.id.content,model.getContent());

            }
        } );

    }


    private void initData(){

        RetrofitFactory.getRetrofit().create(INewsApi.class).getNewsDetail(getIntent().getStringExtra(HomeArticleDetailActivity.NEWS_ID))
                .map(response->response.getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    mTitle.setText(data.getTitle());
                    mSource.setText(data.getResource());
                    mWebView.loadData(data.getContent(), "text/html", "utf-8");
                    this.commentData();
                });

    }

    private int currentPage = 0;

    private void commentData() {

        RetrofitFactory.getRetrofit().create(INewsApi.class).getNewsComment(currentPage,getIntent().getStringExtra(HomeArticleDetailActivity.NEWS_ID))
                .map(resp->resp.getData().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listBeans -> {
                    mListBeans = listBeans;

                    mNewsCommetAdapter.refresh(mListBeans);

                    ++currentPage;
                });
    }


}
