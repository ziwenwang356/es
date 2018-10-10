package com.tangdou.es2.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "channel_profile")
public class ChannelProfile implements Serializable {
	@Id
	private Integer channel_id;
	private Integer province_id;// 外键，省份id,0为不限制省份
	private Integer city_id;// 外键，城市id,0为不限制城市
	private Integer position_id;// 外键，广告位id
	private Integer network;// 网络环境，0:所有网络环境，1:wifi, 2:4G, 3:3G
	private Integer client;// 系统，0:所有端，1:ios, 2:android
	private Integer frequency;// 投放频次,预留字段,一期不做
	private Integer crowd;// 人群，预留字段，一期不做
	private String channel_key;
	private Integer price_id;// 外键，对应价格表id
	private Integer state;// 0：正常；1：搁置；2：删除
	private Integer impression;// '热度，统计用'
	private Integer conversion;// '转化，统计用'
	private Integer competition;// '竞争,统计用'
	private Date add_time;
	private String add_user_id;
	private Date mod_time;
	private Integer mod_user_id;
	private String channel_name;
	private String channel_url;

	public Integer getChannel_id() {
		return channel_id;
	}

	public void setChannel_id(Integer channel_id) {
		this.channel_id = channel_id;
	}

	public Integer getProvince_id() {
		return province_id;
	}

	public void setProvince_id(Integer province_id) {
		this.province_id = province_id;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}

	public Integer getPosition_id() {
		return position_id;
	}

	public void setPosition_id(Integer position_id) {
		this.position_id = position_id;
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

	public String getChannel_key() {
		return channel_key;
	}

	public void setChannel_key(String channel_key) {
		this.channel_key = channel_key;
	}

	public Integer getPrice_id() {
		return price_id;
	}

	public void setPrice_id(Integer price_id) {
		this.price_id = price_id;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
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

	public Integer getMod_user_id() {
		return mod_user_id;
	}

	public void setMod_user_id(Integer mod_user_id) {
		this.mod_user_id = mod_user_id;
	}

	public String getChannel_name() {
		return channel_name;
	}

	public void setChannel_name(String channel_name) {
		this.channel_name = channel_name;
	}

	public String getChannel_url() {
		return channel_url;
	}

	public void setChannel_url(String channel_url) {
		this.channel_url = channel_url;
	}

}