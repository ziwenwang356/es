package com.tangdou.es2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "creative_info_0")
public class CreativeInfo implements Serializable {

    @Id
    @Column(name = "creative_id")
    private Long creativeid;
    @Column(name = "subscribe_id")
    private Integer subscribeid;
    @Column(name = "channel_id")
    private Integer channelid;
    @Column(name = "campaign_id")
    private Integer campaignid;
    private Long user_id;
    private String title;
    private String describe;
    private String target_url;
    private String open_url;
    private String pic_url;
    private Integer show_time;
    private String video_url;
    private Integer video_duration;
    private String appinfo;
    private String appid;
    private String vid;
    private Integer action;
    private Integer state;
    private Integer repeat;
    private Integer ad_type;
    private Integer creative_type;
    private Integer position_type;
    private Integer pic_type;
    private Integer local_industry;
    private String refuse_reason;
    private String mod_info;
    private Date add_time;
    private String add_user_id;
    private Date mod_time;
    private String mod_user_id;

    public Long getCreative_id() {
        return creativeid;
    }

    public void setCreative_id(Long creative_id) {
        this.creativeid = creative_id;
    }

    public Integer getSubscribe_id() {
        return subscribeid;
    }

    public void setSubscribe_id(Integer subscribe_id) {
        this.subscribeid = subscribe_id;
    }

    public Integer getChannel_id() {
        return channelid;
    }

    public void setChannel_id(Integer channel_id) {
        this.channelid = channel_id;
    }

    public Integer getCampaign_id() {
        return campaignid;
    }

    public void setCampaign_id(Integer campaign_id) {
        this.campaignid = campaign_id;
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

    public Integer getRepeat() {
        return repeat;
    }

    public void setRepeat(Integer repeat) {
        this.repeat = repeat;
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

    public Integer getPic_type() {
        return pic_type;
    }

    public void setPic_type(Integer pic_type) {
        this.pic_type = pic_type;
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

    public String getMod_info() {
        return mod_info;
    }

    public void setMod_info(String mod_info) {
        this.mod_info = mod_info;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public String getAdd_user_id() {
        return add_user_id;
    }

    public void setAdd_user_id(String add_user_id) {
        this.add_user_id = add_user_id;
    }

    public Date getMod_time() {
        return mod_time;
    }

    public void setMod_time(Date mod_time) {
        this.mod_time = mod_time;
    }

    public String getMod_user_id() {
        return mod_user_id;
    }

    public void setMod_user_id(String mod_user_id) {
        this.mod_user_id = mod_user_id;
    }

}