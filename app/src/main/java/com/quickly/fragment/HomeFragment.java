package com.quickly.fragment;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.quickly.api.ICategoryApi;
import com.quickly.fragment.home.HomeArticleDetailActivity;
import com.quickly.R;
import com.quickly.adapter.HomeBannerViewHolder;
import com.quickly.adapter.HomeCategoryAdapter;
import com.quickly.adapter.HomeRecommendAdapter;
import com.quickly.api.INewsApi;
import com.quickly.bean.CategoryBean;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.fragment.home.HomeCategoryView;
import com.quickly.fragment.home.LoadMoreListView;
import com.quickly.utils.RetrofitFactory;
import com.zhouwei.mzbanner.MZBannerView;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator;
/**
 * A simple {@link Fragment} subclass.
 */
@SuppressWarnings("all")
public class HomeFragment extends BaseFragment implements LoadMoreListView.ILoadMoreListener {

    private MZBannerView mMZBannerView;

    private List<String> bannerLists = new ArrayList<>();
    ////////////////////////////////////////////
    private ViewPager mViewPagerCategory;

    private CircleIndicator mIndicator;

    private List<View>  mCategoryViews = new ArrayList<>(2);

    private List<CategoryBean.DataBean.ListBean>  mCategoryData = new ArrayList<>();

    ////////////////////////////////////
    private LoadMoreListView mListViewRecommend;

    private List<NewsArticleItem.DataBean.ListBean> mRecommendData;

    private HomeRecommendAdapter homeRecommendAdapter;

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_layout;
    }

    @Override
    public void init(Bundle savedInstanceState) {

        mMZBannerView = mRootView.findViewById(R.id.home_banner);

        mViewPagerCategory = mRootView.findViewById(R.id.home_category);

        mIndicator = mRootView.findViewById(R.id.home_indicator);

        mListViewRecommend = mRootView.findViewById(R.id.home_recommend);

        mListViewRecommend.setILoadMoreListener(this);



        initData();
    }
    public void initData() {

        bannerLists.add("http://image.luosen365.com/016e8bbee88a4ce0bf9daca6cc3261e4.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_01.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_02.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_03.jpg");
        bannerLists.add("http://image.luosen365.com/home_banner_04.jpg");

        if(mMZBannerView  != null){

            //设置BannerView 的切换时间间隔
            mMZBannerView.setDelayedTime(3000);

            //设置ViewPager（Banner）切换速度
            mMZBannerView.setDuration(800);

            mMZBannerView.setBannerPageClickListener((view,position)->{

            });
            // 设置数据
            mMZBannerView.setPages(bannerLists,()->new HomeBannerViewHolder());
        }

        RetrofitFactory.getRetrofit().create(ICategoryApi.class).getNewsCategory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resp->{
                    mCategoryData = resp.getData().getList();
                    mCategoryViews.add(new HomeCategoryView(getActivity(),mCategoryData.subList(0,8)));
                    mCategoryViews.add(new HomeCategoryView(getActivity(),mCategoryData.subList(8,mCategoryData.size())));
                    HomeCategoryAdapter homeCategoryAdapter = new HomeCategoryAdapter(mCategoryViews);
                    mViewPagerCategory.setAdapter(homeCategoryAdapter);
                    homeCategoryAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());
                    mIndicator.setViewPager(mViewPagerCategory);
                });
        this.onLoad();


    }



    @Override
    public void onPause() {
        super.onPause();
        if(mMZBannerView != null){
            mMZBannerView.pause();//暂停轮播
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        // 优化
        if(hidden) {
            if(mMZBannerView != null){
                mMZBannerView.pause();//暂停轮播
            }
        }else {
            if(mMZBannerView != null){
                mMZBannerView.start();//开始轮播
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mMZBannerView != null){
            mMZBannerView.start();//开始轮播
        }
    }

    private int recommendPage = 0;


    @Override
    public void onLoad() {
        this.recommendPage++;

        RetrofitFactory.getRetrofit().create(INewsApi.class).getRecommend(recommendPage)
                .map(response->response.getData().getList())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(list->{
            mListViewRecommend.loadComplete();
            mRecommendData = list;
            if(homeRecommendAdapter == null) {
                homeRecommendAdapter = new HomeRecommendAdapter(getActivity(), mRecommendData);
                mListViewRecommend.setAdapter(homeRecommendAdapter);

                mListViewRecommend.setOnItemClickListener((parent,view,position,id)->{
                    NewsArticleItem.DataBean.ListBean item = (NewsArticleItem.DataBean.ListBean) parent.getAdapter().getItem(position);
                    Intent intent = new Intent(getContext(), HomeArticleDetailActivity.class);
                    intent.putExtra(HomeArticleDetailActivity.NEWS_ID,item.getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(intent);
                });
            }else {
                homeRecommendAdapter.onDateChange(mRecommendData);
            }
        },throwable -> {
            recommendPage --;
            throwable.printStackTrace();
        });

    }



}
