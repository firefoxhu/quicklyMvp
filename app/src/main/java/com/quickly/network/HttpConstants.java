package com.quickly.network;

public class HttpConstants {
    /**
     * 方便我们切换测试服务器与主站服务器的地址
     */
    private static final String ROOT_URL = "https://www.luosen365.com";
    private static final String URL = "http://192.168.1.100:8080";

    private static final String SERVER_IP = "http://39.107.228.75:9001";

    public static String CATEGORY_LIST = SERVER_IP + "/category";

    public static String RECOMMEND_LIST = SERVER_IP + "/news/recommend";

    public static String NEWS_DETAIL = SERVER_IP + "/news/detail";

    public static String NEWS_COMMENT = SERVER_IP + "/comment/list";

    public static String CIRCLE_ARTICLE = ROOT_URL + "/article/list";

    public static String CIRCLE_VIDEO = SERVER_IP + "/video/list";



    /** *******************************************************************************************************************************************
     * {"code":0,"msg":"处理成功！","data":{"hasNext":false,"list":[
     * {"id":"288795c262794776aa8982f840b183b1","title":"空调","imgUrl":"http://www.luosen365.com/image/20180516/1526462666513.gif","num":"0"},
     * {"id":"288795c262794776aa8982f840b183b1","title":"油烟机","imgUrl":"http://www.luosen365.com/image/20180516/1526462622687.gif","num":"0"},
     * {"id":"8c1a026392c64dc1bb12138ae1a96eb2","title":"热水器","imgUrl":"http://www.luosen365.com/image/20180516/1526462313535.gif","num":"0"},
     * {"id":"d31810b74135459d9a1fc3e084d33fce","title":"洗衣机","imgUrl":"http://www.luosen365.com/image/20180516/1526462692656.gif","num":"0"},
     * {"id":"da12bb48aa434bec8af024f50f5169b9","title":"其它","imgUrl":"http://www.luosen365.com/image/20180516/1526462719858.gif","num":"0"}]}}
     ** ********************************************************************************************************************************************
     */
    public static String[] VIDEO_TYPE = {
            "288795c262794776aa8982f840b183b1","288795c262794776aa8982f840b183b1","8c1a026392c64dc1bb12138ae1a96eb2","d31810b74135459d9a1fc3e084d33fce","da12bb48aa434bec8af024f50f5169b9"
    };

}