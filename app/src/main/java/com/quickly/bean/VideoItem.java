package com.quickly.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

public class VideoItem implements Serializable {


    /**
     * code : 0
     * msg : 处理成功！
     * data : {"hasNext":false,"list":[{"id":"a2bc3fcde3bf462c901eb20a00ea8c78","categoryId":"288795c262794776aa8982f840b183b1","title":"空调清洗出来的脏物","abstracts":"空调清洗出来的脏物","url":"http://www.luosen365.com/video/20180521/1526885417656.mp4","pic":"http://www.luosen365.com/image/20180521/1526885078018.png","playNumber":"37","author":"信阳365","source":"信阳365","fabulous":"18","createTime":"2018-05-21 14:50:49"},{"id":"cc3f21ccfdb8426cba7e7ced9567bdf3","categoryId":"288795c262794776aa8982f840b183b1","title":"空调清洗保养视频","abstracts":"空调清洗保养视频","url":"http://www.luosen365.com/video/20180521/1526882047285.mp4","pic":"http://www.luosen365.com/image/20180521/1526882211295.png","playNumber":"98","author":"信阳365","source":"信阳365","fabulous":"4","createTime":"2018-05-21 13:57:55"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public static VideoItem objectFromData(String str) {

        return new Gson().fromJson(str, VideoItem.class);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hasNext : false
         * list : [{"id":"a2bc3fcde3bf462c901eb20a00ea8c78","categoryId":"288795c262794776aa8982f840b183b1","title":"空调清洗出来的脏物","abstracts":"空调清洗出来的脏物","url":"http://www.luosen365.com/video/20180521/1526885417656.mp4","pic":"http://www.luosen365.com/image/20180521/1526885078018.png","playNumber":"37","author":"信阳365","source":"信阳365","fabulous":"18","createTime":"2018-05-21 14:50:49"},{"id":"cc3f21ccfdb8426cba7e7ced9567bdf3","categoryId":"288795c262794776aa8982f840b183b1","title":"空调清洗保养视频","abstracts":"空调清洗保养视频","url":"http://www.luosen365.com/video/20180521/1526882047285.mp4","pic":"http://www.luosen365.com/image/20180521/1526882211295.png","playNumber":"98","author":"信阳365","source":"信阳365","fabulous":"4","createTime":"2018-05-21 13:57:55"}]
         */

        private boolean hasNext;
        private List<ListBean> list;

        public static DataBean objectFromData(String str) {

            return new Gson().fromJson(str, DataBean.class);
        }

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : a2bc3fcde3bf462c901eb20a00ea8c78
             * categoryId : 288795c262794776aa8982f840b183b1
             * title : 空调清洗出来的脏物
             * abstracts : 空调清洗出来的脏物
             * url : http://www.luosen365.com/video/20180521/1526885417656.mp4
             * pic : http://www.luosen365.com/image/20180521/1526885078018.png
             * playNumber : 37
             * author : 信阳365
             * source : 信阳365
             * fabulous : 18
             * createTime : 2018-05-21 14:50:49
             */

            private String id;
            private String categoryId;
            private String title;
            private String abstracts;
            private String url;
            private String pic;
            private String playNumber;
            private String author;
            private String source;
            private String fabulous;
            private String createTime;

            public static ListBean objectFromData(String str) {

                return new Gson().fromJson(str, ListBean.class);
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAbstracts() {
                return abstracts;
            }

            public void setAbstracts(String abstracts) {
                this.abstracts = abstracts;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getPlayNumber() {
                return playNumber;
            }

            public void setPlayNumber(String playNumber) {
                this.playNumber = playNumber;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getFabulous() {
                return fabulous;
            }

            public void setFabulous(String fabulous) {
                this.fabulous = fabulous;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
