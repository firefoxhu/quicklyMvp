package com.quickly.fragment.circle.article;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User implements Serializable {

    public int age;
    public String name;

    public List<String> pictures = new ArrayList<>();

    public String url = "http://jzvd.nathen.cn/df6096e7878541cbbea3f7298683fbed/ef76450342914427beafe9368a4e0397-5287d2089db37e62345123a1be272f8b.mp4";

    public String thUrl = "http://jzvd-pic.nathen.cn/jzvd-pic/a019ffc1-556c-4a85-b70c-b1b49811d577.jpg";


    public User(int age, String name) {
        this.age = age;
        this.name = name;
        this.pictures.add("http://image.luosen365.com/home_banner_01.jpg");
        this.pictures.add("http://image.luosen365.com/home_banner_02.jpg");
        this.pictures.add("http://image.luosen365.com/home_banner_03.jpg");
        this.pictures.add("http://image.luosen365.com/home_banner_01.jpg");
        this.pictures.add("http://image.luosen365.com/home_banner_01.jpg");
        this.pictures.add("http://image.luosen365.com/home_banner_02.jpg");
        this.pictures.add("http://image.luosen365.com/home_banner_03.jpg");
        this.pictures.add("http://image.luosen365.com/home_banner_01.jpg");

    }
}
