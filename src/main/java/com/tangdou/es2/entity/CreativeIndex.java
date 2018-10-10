package com.tangdou.es2.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

@Document(indexName = "creative_index", type = "creative", shards = 5, replicas = 1, indexStoreType = "fs", refreshInterval = "-1")
public class CreativeIndex implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Integer id;
    @Field
    private Integer position_id;
    @Field
    private String creative_id;
    @Field
    private Integer subscribe_id;
    @Field
    private Integer channel_id;
    @Field
    private Integer campaign_id;
    @Field
    private Long user_id;
    @Field
    private String title;
    @Field
    private String describe;
    @Field
    private String target_url;
    @Field
    private String open_url;
    @Field
    private String pic_url;
    @Field
    private Integer show_time;
    @Field
    private String video_url;
    @Field
    private Integer video_duration;
    @Field
    private String appinfo;
    @Field
    private String appid;
    @Field
    private String vid;
    @Field
    private Integer action;
    @Field
    private Integer state;
    @Field
    private Integer ad_type;
    @Field
    private Integer creative_type;
    @Field
    private Integer position_type;
    @Field
    private Integer local_industry;
    @Field
    private String refuse_reason;
    @Field
    private Integer country_id;
    @Field
    private Integer city_id;
    @Field
    private Integer province_id;


    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCreative_id() {
        return creative_id;
    }

    public void setCreative_id(String creative_id) {
        this.creative_id = creative_id;
    }

    public Integer getSubscribe_id() {
        return subscribe_id;
    }

    public void setSubscribe_id(Integer subscribe_id) {
        this.subscribe_id = subscribe_id;
    }

    public Integer getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Integer channel_id) {
        this.channel_id = channel_id;
    }

    public Integer getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(Integer campaign_id) {
        this.campaign_id = campaign_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTarget_url() {
        return target_url;
    }

    public void setTarget_url(String target_url) {
        this.target_url = target_url;
    }

    public String getOpen_url() {
        return open_url;
    }

    public void setOpen_url(String open_url) {
        this.open_url = open_url;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public Integer getShow_time() {
        return show_time;
    }

    public void setShow_time(Integer show_time) {
        this.show_time = show_time;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public Integer getVideo_duration() {
        return video_duration;
    }

    public void setVideo_duration(Integer video_duration) {
        this.video_duration = video_duration;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getAd_type() {
        return ad_type;
    }

    public void setAd_type(Integer ad_type) {
        this.ad_type = ad_type;
    }

    public Integer getCreative_type() {
        return creative_type;
    }

    public void setCreative_type(Integer creative_type) {
        this.creative_type = creative_type;
    }

    public Integer getPosition_type() {
        return position_type;
    }

    public void setPosition_type(Integer position_type) {
        this.position_type = position_type;
    }

    public Integer getLocal_industry() {
        return local_industry;
    }

    public void setLocal_industry(Integer local_industry) {
        this.local_industry = local_industry;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    public Integer getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getProvince_id() {
        return province_id;
    }

    public void setProvince_id(Integer province_id) {
        this.province_id = province_id;
    }
}
