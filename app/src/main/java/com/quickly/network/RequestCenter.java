package com.quickly.network;

import com.quickly.bean.ArticleItem;
import com.quickly.bean.CategoryBean;
import com.quickly.bean.VideoItem;
import com.quickly.okhttp.CommonOkHttpClient;
import com.quickly.okhttp.listener.DisposeDataHandle;
import com.quickly.okhttp.listener.DisposeDataListener;
import com.quickly.okhttp.request.CommonRequest;
import com.quickly.okhttp.request.RequestParams;

import java.util.HashMap;
import java.util.Map;

public class RequestCenter {

    //根据参数发送所有的get请求
    private static void getRequest(String url, RequestParams params,DisposeDataListener listener,Class<?> clazz){
        CommonOkHttpClient.sendRequest(CommonRequest.createGetRequest(url, params),new DisposeDataHandle(listener,clazz));
    }


    private static void postRequest(String url,RequestParams params,DisposeDataListener listener,Class<?> clazz){
        CommonOkHttpClient.sendRequest(CommonRequest.createPostRequest(url,params),new DisposeDataHandle(listener,clazz));
    }




    /**
     * 真正的发送我们首页的请求 咨询类别
     * @param listener
     */
    public static void categoryList(DisposeDataListener listener){
        Map<String,String> params = new HashMap<>();
        params.put("size","30");
        params.put("classId","2904e1433c9541939e36f8b45e1abbdc");
        RequestCenter.getRequest(HttpConstants.CATEGORY_LIST,new RequestParams(params),listener, CategoryBean.class);
    }





    /**
     * 发现的图文页面
     * @param listener
     */
    public static void circleArticle(DisposeDataListener listener,int page){
        Map<String,String> params = new HashMap<>();
        params.put("page",String.valueOf(page));
        params.put("size","8");
        RequestCenter.getRequest(HttpConstants.CIRCLE_ARTICLE,new RequestParams(params),listener, ArticleItem.class);
    }



    /**
     * 发现的图文页面
     * @param listener
     */
    public static void circleVideo(DisposeDataListener listener,int page){
        Map<String,String> params = new HashMap<>();
        params.put("page",String.valueOf(page));
        params.put("size","8");
        params.put("categoryId","288795c262794776aa8982f840b183b1");
        RequestCenter.getRequest(HttpConstants.CIRCLE_VIDEO,new RequestParams(params),listener, VideoItem.class);
    }



}
