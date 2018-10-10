package com.tangdou.es2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "position")
public class Position implements Serializable {
    @Id
    private Integer position_id;// 主键，自增
    private String page_name;// '页面名称'
    private String page_description;// '页面描述'
    private String page_url;// '页面地址，h5和小程序会用到'
    private String name;// '位置名称'
    private String description;// '位置描述'
    private Integer price_id;// '外键，对应价格表id'
    private String demo_url;// '示例地址'
    private Integer type;// '类型，1视频，2图文，3文字, 4图片'
    private Integer cycle;// '轮播数量'
    private String regular;// '规格配置，{"widht":"100px","height":"100px","size":"1000kb"}'
    private Integer client;// '系统，0:所有端，1:ios, 2:android,3,web,4:小程序'
    private String version;// '版本号'
    private String code;// '没广告的时候给的默认代码'
    private String js_code;//没广告的时候给的默认JS代码
    private Integer state;
    private Date add_time;
    private String add_user_id;
    private Date mod_time;
    private String mod_user_id;

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getPage_description() {
        return page_description;
    }

    public void setPage_description(String page_description) {
        this.page_description = page_description;
    }

    public String getPage_url() {
        return page_url;
    }

    public void setPage_url(String page_url) {
        this.page_url = page_url;
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

    public Integer getPrice_id() {
        return price_id;
    }

    public void setPrice_id(Integer price_id) {
        this.price_id = price_id;
    }

    public String getDemo_url() {
        return demo_url;
    }

    public void setDemo_url(String demo_url) {
        this.demo_url = demo_url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}