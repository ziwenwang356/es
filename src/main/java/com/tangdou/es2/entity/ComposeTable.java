package com.tangdou.es2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "compose_table")
public class ComposeTable implements Serializable {

    private static final Long serialVersionUUID = 1l;
    @Id
    private Long creativeid;
    private Integer channelid;
    private Integer provinceid;
    private Integer cityid;
    private Integer positionid;
    private Integer network;
    private Integer client;
    private Integer frequency;
    private Integer crowd;
    private Integer type;
    private Integer priceid;
    private Integer subscribeid;
    private String creativetitle;
    private String channelkey;
    private String provincename;
    private String cityname;
    private String positionname;
    private Integer campaignid;
    private String campaignname;
    private Long userid;
    private Date starttime;
    private Date endtime;
    private Integer budget;
    private Integer budgettype;
    private Integer budgetover;
    private Integer campaignstate;
    private Integer adtype;
    private String adduserid;
    private String moduserid;
    private Integer bid;
    private Integer subscribestate;
    private String describe;
    private String targeturl;
    private String openurl;
    private String picurl;
    private Integer showtime;
    private String videourl;
    private Integer videoduration;
    private String appinfo;
    private String appid;
    private String vid;
    private Integer action;
    private Integer creativestate;
    private Integer repeat;
    private Integer creativetype;
    private Integer positiontype;
    private Integer pictype;
    private Integer localindustry;
    private String refusereason;
    private String modinfo;
    private Integer channelstate;
    private Integer impression;
    private Integer conversion;
    private Integer competition;
    private String channelname;
    private String channelurl;
    private String pagename;
    private String pagedescription;
    private String pageurl;
    private String positiondescription;
    private String demourl;
    private Integer cycle;
    private String regular;
    private Integer positionclient;
    private String version;
    private String code;
    private String js_code;
    private Integer positionstate;


    public Long getCreativeid() {
        return creativeid;
    }

    public void setCreativeid(Long creativeid) {
        this.creativeid = creativeid;
    }

    public Integer getChannelid() {
        return channelid;
    }

    public void setChannelid(Integer channelid) {
        this.channelid = channelid;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPriceid() {
        return priceid;
    }

    public void setPriceid(Integer priceid) {
        this.priceid = priceid;
    }

    public Integer getSubscribeid() {
        return subscribeid;
    }

    public void setSubscribeid(Integer subscribeid) {
        this.subscribeid = subscribeid;
    }

    public String getCreativetitle() {
        return creativetitle;
    }

    public void setCreativetitle(String creativetitle) {
        this.creativetitle = creativetitle;
    }

    public String getChannelkey() {
        return channelkey;
    }

    public void setChannelkey(String channelkey) {
        this.channelkey = channelkey;
    }

    public String getProvincename() {
        return provincename;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public String getPositionname() {
        return positionname;
    }

    public void setPositionname(String positionname) {
        this.positionname = positionname;
    }

    public Integer getCampaignid() {
        return campaignid;
    }

    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    public String getCampaignname() {
        return campaignname;
    }

    public void setCampaignname(String campaignname) {
        this.campaignname = campaignname;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getBudgettype() {
        return budgettype;
    }

    public void setBudgettype(Integer budgettype) {
        this.budgettype = budgettype;
    }

    public Integer getBudgetover() {
        return budgetover;
    }

    public void setBudgetover(Integer budgetover) {
        this.budgetover = budgetover;
    }

    public Integer getCampaignstate() {
        return campaignstate;
    }

    public void setCampaignstate(Integer campaignstate) {
        this.campaignstate = campaignstate;
    }

    public Integer getAdtype() {
        return adtype;
    }

    public void setAdtype(Integer adtype) {
        this.adtype = adtype;
    }

    public String getAdduserid() {
        return adduserid;
    }

    public void setAdduserid(String adduserid) {
        this.adduserid = adduserid;
    }

    public String getModuserid() {
        return moduserid;
    }

    public void setModuserid(String moduserid) {
        this.moduserid = moduserid;
    }

    public Integer getBid() {
        return bid;
    }

    public void setBid(Integer bid) {
        this.bid = bid;
    }

    public Integer getSubscribestate() {
        return subscribestate;
    }

    public void setSubscribestate(Integer subscribestate) {
        this.subscribestate = subscribestate;
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

    public Integer getCreativestate() {
        return creativestate;
    }

    public void setCreativestate(Integer creativestate) {
        this.creativestate = creativestate;
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

    public String getRefusereason() {
        return refusereason;
    }

    public void setRefusereason(String refusereason) {
        this.refusereason = refusereason;
    }

    public String getModinfo() {
        return modinfo;
    }

    public void setModinfo(String modinfo) {
        this.modinfo = modinfo;
    }

    public Integer getChannelstate() {
        return channelstate;
    }

    public void setChannelstate(Integer channelstate) {
        this.channelstate = channelstate;
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

    public String getPagename() {
        return pagename;
    }

    public void setPagename(String pagename) {
        this.pagename = pagename;
    }

    public String getPagedescription() {
        return pagedescription;
    }

    public void setPagedescription(String pagedescription) {
        this.pagedescription = pagedescription;
    }

    public String getPageurl() {
        return pageurl;
    }

    public void setPageurl(String pageurl) {
        this.pageurl = pageurl;
    }

    public String getPositiondescription() {
        return positiondescription;
    }

    public void setPositiondescription(String positiondescription) {
        this.positiondescription = positiondescription;
    }

    public String getDemourl() {
        return demourl;
    }

    public void setDemourl(String demourl) {
        this.demourl = demourl;
    }

    public Integer getCycle() {
        return cycle;
    }

    public void setCycle(Integer cycle) {
        this.cycle = cycle;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public Integer getPositionclient() {
        return positionclient;
    }

    public void setPositionclient(Integer positionclient) {
        this.positionclient = positionclient;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJs_code() {
        return js_code;
    }

    public void setJs_code(String js_code) {
        this.js_code = js_code;
    }

    public Integer getPositionstate() {
        return positionstate;
    }

    public void setPositionstate(Integer positionstate) {
        this.positionstate = positionstate;
    }
}
