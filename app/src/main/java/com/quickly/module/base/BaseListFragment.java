package com.quickly.module.base;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;
import com.quickly.R;
import com.quickly.utils.RxBus;
import com.quickly.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;
import io.reactivex.Observable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

import static android.view.View.OVER_SCROLL_NEVER;

/**
 * Created by Meiji on 2017/6/10.
 */

public abstract class BaseListFragment<T extends IBasePresenter> extends LazyLoadFragment<T> implements IBaseListView<T>, OnRefreshListener,OnRefreshLoadMoreListener {

    public static final String TAG = "BaseListFragment";
    protected RecyclerView recyclerView;
    protected RefreshLayout mRefreshLayout;
    protected MultiTypeAdapter adapter;
    protected Items oldItems = new Items();
    protected boolean canLoadMore = true;
    protected Observable<Integer> observable;
    protected Toolbar mToolbar;
    @Override
    protected int attachLayoutId() {
        return R.layout.fragment_list;
    }


    @Override
    protected void initView(View view) {
        mToolbar = view.findViewById(R.id.toolbar);


        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);
        // 设置刷新内容
        mRefreshLayout.setRefreshContent(recyclerView);
        mRefreshLayout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);
        mRefreshLayout.setDragRate(1);
        mRefreshLayout.setHeaderMaxDragRate(2.0f);
        mRefreshLayout.setEnableNestedScroll(false);
        // 设置监听事件
        mRefreshLayout.setOnRefreshLoadMoreListener(this);
        // recycler view
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        StatusBarUtil.setPaddingSmart(getActivity(), mToolbar);
        StatusBarUtil.setPaddingSmart(getActivity(), recyclerView);
        StatusBarUtil.setMargin(getActivity(), view.findViewById(R.id.header));
        StatusBarUtil.setPaddingSmart(getActivity(), view.findViewById(R.id.blur_view));


        mRefreshLayout.autoRefresh();
    }

    @Override
    public void fetchData() {
        observable = RxBus.getInstance().register(BaseListFragment.TAG);
        observable.subscribe(integer -> adapter.notifyDataSetChanged());
    }

    @Override
    public void onShowNetError() {
        Toast.makeText(mContext,"网络异常！", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onDestroy() {
        RxBus.getInstance().unregister(BaseListFragment.TAG, observable);
        super.onDestroy();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        presenter.doRefresh();
        this.canLoadMore = presenter.noDataLoad();
        if(this.canLoadMore) {
            refreshLayout.finishRefresh();
        }else {
            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
            refreshLayout.setNoMoreData(false);
        }

    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        presenter.doLoadMoreData();
        this.canLoadMore = presenter.noDataLoad();
        if(this.canLoadMore) {
            refreshLayout.finishLoadMore();
        }else {
            refreshLayout.finishLoadMoreWithNoMoreData();//将不会再次触发加载更多事件
            refreshLayout.setNoMoreData(false);
        }


    }

}
