package com.example.zhihuribao.main.utils;

import java.io.Serializable;
import java.util.List;

/**
 * Created by banker_test on 2017/4/26.
 */

public class TodayNewsList implements Serializable{

    /**
     * date : 20170909
     * stories : [{"images":["https://pic1.zhimg.com/v2-6bfb82150a7748490c1a374973946340.jpg"],"type":0,"id":9603167,"ga_prefix":"090914","title":"「你文采很好。」这句话到底有没有语病？"},{"images":["https://pic3.zhimg.com/v2-11934552b8f593a93f9ffa8fedaca722.jpg"],"type":0,"id":9608246,"ga_prefix":"090912","title":"大误 · 那个人，好像一条狗啊"},{"images":["https://pic3.zhimg.com/v2-331386254b0f3fa95eac7353e5a6c78e.jpg"],"type":0,"id":9601491,"ga_prefix":"090911","title":"- 我又不是老板，不用战略思维\r\n- 那你就永远当不了老板"},{"images":["https://pic2.zhimg.com/v2-f0e6b1182f6b4ca0e05e342f12e79d6d.jpg"],"type":0,"id":9607980,"ga_prefix":"090910","title":"迷失，情色，恐惧，分裂\u2026\u2026在它的面前，一切都成倍放大"},{"images":["https://pic4.zhimg.com/v2-f5f6c7abfb813888b34379992671d8df.jpg"],"type":0,"id":9601422,"ga_prefix":"090909","title":"「睡着了都在学习」，这可不光是学霸独有的本事"},{"images":["https://pic2.zhimg.com/v2-1d62a8a2288a2c2e63ed5d5f642a0acd.jpg"],"type":0,"id":9601650,"ga_prefix":"090908","title":"会不会故意修不好，还偷偷加我钱\u2026\u2026不只是你，修理工也在为这事发愁"},{"images":["https://pic1.zhimg.com/v2-ff6e5c51f36d467086a57d3713eb37ec.jpg"],"type":0,"id":9607602,"ga_prefix":"090907","title":"生活中哪些意想不到的事，其实是由基因决定的？"},{"images":["https://pic1.zhimg.com/v2-62d61fefaa5a2aa427cebe3c128efb48.jpg"],"type":0,"id":9608088,"ga_prefix":"090907","title":"少年，我这有一份「吃鸡」宝典，你要看吗？"},{"images":["https://pic4.zhimg.com/v2-90687d286540224b09a9797a2022165b.jpg"],"type":0,"id":9605208,"ga_prefix":"090907","title":"已婚男女可以和伴侣之外的异性单独吃饭吗？"},{"images":["https://pic3.zhimg.com/v2-e7dde958a0a0db851408f7ddd7d99342.jpg"],"type":0,"id":9605226,"ga_prefix":"090906","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic2.zhimg.com/v2-9f8319941185ab791c11c93dc6682195.jpg","type":0,"id":9608088,"ga_prefix":"090907","title":"少年，我这有一份「吃鸡」宝典，你要看吗？"},{"image":"https://pic1.zhimg.com/v2-14ee9f8f7bbaea667f2283ee4c95bb14.jpg","type":0,"id":9605208,"ga_prefix":"090907","title":"已婚男女可以和伴侣之外的异性单独吃饭吗？"},{"image":"https://pic4.zhimg.com/v2-00634d2f63fc750276670ba7b46a5ca7.jpg","type":0,"id":9608131,"ga_prefix":"090818","title":"刚刚经历了墨西哥 8.2 级地震，大家都很镇定，看来要正常上班"},{"image":"https://pic3.zhimg.com/v2-14c13f9f87b1b3082929b444072eebb6.jpg","type":0,"id":9607829,"ga_prefix":"090815","title":"看照片，我就知道你是同性恋，斯坦福大学的人工智能说"},{"image":"https://pic1.zhimg.com/v2-cceffc2e17185ae51b7b2d14b4414e84.jpg","type":0,"id":9606837,"ga_prefix":"090807","title":"我这么胖，到底是因为吃得太多还是动得太少？"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }


    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-6bfb82150a7748490c1a374973946340.jpg"]
         * type : 0
         * id : 9603167
         * ga_prefix : 090914
         * title : 「你文采很好。」这句话到底有没有语病？
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic2.zhimg.com/v2-9f8319941185ab791c11c93dc6682195.jpg
         * type : 0
         * id : 9608088
         * ga_prefix : 090907
         * title : 少年，我这有一份「吃鸡」宝典，你要看吗？
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
