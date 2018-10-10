package com.tangdou.es2.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

@Document(indexName = "creative_index_xi3", type = "creative", shards = 5, replicas = 1, indexStoreType = "fs", refreshInterval = "-1")
public class CreativeIndexTotal implements Serializable {

    private static final Long serialVersionUUID = 1l;
    @Id
    private Long creativeid;
    @Field
    private Integer subscribeid;
    @Field
    private Integer channelid;
    @Field
    private Integer campaignid;
    @Field
    private Long userid;
    @Field
    private Integer provinceid;
    @Field
    private Integer cityid;
    @Field
    private Integer positionid;
    @Field
    private Integer network;
    @Field
    private Integer client;
    @Field
    private Integer frequency;
    @Field
    private Integer crowd;
    @Field
    private Integer priceid;
    @Field(type = FieldType.Keyword)
    private String title;
    @Field(type = FieldType.Keyword)
    private String channelkey;
    //国家级别
    @Field(type = FieldType.Keyword)
    private String region1;
    //省级别
    @Field(type = FieldType.Keyword)
    private String region2;
    //城市级别
    @Field(type = FieldType.Keyword)
    private String region3;
    @Field
    private Date starttime;
    @Field
    private Date endtime;
    @Field
    private Integer adtype;
    @Field
    private Integer bid;
    //creative_info_0
    @Field
    private String describe;
    @Field
    private String targeturl;
    @Field
    private String openurl;    //'第三方app活动页地址'
    @Field
    private String picurl;
    @Field
    private Integer showtime;//'广告展示时长(单位秒）'
    @Field
    private String videourl;
    @Field
    private Integer videoduration;//'视频时长'
    @Field
    private String appinfo;    // 'app下载信息'
    @Field
    private String appid;
    @Field
    private String vid;
    @Field
    private Integer action;   //'0 H5跳转，1 播放， 2 下载， 3 第三方活动页'
    @Field
    private Integer repeat;    //'是否允许重复:0允许,1不允许'
    @Field
    private Integer creativetype;    //'类型，1视频，2图文，3文字, 4图片'
    @Field
    private Integer positiontype;    //'位置类型：1：正常视频位（默认值）,2：导流位-小程序之间互导, 3：广告位-广点通, 4：广告位-代理, 5：广告位-直销 ,6：导流位-小程序内页面间导流,7:小程序投h5广告,8:华夏乐游'
    @Field
    private Integer pictype;
    @Field(type = FieldType.Keyword)
    private Integer localindustry;    //(4) DEFAULT NULL COMMENT '归属行业，对应程序枚举'
    // channel_profile;
    @Field
    private Integer impression;    //'热度，统计用'
    @Field
    private Integer conversion;    //'转化，统计用'
    @Field
    private Integer competition;    //'竞争,统计用'
    @Field(type = FieldType.Keyword)
    private String channelname;
    @Field
    private String channelurl;
    // position;     //'类型，1视频，2图文，3文字, 4图片'
    @Field
    private Double ecpm;


    public Long getCreativeid() {
        return creativeid;
    }

    public void setCreativeid(Long creativeid) {
        this.creativeid = creativeid;
    }

    public Integer getSubscribeid() {
        return subscribeid;
    }

    public void setSubscribeid(Integer subscribeid) {
        this.subscribeid = subscribeid;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
    }

    public Integer getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Integer getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
    }

    public Integer getCityid() {
        return cityid;
    }

    public void setCityid(Integer cityid) {
        this.cityid = cityid;
    }

    public Integer getPositionid() {
        return positionid;
    }

    public void setPositionid(Integer positionid) {
        this.positionid = positionid;
    }

    public Integer getNetwork() {
        return network;
    }

    public void setNetwork(Integer network) {
        this.network = network;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getCrowd() {
        return crowd;
    }

    public void setCrowd(Integer crowd) {
        this.crowd = crowd;
    }

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChannelkey() {
        return channelkey;
    }

    public void setChannelkey(String channelkey) {
        this.channelkey = channelkey;
    }

    public String getRegion1() {
        return region1;
    }

    public void setRegion1(String region1) {
        this.region1 = region1;
    }

    public String getRegion2() {
        return region2;
    }

    public void setRegion2(String region2) {
        this.region2 = region2;
    }

    public String getRegion3() {
        return region3;
    }

    public void setRegion3(String region3) {
        this.region3 = region3;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getAdtype() {
        return adtype;
    }

    public void setAdtype(Integer adtype) {
        this.adtype = adtype;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTargeturl() {
        return targeturl;
    }

    public void setTargeturl(String targeturl) {
        this.targeturl = targeturl;
    }

    public String getOpenurl() {
        return openurl;
    }

    public void setOpenurl(String openurl) {
        this.openurl = openurl;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public Integer getShowtime() {
        return showtime;
    }

    public void setShowtime(Integer showtime) {
        this.showtime = showtime;
    }

    public String getVideourl() {
        return videourl;
    }

    public void setVideourl(String videourl) {
        this.videourl = videourl;
    }

    public Integer getVideoduration() {
        return videoduration;
    }

    public void setVideoduration(Integer videoduration) {
        this.videoduration = videoduration;
    }

    public String getAppinfo() {
        return appinfo;
    }

    public void setAppinfo(String appinfo) {
        this.appinfo = appinfo;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
    }

    public Integer getCreativetype() {
        return creativetype;
    }

    public void setCreativetype(Integer creativetype) {
        this.creativetype = creativetype;
    }

    public Integer getPositiontype() {
        return positiontype;
    }

    public void setPositiontype(Integer positiontype) {
        this.positiontype = positiontype;
    }

    public Integer getPictype() {
        return pictype;
    }

    public void setPictype(Integer pictype) {
        this.pictype = pictype;
    }

    public Integer getLocalindustry() {
        return localindustry;
    }

    public void setLocalindustry(Integer localindustry) {
        this.localindustry = localindustry;
    }

    public Integer getImpression() {
        return impression;
    }

    public void setImpression(Integer impression) {
        this.impression = impression;
    }

    public Integer getConversion() {
        return conversion;
    }

    public void setConversion(Integer conversion) {
        this.conversion = conversion;
    }

    public Integer getCompetition() {
        return competition;
    }

    public void setCompetition(Integer competition) {
        this.competition = competition;
    }

    public String getChannelname() {
        return channelname;
    }

    public void setChannelname(String channelname) {
        this.channelname = channelname;
    }

    public String getChannelurl() {
        return channelurl;
    }

    public void setChannelurl(String channelurl) {
        this.channelurl = channelurl;
    }

    public Double getEcpm() {
        return ecpm;
    }

    public void setEcpm(Double ecpm) {
        this.ecpm = ecpm;
    }
}
