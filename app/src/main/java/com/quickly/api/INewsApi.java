package com.quickly.api;
import com.quickly.bean.news.NewsArticleItem;
import com.quickly.bean.news.NewsCommentItem;
import com.quickly.bean.news.NewsDetail;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface INewsApi {

    @GET("news/recommend?size=8")
    Observable<NewsArticleItem> getRecommend(@Query("page") int page);

    @GET("news/detail")
    Observable<NewsDetail> getNewsDetail(@Query("id") String id);

    @GET("comment/list?size=8")
    Observable<NewsCommentItem> getNewsComment(@Query("page") int page,@Query("id") String id);


}
