package com.example.kaiyan.bean;

import java.util.List;

/**
 * Created by wys10 on 17/12/14.
 */

public class HomepageBean {


    private int count;
    private int total;
    private String nextPageUrl;
    private boolean adExist;
    private long date;
    private long nextPublishTime;
    private Object dialog;
    private Object topIssue;
    private int refreshCount;
    private int lastStartId;
    private List<ItemListBean> itemList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageUrl() {
        return nextPageUrl;
    }

    public void setNextPageUrl(String nextPageUrl) {
        this.nextPageUrl = nextPageUrl;
    }

    public boolean isAdExist() {
        return adExist;
    }

    public void setAdExist(boolean adExist) {
        this.adExist = adExist;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getNextPublishTime() {
        return nextPublishTime;
    }

    public void setNextPublishTime(long nextPublishTime) {
        this.nextPublishTime = nextPublishTime;
    }

    public Object getDialog() {
        return dialog;
    }

    public void setDialog(Object dialog) {
        this.dialog = dialog;
    }

    public Object getTopIssue() {
        return topIssue;
    }

    public void setTopIssue(Object topIssue) {
        this.topIssue = topIssue;
    }

    public int getRefreshCount() {
        return refreshCount;
    }

    public void setRefreshCount(int refreshCount) {
        this.refreshCount = refreshCount;
    }

    public int getLastStartId() {
        return lastStartId;
    }

    public void setLastStartId(int lastStartId) {
        this.lastStartId = lastStartId;
    }

    public List<ItemListBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemListBean> itemList) {
        this.itemList = itemList;
    }

    public static class ItemListBean {

        private String type;
        private DataBean data;
        private String tag;
        private int id;
        private int adIndex;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getAdIndex() {
            return adIndex;
        }

        public void setAdIndex(int adIndex) {
            this.adIndex = adIndex;
        }


        public static class DataBean {

            private String dataType;
            private int id;
            private String title;
            private String slogan;
            private String description;
            private ProviderBean provider;
            private String category;
            private AuthorBean author;
            private CoverBean cover;
            private String playUrl;
            private String thumbPlayUrl;
            private int duration;
            private WebUrlBean webUrl;
            private long releaseTime;
            private String library;
            private ConsumptionBean consumption;
            private Object campaign;
            private Object waterMarks;
            private Object adTrack;
            private String type;
            private String titlePgc;
            private String descriptionPgc;
            private String remark;
            private int idx;
            private Object shareAdTrack;
            private Object favoriteAdTrack;
            private Object webAdTrack;
            private long date;
            private Object promotion;
            private Object label;
            private String descriptionEditor;
            private boolean collected;
            private boolean played;
            private Object lastViewTime;
            private Object playlists;
            private Object src;
            private List<PlayInfoBean> playInfo;
            private List<TagsBean> tags;
            private List<?> labelList;
            private List<?> subtitles;
            //内部itemList
            private List<ItemBannerList> itemList;

            public void setItemList(List<ItemBannerList> itemList) {
                this.itemList = itemList;
            }

            public List<ItemBannerList> getItemList() {
                return itemList;
            }

            public static class ItemBannerList{

                /**
                 * type : video
                 * data : {"dataType":"VideoBeanForClient","id":76959,"title":"手机是如何「改变」你的？","slogan":"是获得欢愉，还是一步步被「吞噬」","description":"当今，手机已变成了人们生活娱乐的必备品。但与此同时，一些由手机引起的问题也随之而来\u2026\u2026","provider":{"name":"YouTube","alias":"youtube","icon":"http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png"},"category":"科普","author":{"id":2539,"icon":"http://img.kaiyanapp.com/619decaa6ba28afddcf7ad47ac78161d.jpeg?imageMogr2/quality/60/format/jpg","name":"AsapSCIENCE","description":"让科普变得更生动有趣～","link":"","latestReleaseTime":1516689859000,"videoNum":1,"adTrack":null,"follow":{"itemType":"author","itemId":2539,"followed":false},"shield":{"itemType":"author","itemId":2539,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true},"cover":{"feed":"http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/3df15c68783345fed2390e1ba0f9efed.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"},"playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=default&source=aliyun","thumbPlayUrl":null,"duration":191,"webUrl":{"raw":"http://www.eyepetizer.net/detail.html?vid=76959","forWeibo":"http://www.eyepetizer.net/detail.html?vid=76959"},"releaseTime":1516689859000,"library":"DEFAULT","playInfo":[{"height":480,"width":854,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=aliyun","size":10126629},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=qcloud","size":10126629},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=ucloud","size":10126629}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=aliyun"},{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=aliyun","size":21146321},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=qcloud","size":21146321},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=ucloud","size":21146321}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=aliyun"}],"consumption":{"collectionCount":256,"shareCount":230,"replyCount":3},"campaign":null,"waterMarks":null,"adTrack":null,"tags":[{"id":556,"name":"科技","actionUrl":"eyepetizer://tag/556/?title=%E7%A7%91%E6%8A%80","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/27c3e861a02ced6069dd64c977746a61.jpeg?imageMogr2/quality/60","headerImage":"http://img.kaiyanapp.com/b8a3443f9ff392b22201b74e47995e3c.jpeg?imageMogr2/quality/100","tagRecType":"NORMAL"},{"id":666,"name":"生活","actionUrl":"eyepetizer://tag/666/?title=%E7%94%9F%E6%B4%BB","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/be945b24182e6a268bdd66f054148f47.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/be945b24182e6a268bdd66f054148f47.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"},{"id":44,"name":"科普","actionUrl":"eyepetizer://tag/44/?title=%E7%A7%91%E6%99%AE","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"}],"type":"NORMAL","titlePgc":null,"descriptionPgc":null,"remark":null,"idx":0,"shareAdTrack":null,"favoriteAdTrack":null,"webAdTrack":null,"date":1516689859000,"promotion":null,"label":null,"labelList":[],"descriptionEditor":"当今，手机已变成了人们生活娱乐的必备品。但与此同时，一些由手机引起的问题也随之而来\u2026\u2026","collected":false,"played":false,"subtitles":[],"lastViewTime":null,"playlists":null,"src":null}
                 * tag : null
                 * id : 0
                 * adIndex : -1
                 */

                private String type;
                private InnerDataBeen data;
                private Object tag;
                private int id;
                private int adIndex;

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public InnerDataBeen getData() {
                    return data;
                }

                public void setData(InnerDataBeen data) {
                    this.data = data;
                }

                public Object getTag() {
                    return tag;
                }

                public void setTag(Object tag) {
                    this.tag = tag;
                }

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getAdIndex() {
                    return adIndex;
                }

                public void setAdIndex(int adIndex) {
                    this.adIndex = adIndex;
                }

                public static class InnerDataBeen {
                    /**
                     * dataType : VideoBeanForClient
                     * id : 76959
                     * title : 手机是如何「改变」你的？
                     * slogan : 是获得欢愉，还是一步步被「吞噬」
                     * description : 当今，手机已变成了人们生活娱乐的必备品。但与此同时，一些由手机引起的问题也随之而来……
                     * provider : {"name":"YouTube","alias":"youtube","icon":"http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png"}
                     * category : 科普
                     * author : {"id":2539,"icon":"http://img.kaiyanapp.com/619decaa6ba28afddcf7ad47ac78161d.jpeg?imageMogr2/quality/60/format/jpg","name":"AsapSCIENCE","description":"让科普变得更生动有趣～","link":"","latestReleaseTime":1516689859000,"videoNum":1,"adTrack":null,"follow":{"itemType":"author","itemId":2539,"followed":false},"shield":{"itemType":"author","itemId":2539,"shielded":false},"approvedNotReadyVideoCount":0,"ifPgc":true}
                     * cover : {"feed":"http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageMogr2/quality/60/format/jpg","detail":"http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageMogr2/quality/60/format/jpg","blurred":"http://img.kaiyanapp.com/3df15c68783345fed2390e1ba0f9efed.jpeg?imageMogr2/quality/60/format/jpg","sharing":null,"homepage":"http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"}
                     * playUrl : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=default&source=aliyun
                     * thumbPlayUrl : null
                     * duration : 191
                     * webUrl : {"raw":"http://www.eyepetizer.net/detail.html?vid=76959","forWeibo":"http://www.eyepetizer.net/detail.html?vid=76959"}
                     * releaseTime : 1516689859000
                     * library : DEFAULT
                     * playInfo : [{"height":480,"width":854,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=aliyun","size":10126629},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=qcloud","size":10126629},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=ucloud","size":10126629}],"name":"标清","type":"normal","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=aliyun"},{"height":720,"width":1280,"urlList":[{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=aliyun","size":21146321},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=qcloud","size":21146321},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=ucloud","size":21146321}],"name":"高清","type":"high","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=high&source=aliyun"}]
                     * consumption : {"collectionCount":256,"shareCount":230,"replyCount":3}
                     * campaign : null
                     * waterMarks : null
                     * adTrack : null
                     * tags : [{"id":556,"name":"科技","actionUrl":"eyepetizer://tag/556/?title=%E7%A7%91%E6%8A%80","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/27c3e861a02ced6069dd64c977746a61.jpeg?imageMogr2/quality/60","headerImage":"http://img.kaiyanapp.com/b8a3443f9ff392b22201b74e47995e3c.jpeg?imageMogr2/quality/100","tagRecType":"NORMAL"},{"id":666,"name":"生活","actionUrl":"eyepetizer://tag/666/?title=%E7%94%9F%E6%B4%BB","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/be945b24182e6a268bdd66f054148f47.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/be945b24182e6a268bdd66f054148f47.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"},{"id":44,"name":"科普","actionUrl":"eyepetizer://tag/44/?title=%E7%A7%91%E6%99%AE","adTrack":null,"desc":null,"bgPicture":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","headerImage":"http://img.kaiyanapp.com/f2e7359e81e217782f32cc3d482b3284.jpeg?imageMogr2/quality/60/format/jpg","tagRecType":"NORMAL"}]
                     * type : NORMAL
                     * titlePgc : null
                     * descriptionPgc : null
                     * remark : null
                     * idx : 0
                     * shareAdTrack : null
                     * favoriteAdTrack : null
                     * webAdTrack : null
                     * date : 1516689859000
                     * promotion : null
                     * label : null
                     * labelList : []
                     * descriptionEditor : 当今，手机已变成了人们生活娱乐的必备品。但与此同时，一些由手机引起的问题也随之而来……
                     * collected : false
                     * played : false
                     * subtitles : []
                     * lastViewTime : null
                     * playlists : null
                     * src : null
                     */

                    private String dataType;
                    private int id;
                    private String title;
                    private String slogan;
                    private String description;
                    private ProviderBean provider;
                    private String category;
                    private AuthorBean author;
                    private CoverBean cover;
                    private String playUrl;
                    private Object thumbPlayUrl;
                    private int duration;
                    private WebUrlBean webUrl;
                    private long releaseTime;
                    private String library;
                    private ConsumptionBean consumption;
                    private Object campaign;
                    private Object waterMarks;
                    private Object adTrack;
                    private String type;
                    private Object titlePgc;
                    private Object descriptionPgc;
                    private Object remark;
                    private int idx;
                    private Object shareAdTrack;
                    private Object favoriteAdTrack;
                    private Object webAdTrack;
                    private long date;
                    private Object promotion;
                    private Object label;
                    private String descriptionEditor;
                    private boolean collected;
                    private boolean played;
                    private Object lastViewTime;
                    private Object playlists;
                    private Object src;
                    private List<PlayInfoBean> playInfo;
                    private List<TagsBean> tags;
                    private List<?> labelList;
                    private List<?> subtitles;

                    public String getDataType() {
                        return dataType;
                    }

                    public void setDataType(String dataType) {
                        this.dataType = dataType;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getTitle() {
                        return title;
                    }

                    public void setTitle(String title) {
                        this.title = title;
                    }

                    public String getSlogan() {
                        return slogan;
                    }

                    public void setSlogan(String slogan) {
                        this.slogan = slogan;
                    }

                    public String getDescription() {
                        return description;
                    }

                    public void setDescription(String description) {
                        this.description = description;
                    }

                    public ProviderBean getProvider() {
                        return provider;
                    }

                    public void setProvider(ProviderBean provider) {
                        this.provider = provider;
                    }

                    public String getCategory() {
                        return category;
                    }

                    public void setCategory(String category) {
                        this.category = category;
                    }

                    public AuthorBean getAuthor() {
                        return author;
                    }

                    public void setAuthor(AuthorBean author) {
                        this.author = author;
                    }

                    public CoverBean getCover() {
                        return cover;
                    }

                    public void setCover(CoverBean cover) {
                        this.cover = cover;
                    }

                    public String getPlayUrl() {
                        return playUrl;
                    }

                    public void setPlayUrl(String playUrl) {
                        this.playUrl = playUrl;
                    }

                    public Object getThumbPlayUrl() {
                        return thumbPlayUrl;
                    }

                    public void setThumbPlayUrl(Object thumbPlayUrl) {
                        this.thumbPlayUrl = thumbPlayUrl;
                    }

                    public int getDuration() {
                        return duration;
                    }

                    public void setDuration(int duration) {
                        this.duration = duration;
                    }

                    public WebUrlBean getWebUrl() {
                        return webUrl;
                    }

                    public void setWebUrl(WebUrlBean webUrl) {
                        this.webUrl = webUrl;
                    }

                    public long getReleaseTime() {
                        return releaseTime;
                    }

                    public void setReleaseTime(long releaseTime) {
                        this.releaseTime = releaseTime;
                    }

                    public String getLibrary() {
                        return library;
                    }

                    public void setLibrary(String library) {
                        this.library = library;
                    }

                    public ConsumptionBean getConsumption() {
                        return consumption;
                    }

                    public void setConsumption(ConsumptionBean consumption) {
                        this.consumption = consumption;
                    }

                    public Object getCampaign() {
                        return campaign;
                    }

                    public void setCampaign(Object campaign) {
                        this.campaign = campaign;
                    }

                    public Object getWaterMarks() {
                        return waterMarks;
                    }

                    public void setWaterMarks(Object waterMarks) {
                        this.waterMarks = waterMarks;
                    }

                    public Object getAdTrack() {
                        return adTrack;
                    }

                    public void setAdTrack(Object adTrack) {
                        this.adTrack = adTrack;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public Object getTitlePgc() {
                        return titlePgc;
                    }

                    public void setTitlePgc(Object titlePgc) {
                        this.titlePgc = titlePgc;
                    }

                    public Object getDescriptionPgc() {
                        return descriptionPgc;
                    }

                    public void setDescriptionPgc(Object descriptionPgc) {
                        this.descriptionPgc = descriptionPgc;
                    }

                    public Object getRemark() {
                        return remark;
                    }

                    public void setRemark(Object remark) {
                        this.remark = remark;
                    }

                    public int getIdx() {
                        return idx;
                    }

                    public void setIdx(int idx) {
                        this.idx = idx;
                    }

                    public Object getShareAdTrack() {
                        return shareAdTrack;
                    }

                    public void setShareAdTrack(Object shareAdTrack) {
                        this.shareAdTrack = shareAdTrack;
                    }

                    public Object getFavoriteAdTrack() {
                        return favoriteAdTrack;
                    }

                    public void setFavoriteAdTrack(Object favoriteAdTrack) {
                        this.favoriteAdTrack = favoriteAdTrack;
                    }

                    public Object getWebAdTrack() {
                        return webAdTrack;
                    }

                    public void setWebAdTrack(Object webAdTrack) {
                        this.webAdTrack = webAdTrack;
                    }

                    public long getDate() {
                        return date;
                    }

                    public void setDate(long date) {
                        this.date = date;
                    }

                    public Object getPromotion() {
                        return promotion;
                    }

                    public void setPromotion(Object promotion) {
                        this.promotion = promotion;
                    }

                    public Object getLabel() {
                        return label;
                    }

                    public void setLabel(Object label) {
                        this.label = label;
                    }

                    public String getDescriptionEditor() {
                        return descriptionEditor;
                    }

                    public void setDescriptionEditor(String descriptionEditor) {
                        this.descriptionEditor = descriptionEditor;
                    }

                    public boolean isCollected() {
                        return collected;
                    }

                    public void setCollected(boolean collected) {
                        this.collected = collected;
                    }

                    public boolean isPlayed() {
                        return played;
                    }

                    public void setPlayed(boolean played) {
                        this.played = played;
                    }

                    public Object getLastViewTime() {
                        return lastViewTime;
                    }

                    public void setLastViewTime(Object lastViewTime) {
                        this.lastViewTime = lastViewTime;
                    }

                    public Object getPlaylists() {
                        return playlists;
                    }

                    public void setPlaylists(Object playlists) {
                        this.playlists = playlists;
                    }

                    public Object getSrc() {
                        return src;
                    }

                    public void setSrc(Object src) {
                        this.src = src;
                    }

                    public List<PlayInfoBean> getPlayInfo() {
                        return playInfo;
                    }

                    public void setPlayInfo(List<PlayInfoBean> playInfo) {
                        this.playInfo = playInfo;
                    }

                    public List<TagsBean> getTags() {
                        return tags;
                    }

                    public void setTags(List<TagsBean> tags) {
                        this.tags = tags;
                    }

                    public List<?> getLabelList() {
                        return labelList;
                    }

                    public void setLabelList(List<?> labelList) {
                        this.labelList = labelList;
                    }

                    public List<?> getSubtitles() {
                        return subtitles;
                    }

                    public void setSubtitles(List<?> subtitles) {
                        this.subtitles = subtitles;
                    }

                    public static class ProviderBean {
                        /**
                         * name : YouTube
                         * alias : youtube
                         * icon : http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                         */

                        private String name;
                        private String alias;
                        private String icon;

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getAlias() {
                            return alias;
                        }

                        public void setAlias(String alias) {
                            this.alias = alias;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }
                    }

                    public static class AuthorBean {
                        /**
                         * id : 2539
                         * icon : http://img.kaiyanapp.com/619decaa6ba28afddcf7ad47ac78161d.jpeg?imageMogr2/quality/60/format/jpg
                         * name : AsapSCIENCE
                         * description : 让科普变得更生动有趣～
                         * link :
                         * latestReleaseTime : 1516689859000
                         * videoNum : 1
                         * adTrack : null
                         * follow : {"itemType":"author","itemId":2539,"followed":false}
                         * shield : {"itemType":"author","itemId":2539,"shielded":false}
                         * approvedNotReadyVideoCount : 0
                         * ifPgc : true
                         */

                        private int id;
                        private String icon;
                        private String name;
                        private String description;
                        private String link;
                        private long latestReleaseTime;
                        private int videoNum;
                        private Object adTrack;
                        private FollowBean follow;
                        private ShieldBean shield;
                        private int approvedNotReadyVideoCount;
                        private boolean ifPgc;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getIcon() {
                            return icon;
                        }

                        public void setIcon(String icon) {
                            this.icon = icon;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getDescription() {
                            return description;
                        }

                        public void setDescription(String description) {
                            this.description = description;
                        }

                        public String getLink() {
                            return link;
                        }

                        public void setLink(String link) {
                            this.link = link;
                        }

                        public long getLatestReleaseTime() {
                            return latestReleaseTime;
                        }

                        public void setLatestReleaseTime(long latestReleaseTime) {
                            this.latestReleaseTime = latestReleaseTime;
                        }

                        public int getVideoNum() {
                            return videoNum;
                        }

                        public void setVideoNum(int videoNum) {
                            this.videoNum = videoNum;
                        }

                        public Object getAdTrack() {
                            return adTrack;
                        }

                        public void setAdTrack(Object adTrack) {
                            this.adTrack = adTrack;
                        }

                        public FollowBean getFollow() {
                            return follow;
                        }

                        public void setFollow(FollowBean follow) {
                            this.follow = follow;
                        }

                        public ShieldBean getShield() {
                            return shield;
                        }

                        public void setShield(ShieldBean shield) {
                            this.shield = shield;
                        }

                        public int getApprovedNotReadyVideoCount() {
                            return approvedNotReadyVideoCount;
                        }

                        public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                            this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                        }

                        public boolean isIfPgc() {
                            return ifPgc;
                        }

                        public void setIfPgc(boolean ifPgc) {
                            this.ifPgc = ifPgc;
                        }

                        public static class FollowBean {
                            /**
                             * itemType : author
                             * itemId : 2539
                             * followed : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean followed;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isFollowed() {
                                return followed;
                            }

                            public void setFollowed(boolean followed) {
                                this.followed = followed;
                            }
                        }

                        public static class ShieldBean {
                            /**
                             * itemType : author
                             * itemId : 2539
                             * shielded : false
                             */

                            private String itemType;
                            private int itemId;
                            private boolean shielded;

                            public String getItemType() {
                                return itemType;
                            }

                            public void setItemType(String itemType) {
                                this.itemType = itemType;
                            }

                            public int getItemId() {
                                return itemId;
                            }

                            public void setItemId(int itemId) {
                                this.itemId = itemId;
                            }

                            public boolean isShielded() {
                                return shielded;
                            }

                            public void setShielded(boolean shielded) {
                                this.shielded = shielded;
                            }
                        }
                    }

                    public static class CoverBean {
                        /**
                         * feed : http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageMogr2/quality/60/format/jpg
                         * detail : http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageMogr2/quality/60/format/jpg
                         * blurred : http://img.kaiyanapp.com/3df15c68783345fed2390e1ba0f9efed.jpeg?imageMogr2/quality/60/format/jpg
                         * sharing : null
                         * homepage : http://img.kaiyanapp.com/8e351822a75ce62dbf9d532373e394fa.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                         */

                        private String feed;
                        private String detail;
                        private String blurred;
                        private Object sharing;
                        private String homepage;

                        public String getFeed() {
                            return feed;
                        }

                        public void setFeed(String feed) {
                            this.feed = feed;
                        }

                        public String getDetail() {
                            return detail;
                        }

                        public void setDetail(String detail) {
                            this.detail = detail;
                        }

                        public String getBlurred() {
                            return blurred;
                        }

                        public void setBlurred(String blurred) {
                            this.blurred = blurred;
                        }

                        public Object getSharing() {
                            return sharing;
                        }

                        public void setSharing(Object sharing) {
                            this.sharing = sharing;
                        }

                        public String getHomepage() {
                            return homepage;
                        }

                        public void setHomepage(String homepage) {
                            this.homepage = homepage;
                        }
                    }

                    public static class WebUrlBean {
                        /**
                         * raw : http://www.eyepetizer.net/detail.html?vid=76959
                         * forWeibo : http://www.eyepetizer.net/detail.html?vid=76959
                         */

                        private String raw;
                        private String forWeibo;

                        public String getRaw() {
                            return raw;
                        }

                        public void setRaw(String raw) {
                            this.raw = raw;
                        }

                        public String getForWeibo() {
                            return forWeibo;
                        }

                        public void setForWeibo(String forWeibo) {
                            this.forWeibo = forWeibo;
                        }
                    }

                    public static class ConsumptionBean {
                        /**
                         * collectionCount : 256
                         * shareCount : 230
                         * replyCount : 3
                         */

                        private int collectionCount;
                        private int shareCount;
                        private int replyCount;

                        public int getCollectionCount() {
                            return collectionCount;
                        }

                        public void setCollectionCount(int collectionCount) {
                            this.collectionCount = collectionCount;
                        }

                        public int getShareCount() {
                            return shareCount;
                        }

                        public void setShareCount(int shareCount) {
                            this.shareCount = shareCount;
                        }

                        public int getReplyCount() {
                            return replyCount;
                        }

                        public void setReplyCount(int replyCount) {
                            this.replyCount = replyCount;
                        }
                    }

                    public static class PlayInfoBean {
                        /**
                         * height : 480
                         * width : 854
                         * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=aliyun","size":10126629},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=qcloud","size":10126629},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=ucloud","size":10126629}]
                         * name : 标清
                         * type : normal
                         * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=aliyun
                         */

                        private int height;
                        private int width;
                        private String name;
                        private String type;
                        private String url;
                        private List<UrlListBean> urlList;

                        public int getHeight() {
                            return height;
                        }

                        public void setHeight(int height) {
                            this.height = height;
                        }

                        public int getWidth() {
                            return width;
                        }

                        public void setWidth(int width) {
                            this.width = width;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getUrl() {
                            return url;
                        }

                        public void setUrl(String url) {
                            this.url = url;
                        }

                        public List<UrlListBean> getUrlList() {
                            return urlList;
                        }

                        public void setUrlList(List<UrlListBean> urlList) {
                            this.urlList = urlList;
                        }

                        public static class UrlListBean {
                            /**
                             * name : aliyun
                             * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=76959&editionType=normal&source=aliyun
                             * size : 10126629
                             */

                            private String name;
                            private String url;
                            private int size;

                            public String getName() {
                                return name;
                            }

                            public void setName(String name) {
                                this.name = name;
                            }

                            public String getUrl() {
                                return url;
                            }

                            public void setUrl(String url) {
                                this.url = url;
                            }

                            public int getSize() {
                                return size;
                            }

                            public void setSize(int size) {
                                this.size = size;
                            }
                        }
                    }

                    public static class TagsBean {
                        /**
                         * id : 556
                         * name : 科技
                         * actionUrl : eyepetizer://tag/556/?title=%E7%A7%91%E6%8A%80
                         * adTrack : null
                         * desc : null
                         * bgPicture : http://img.kaiyanapp.com/27c3e861a02ced6069dd64c977746a61.jpeg?imageMogr2/quality/60
                         * headerImage : http://img.kaiyanapp.com/b8a3443f9ff392b22201b74e47995e3c.jpeg?imageMogr2/quality/100
                         * tagRecType : NORMAL
                         */

                        private int id;
                        private String name;
                        private String actionUrl;
                        private Object adTrack;
                        private Object desc;
                        private String bgPicture;
                        private String headerImage;
                        private String tagRecType;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getName() {
                            return name;
                        }

                        public void setName(String name) {
                            this.name = name;
                        }

                        public String getActionUrl() {
                            return actionUrl;
                        }

                        public void setActionUrl(String actionUrl) {
                            this.actionUrl = actionUrl;
                        }

                        public Object getAdTrack() {
                            return adTrack;
                        }

                        public void setAdTrack(Object adTrack) {
                            this.adTrack = adTrack;
                        }

                        public Object getDesc() {
                            return desc;
                        }

                        public void setDesc(Object desc) {
                            this.desc = desc;
                        }

                        public String getBgPicture() {
                            return bgPicture;
                        }

                        public void setBgPicture(String bgPicture) {
                            this.bgPicture = bgPicture;
                        }

                        public String getHeaderImage() {
                            return headerImage;
                        }

                        public void setHeaderImage(String headerImage) {
                            this.headerImage = headerImage;
                        }

                        public String getTagRecType() {
                            return tagRecType;
                        }

                        public void setTagRecType(String tagRecType) {
                            this.tagRecType = tagRecType;
                        }
                    }
                }
            }



            public String getDataType() {
                return dataType;
            }

            public void setDataType(String dataType) {
                this.dataType = dataType;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getSlogan() {
                return slogan;
            }

            public void setSlogan(String slogan) {
                this.slogan = slogan;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public ProviderBean getProvider() {
                return provider;
            }

            public void setProvider(ProviderBean provider) {
                this.provider = provider;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public AuthorBean getAuthor() {
                return author;
            }

            public void setAuthor(AuthorBean author) {
                this.author = author;
            }

            public CoverBean getCover() {
                return cover;
            }

            public void setCover(CoverBean cover) {
                this.cover = cover;
            }

            public String getPlayUrl() {
                return playUrl;
            }

            public void setPlayUrl(String playUrl) {
                this.playUrl = playUrl;
            }

            public String getThumbPlayUrl() {
                return thumbPlayUrl;
            }

            public void setThumbPlayUrl(String thumbPlayUrl) {
                this.thumbPlayUrl = thumbPlayUrl;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public WebUrlBean getWebUrl() {
                return webUrl;
            }

            public void setWebUrl(WebUrlBean webUrl) {
                this.webUrl = webUrl;
            }

            public long getReleaseTime() {
                return releaseTime;
            }

            public void setReleaseTime(long releaseTime) {
                this.releaseTime = releaseTime;
            }

            public String getLibrary() {
                return library;
            }

            public void setLibrary(String library) {
                this.library = library;
            }

            public ConsumptionBean getConsumption() {
                return consumption;
            }

            public void setConsumption(ConsumptionBean consumption) {
                this.consumption = consumption;
            }

            public Object getCampaign() {
                return campaign;
            }

            public void setCampaign(Object campaign) {
                this.campaign = campaign;
            }

            public Object getWaterMarks() {
                return waterMarks;
            }

            public void setWaterMarks(Object waterMarks) {
                this.waterMarks = waterMarks;
            }

            public Object getAdTrack() {
                return adTrack;
            }

            public void setAdTrack(Object adTrack) {
                this.adTrack = adTrack;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTitlePgc() {
                return titlePgc;
            }

            public void setTitlePgc(String titlePgc) {
                this.titlePgc = titlePgc;
            }

            public String getDescriptionPgc() {
                return descriptionPgc;
            }

            public void setDescriptionPgc(String descriptionPgc) {
                this.descriptionPgc = descriptionPgc;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public int getIdx() {
                return idx;
            }

            public void setIdx(int idx) {
                this.idx = idx;
            }

            public Object getShareAdTrack() {
                return shareAdTrack;
            }

            public void setShareAdTrack(Object shareAdTrack) {
                this.shareAdTrack = shareAdTrack;
            }

            public Object getFavoriteAdTrack() {
                return favoriteAdTrack;
            }

            public void setFavoriteAdTrack(Object favoriteAdTrack) {
                this.favoriteAdTrack = favoriteAdTrack;
            }

            public Object getWebAdTrack() {
                return webAdTrack;
            }

            public void setWebAdTrack(Object webAdTrack) {
                this.webAdTrack = webAdTrack;
            }

            public long getDate() {
                return date;
            }

            public void setDate(long date) {
                this.date = date;
            }

            public Object getPromotion() {
                return promotion;
            }

            public void setPromotion(Object promotion) {
                this.promotion = promotion;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(Object label) {
                this.label = label;
            }

            public String getDescriptionEditor() {
                return descriptionEditor;
            }

            public void setDescriptionEditor(String descriptionEditor) {
                this.descriptionEditor = descriptionEditor;
            }

            public boolean isCollected() {
                return collected;
            }

            public void setCollected(boolean collected) {
                this.collected = collected;
            }

            public boolean isPlayed() {
                return played;
            }

            public void setPlayed(boolean played) {
                this.played = played;
            }

            public Object getLastViewTime() {
                return lastViewTime;
            }

            public void setLastViewTime(Object lastViewTime) {
                this.lastViewTime = lastViewTime;
            }

            public Object getPlaylists() {
                return playlists;
            }

            public void setPlaylists(Object playlists) {
                this.playlists = playlists;
            }

            public Object getSrc() {
                return src;
            }

            public void setSrc(Object src) {
                this.src = src;
            }

            public List<PlayInfoBean> getPlayInfo() {
                return playInfo;
            }

            public void setPlayInfo(List<PlayInfoBean> playInfo) {
                this.playInfo = playInfo;
            }

            public List<TagsBean> getTags() {
                return tags;
            }

            public void setTags(List<TagsBean> tags) {
                this.tags = tags;
            }

            public List<?> getLabelList() {
                return labelList;
            }

            public void setLabelList(List<?> labelList) {
                this.labelList = labelList;
            }

            public List<?> getSubtitles() {
                return subtitles;
            }

            public void setSubtitles(List<?> subtitles) {
                this.subtitles = subtitles;
            }

            public static class ProviderBean {
                /**
                 * name : YouTube
                 * alias : youtube
                 * icon : http://img.kaiyanapp.com/fa20228bc5b921e837156923a58713f6.png
                 */

                private String name;
                private String alias;
                private String icon;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getAlias() {
                    return alias;
                }

                public void setAlias(String alias) {
                    this.alias = alias;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }
            }

            public static class AuthorBean {
                /**
                 * id : 2170
                 * icon : http://img.kaiyanapp.com/482c741c06644f5566c7218096dbaf26.jpeg
                 * name : 开眼动画精选
                 * description : 有趣的人永远不缺童心
                 * link :
                 * latestReleaseTime : 1516582800000
                 * videoNum : 548
                 * adTrack : null
                 * follow : {"itemType":"author","itemId":2170,"followed":false}
                 * shield : {"itemType":"author","itemId":2170,"shielded":false}
                 * approvedNotReadyVideoCount : 0
                 * ifPgc : true
                 */

                private int id;
                private String icon;
                private String name;
                private String description;
                private String link;
                private long latestReleaseTime;
                private int videoNum;
                private Object adTrack;
                private FollowBean follow;
                private ShieldBean shield;
                private int approvedNotReadyVideoCount;
                private boolean ifPgc;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getIcon() {
                    return icon;
                }

                public void setIcon(String icon) {
                    this.icon = icon;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getDescription() {
                    return description;
                }

                public void setDescription(String description) {
                    this.description = description;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public long getLatestReleaseTime() {
                    return latestReleaseTime;
                }

                public void setLatestReleaseTime(long latestReleaseTime) {
                    this.latestReleaseTime = latestReleaseTime;
                }

                public int getVideoNum() {
                    return videoNum;
                }

                public void setVideoNum(int videoNum) {
                    this.videoNum = videoNum;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public FollowBean getFollow() {
                    return follow;
                }

                public void setFollow(FollowBean follow) {
                    this.follow = follow;
                }

                public ShieldBean getShield() {
                    return shield;
                }

                public void setShield(ShieldBean shield) {
                    this.shield = shield;
                }

                public int getApprovedNotReadyVideoCount() {
                    return approvedNotReadyVideoCount;
                }

                public void setApprovedNotReadyVideoCount(int approvedNotReadyVideoCount) {
                    this.approvedNotReadyVideoCount = approvedNotReadyVideoCount;
                }

                public boolean isIfPgc() {
                    return ifPgc;
                }

                public void setIfPgc(boolean ifPgc) {
                    this.ifPgc = ifPgc;
                }

                public static class FollowBean {
                    /**
                     * itemType : author
                     * itemId : 2170
                     * followed : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean followed;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isFollowed() {
                        return followed;
                    }

                    public void setFollowed(boolean followed) {
                        this.followed = followed;
                    }
                }

                public static class ShieldBean {
                    /**
                     * itemType : author
                     * itemId : 2170
                     * shielded : false
                     */

                    private String itemType;
                    private int itemId;
                    private boolean shielded;

                    public String getItemType() {
                        return itemType;
                    }

                    public void setItemType(String itemType) {
                        this.itemType = itemType;
                    }

                    public int getItemId() {
                        return itemId;
                    }

                    public void setItemId(int itemId) {
                        this.itemId = itemId;
                    }

                    public boolean isShielded() {
                        return shielded;
                    }

                    public void setShielded(boolean shielded) {
                        this.shielded = shielded;
                    }
                }
            }

            public static class CoverBean {
                /**
                 * feed : http://img.kaiyanapp.com/dfe1091f99d0a643d1905afb460bbb1d.jpeg?imageMogr2/quality/60/format/jpg
                 * detail : http://img.kaiyanapp.com/dfe1091f99d0a643d1905afb460bbb1d.jpeg?imageMogr2/quality/60/format/jpg
                 * blurred : http://img.kaiyanapp.com/4f9c6b8518fb1773d1d2fd6e5d2d1fd6.jpeg?imageMogr2/quality/60/format/jpg
                 * sharing : null
                 * homepage : http://img.kaiyanapp.com/dfe1091f99d0a643d1905afb460bbb1d.jpeg?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim
                 */

                private String feed;
                private String detail;
                private String blurred;
                private Object sharing;
                private String homepage;

                public String getFeed() {
                    return feed;
                }

                public void setFeed(String feed) {
                    this.feed = feed;
                }

                public String getDetail() {
                    return detail;
                }

                public void setDetail(String detail) {
                    this.detail = detail;
                }

                public String getBlurred() {
                    return blurred;
                }

                public void setBlurred(String blurred) {
                    this.blurred = blurred;
                }

                public Object getSharing() {
                    return sharing;
                }

                public void setSharing(Object sharing) {
                    this.sharing = sharing;
                }

                public String getHomepage() {
                    return homepage;
                }

                public void setHomepage(String homepage) {
                    this.homepage = homepage;
                }
            }

            public static class WebUrlBean {
                /**
                 * raw : http://www.eyepetizer.net/detail.html?vid=33205
                 * forWeibo : http://wandou.im/3nphw1
                 */

                private String raw;
                private String forWeibo;

                public String getRaw() {
                    return raw;
                }

                public void setRaw(String raw) {
                    this.raw = raw;
                }

                public String getForWeibo() {
                    return forWeibo;
                }

                public void setForWeibo(String forWeibo) {
                    this.forWeibo = forWeibo;
                }
            }

            public static class ConsumptionBean {
                /**
                 * collectionCount : 841
                 * shareCount : 391
                 * replyCount : 16
                 */

                private int collectionCount;
                private int shareCount;
                private int replyCount;

                public int getCollectionCount() {
                    return collectionCount;
                }

                public void setCollectionCount(int collectionCount) {
                    this.collectionCount = collectionCount;
                }

                public int getShareCount() {
                    return shareCount;
                }

                public void setShareCount(int shareCount) {
                    this.shareCount = shareCount;
                }

                public int getReplyCount() {
                    return replyCount;
                }

                public void setReplyCount(int replyCount) {
                    this.replyCount = replyCount;
                }
            }

            public static class PlayInfoBean {
                /**
                 * height : 480
                 * width : 854
                 * urlList : [{"name":"aliyun","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=33205&editionType=normal&source=aliyun","size":4457241},{"name":"qcloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=33205&editionType=normal&source=qcloud","size":4457241},{"name":"ucloud","url":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=33205&editionType=normal&source=ucloud","size":4457241}]
                 * name : 标清
                 * type : normal
                 * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=33205&editionType=normal&source=aliyun
                 */

                private int height;
                private int width;
                private String name;
                private String type;
                private String url;
                private List<UrlListBean> urlList;

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public List<UrlListBean> getUrlList() {
                    return urlList;
                }

                public void setUrlList(List<UrlListBean> urlList) {
                    this.urlList = urlList;
                }

                public static class UrlListBean {
                    /**
                     * name : aliyun
                     * url : http://baobab.kaiyanapp.com/api/v1/playUrl?vid=33205&editionType=normal&source=aliyun
                     * size : 4457241
                     */

                    private String name;
                    private String url;
                    private int size;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getUrl() {
                        return url;
                    }

                    public void setUrl(String url) {
                        this.url = url;
                    }

                    public int getSize() {
                        return size;
                    }

                    public void setSize(int size) {
                        this.size = size;
                    }
                }
            }

            public static class TagsBean {
                /**
                 * id : 108
                 * name : 爱情
                 * actionUrl : eyepetizer://tag/108/?title=%E7%88%B1%E6%83%85
                 * adTrack : null
                 * desc : null
                 * bgPicture : http://img.kaiyanapp.com/67025fb5bf4f8ff4ccd63cdcfd0cd6c6.jpeg?imageMogr2/quality/60/format/jpg
                 * headerImage : http://img.kaiyanapp.com/67025fb5bf4f8ff4ccd63cdcfd0cd6c6.jpeg?imageMogr2/quality/60/format/jpg
                 * tagRecType : IMPORTANT
                 */

                private int id;
                private String name;
                private String actionUrl;
                private Object adTrack;
                private Object desc;
                private String bgPicture;
                private String headerImage;
                private String tagRecType;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getActionUrl() {
                    return actionUrl;
                }

                public void setActionUrl(String actionUrl) {
                    this.actionUrl = actionUrl;
                }

                public Object getAdTrack() {
                    return adTrack;
                }

                public void setAdTrack(Object adTrack) {
                    this.adTrack = adTrack;
                }

                public Object getDesc() {
                    return desc;
                }

                public void setDesc(Object desc) {
                    this.desc = desc;
                }

                public String getBgPicture() {
                    return bgPicture;
                }

                public void setBgPicture(String bgPicture) {
                    this.bgPicture = bgPicture;
                }

                public String getHeaderImage() {
                    return headerImage;
                }

                public void setHeaderImage(String headerImage) {
                    this.headerImage = headerImage;
                }

                public String getTagRecType() {
                    return tagRecType;
                }

                public void setTagRecType(String tagRecType) {
                    this.tagRecType = tagRecType;
                }
            }
        }
    }
}
