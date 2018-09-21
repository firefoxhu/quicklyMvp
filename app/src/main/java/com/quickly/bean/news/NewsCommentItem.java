package com.quickly.bean.news;

import com.google.gson.Gson;

import java.util.List;

public class NewsCommentItem {


    /**
     * code : 0
     * msg : 处理成功！
     * data : {"hasNext":true,"list":[{"id":"14faa060ee354871b1b63a0f029f62ce","commentator":"用户1.196.219.176","content":"走搬砖要紧","createTime":"2018-06-06 15:10:16"},{"id":"27c3123447944383b0ede743ad6790f0","commentator":"用户1.196.219.176","content":"我也是砖家，不说了我取搬砖了","createTime":"2018-06-06 14:58:46"},{"id":"359b2bbb9da74832851990e02c757848","commentator":"用户1.196.219.176","content":"真好啊","createTime":"2018-06-06 16:06:37"},{"id":"4af9b975c05842509e4639f9700ab38a","commentator":"用户1.193.132.181","content":"范德萨发生的发大水范德萨发","createTime":"2018-08-08 15:20:18"},{"id":"4c16097a575d43f495847e06c1c300e4","commentator":"用户123.160.120.75","content":"发送到发送到","createTime":"2018-06-20 00:07:27"},{"id":"627b718e24c94964a2cf00a6d7f43f21","commentator":"用户1.196.219.176","content":"看完后我觉得自己已经是\u201c砖家\u201d了","createTime":"2018-06-06 14:51:31"},{"id":"6ddaab5cd3094cba9befaaca77ae9bde","commentator":"用户1.193.132.181","content":"范德萨发生的","createTime":"2018-08-10 20:21:32"},{"id":"92185a054521422ab60d73f0509d9498","commentator":"用户223.90.115.247","content":"托塔李天王","createTime":"2018-06-08 11:04:27"}]}
     */

    private int code;
    private String msg;
    private DataBean data;

    public static NewsCommentItem objectFromData(String str) {

        return new Gson().fromJson(str, NewsCommentItem.class);
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
         * hasNext : true
         * list : [{"id":"14faa060ee354871b1b63a0f029f62ce","commentator":"用户1.196.219.176","content":"走搬砖要紧","createTime":"2018-06-06 15:10:16"},{"id":"27c3123447944383b0ede743ad6790f0","commentator":"用户1.196.219.176","content":"我也是砖家，不说了我取搬砖了","createTime":"2018-06-06 14:58:46"},{"id":"359b2bbb9da74832851990e02c757848","commentator":"用户1.196.219.176","content":"真好啊","createTime":"2018-06-06 16:06:37"},{"id":"4af9b975c05842509e4639f9700ab38a","commentator":"用户1.193.132.181","content":"范德萨发生的发大水范德萨发","createTime":"2018-08-08 15:20:18"},{"id":"4c16097a575d43f495847e06c1c300e4","commentator":"用户123.160.120.75","content":"发送到发送到","createTime":"2018-06-20 00:07:27"},{"id":"627b718e24c94964a2cf00a6d7f43f21","commentator":"用户1.196.219.176","content":"看完后我觉得自己已经是\u201c砖家\u201d了","createTime":"2018-06-06 14:51:31"},{"id":"6ddaab5cd3094cba9befaaca77ae9bde","commentator":"用户1.193.132.181","content":"范德萨发生的","createTime":"2018-08-10 20:21:32"},{"id":"92185a054521422ab60d73f0509d9498","commentator":"用户223.90.115.247","content":"托塔李天王","createTime":"2018-06-08 11:04:27"}]
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
             * id : 14faa060ee354871b1b63a0f029f62ce
             * commentator : 用户1.196.219.176
             * content : 走搬砖要紧
             * createTime : 2018-06-06 15:10:16
             */

            private String id;
            private String commentator;
            private String content;
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

            public String getCommentator() {
                return commentator;
            }

            public void setCommentator(String commentator) {
                this.commentator = commentator;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
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
