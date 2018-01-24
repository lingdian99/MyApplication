package com.example.zhihuribao.main.utils;

import java.util.List;

/**
 * Created by banker_test on 2017/4/12.
 */

public class SplashUntil {
    private List<CreativesBean> creatives;

    public List<CreativesBean> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<CreativesBean> creatives) {
        this.creatives = creatives;
    }

    public static class CreativesBean {
        /**
         * url : https://pic3.zhimg.com/v2-5af460972557190bd4306ad66f360d4a.jpg
         * start_time : 1491980356
         * impression_tracks : ["https://sugar.zhihu.com/track?vs=1&ai=3838&ut=&cg=2&ts=1491980356.41&si=1ac2f8676f7d4ee4ba21e3a428b9e3f1&lu=0&hn=ad-engine.ad-engine.9e5c8659&at=impression&pf=PC&az=11&sg=f13dc90e7522be7885574f8b74e99f6f"]
         * type : 0
         * id : 3838
         */

        private String url;
        private int start_time;
        private int type;
        private String id;
        private List<String> impression_tracks;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getStart_time() {
            return start_time;
        }

        public void setStart_time(int start_time) {
            this.start_time = start_time;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<String> getImpression_tracks() {
            return impression_tracks;
        }

        public void setImpression_tracks(List<String> impression_tracks) {
            this.impression_tracks = impression_tracks;
        }
    }
}
