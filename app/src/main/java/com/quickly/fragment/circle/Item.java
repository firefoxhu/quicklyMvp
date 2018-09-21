package com.quickly.fragment.circle;
import android.support.v4.app.Fragment;

public enum Item {
        CIRCLE_PICTURE("图文", ArticlePanel.class),
        CIRCLE_VIDEO("视频", VideoPanel.class)
        ;
        public String name;

        public Class<? extends Fragment> clazz;

        Item(String name, Class<? extends Fragment> clazz) {
            this.name = name;
            this.clazz = clazz;
        }
}