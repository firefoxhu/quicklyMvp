package com.quickly.module.content;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.quickly.ErrorAction;
import com.quickly.ImageBrowserActivity;
import com.quickly.api.INewsApi;
import com.quickly.application.QuicklyApplication;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.bean.news.NewsDetail;
import com.quickly.utils.RetrofitFactory;
import com.quickly.utils.SettingUtil;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class NewsContentPresenter implements INewsContent.Presenter {

    private static final String TAG = "NewsContentPresenter";
    // 获取img标签正则
    private static final String IMAGE_URL_REGEX = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
    private INewsContent.View view;
    private String html;

    NewsContentPresenter(INewsContent.View view) {
        this.view = view;
    }

    @Override
    public void doLoadData(NewsArticleItem.DataBean.ListBean dataBean) {

        RetrofitFactory.getRetrofit().create(INewsApi.class).getNewsDetail(dataBean.getId())
                .map(data->getHTML(data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .as(view.bindAutoDispose())
                .subscribe(s -> view.onSetWebView(s,true),
                        throwable -> {
                            view.onSetWebView(null,false);
                            ErrorAction.print(throwable);
                            doShowNetError();
                        });
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {
        view.onHideLoading();
        view.onShowNetError();
    }

    @Override
    public void doLoadMoreData() {

    }

    @Override
    public boolean noDataLoad() {
        return false;
    }

    private String getHTML(NewsDetail bean) {
        String content = bean.getData().getContent();
        if (content != null) {


            html = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">" +
                    "<body>\n" +
                    "<article class=\"article-container\">\n" +
                    "    <div class=\"article__content article-content\">" +
                    content +
                    "    </div>\n" +
                    "</article>\n" +
                    "</body>\n" +
                    "</html>";

            return html;
        } else {
            return null;
        }
    }

    /**
     * js 通信接口，定义供 JavaScript 调用的交互接口
     * 点击图片启动新的 Activity，并传入点击图片对应的 url 和页面所有图片
     * 对应的 url
     *
     * @param url 点击图片对应的 url
     */
    @JavascriptInterface
    public void openImage(String url) {
        if (!TextUtils.isEmpty(url)) {
            ArrayList<String> list = getAllImageUrlFromHtml(html);
            if (list.size() > 0) {
                ImageBrowserActivity.start(QuicklyApplication.appContext, url, list);
                Log.d(TAG, "openImage: " + list.toString());
            }
        }
    }

    /***
     * 获取页面所有图片对应的地址对象，
     * 例如 <img src="http://sc1.hao123img.com/data/f44d0aab7bc35b8767de3c48706d429e" />
     */
    private ArrayList<String> getAllImageUrlFromHtml(String html) {
        Matcher matcher = Pattern.compile(IMAGE_URL_REGEX).matcher(html);
        ArrayList<String> imgUrlList = new ArrayList<>();
        while (matcher.find()) {
            int count = matcher.groupCount();
            if (count >= 1) {
                imgUrlList.add(matcher.group(1));
            }
        }
        return imgUrlList;
    }

}
