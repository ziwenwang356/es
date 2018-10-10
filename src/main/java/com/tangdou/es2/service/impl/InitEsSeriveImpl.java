package com.tangdou.es2.service.impl;

import com.tangdou.es2.entity.*;
import com.tangdou.es2.repository.*;
import com.tangdou.es2.service.CreativeIndexTotalService;
import com.tangdou.es2.service.InitEsSerive;
import com.tangdou.es2.utils.ExceptionDeal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class InitEsSeriveImpl implements InitEsSerive {

    Logger logger = LoggerFactory.getLogger(InitEsSeriveImpl.class);
    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private SubscribeRepository subscribeRepository;

    @Autowired
    private CreativeInfoRepository creativeInfoRepository;

    @Autowired
    private ChannelProfileRepository channelProfileRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    CreativeIndexTotalService creativeIndexTotalService;

    @Autowired
    private CreativeIndexTotalRepository creativeIndexTotalRepository;


    //    @Scheduled(cron = "0 0/10 * * * ?")
    @Override
    public void insertIntoCompose() {
        logger.info("Init all data is starting...");
        Long counter = 0L;
        List<CreativeInfo> creativeInfos = creativeInfoRepository.findAll();
        if (null == creativeInfos) {
            return;
        }
        List<Integer> campaignIds = new ArrayList<>();
        for (CreativeInfo creativeInfo : creativeInfos) {
            campaignIds.add(creativeInfo.getCampaign_id());
        }
        List<Campaign> campaigns = campaignRepository.findAll();
        List<Integer> camIds = new ArrayList<>();
        for (Campaign campaign : campaigns) {
            if (null == campaign) {
                continue;
            }
            camIds.add(campaign.getCampaign_id());
        }
        List<Integer> rubbish = new ArrayList<>();
        for (Integer id : campaignIds) {
            if (!camIds.contains(id)) {
                rubbish.add(id);
            }
        }
        for (CreativeInfo creativeInfo : creativeInfos) {
            if (null == creativeInfo) {
                continue;
            }
            if (0 != creativeInfo.getState()) {
                continue;
            }

            ChannelProfile channelProfile = channelProfileRepository.findById(creativeInfo.getChannel_id()).get();
            if (rubbish.contains(creativeInfo.getCampaign_id())) {
                continue;
            }
            Campaign campaign = campaignRepository.findById(creativeInfo.getCampaign_id()).get();
            Subscribe subscribe = subscribeRepository.findById(creativeInfo.getSubscribe_id()).get();
            CreativeIndexTotal creativeIndexTotal = new CreativeIndexTotal();
            creativeIndexTotal.setCreativeid(creativeInfo.getCreative_id());
            creativeIndexTotal.setChannelid(creativeInfo.getChannel_id());
            if (null != channelProfile) {
                if (0 != channelProfile.getState()) {
                    continue;
                }
                creativeIndexTotal.setProvinceid(channelProfile.getProvince_id());
                creativeIndexTotal.setCityid(channelProfile.getCity_id());
                creativeIndexTotal.setPositionid(channelProfile.getPosition_id());
                if (!ExceptionDeal.isNull(channelProfile.getNetwork())) {
                    creativeIndexTotal.setNetwork(channelProfile.getNetwork());
                }
                if (!ExceptionDeal.isNull(channelProfile.getClient())) {
                    creativeIndexTotal.setClient(channelProfile.getClient());
                }
                if (!ExceptionDeal.isNull(channelProfile.getFrequency())) {
                    creativeIndexTotal.setFrequency(channelProfile.getFrequency());
                } else {
                    creativeIndexTotal.setFrequency(0);
                }
                if (!ExceptionDeal.isNull(channelProfile.getCrowd())) {
                    creativeIndexTotal.setCrowd(channelProfile.getCrowd());
                } else {
                    creativeIndexTotal.setCrowd(0);
                }
                if (!ExceptionDeal.isNull(channelProfile.getChannel_key())) {
                    creativeIndexTotal.setChannelkey(channelProfile.getChannel_key());
                }
                if (!ExceptionDeal.isNull(channelProfile.getImpression())) {
                    creativeIndexTotal.setImpression(channelProfile.getImpression());
                } else {
                    creativeIndexTotal.setImpression(0);
                }
                if (!ExceptionDeal.isNull(channelProfile.getConversion())) {
                    creativeIndexTotal.setConversion(channelProfile.getConversion());
                } else {
                    creativeIndexTotal.setConversion(0);
                }
                if (!ExceptionDeal.isNull(channelProfile.getCompetition())) {
                    creativeIndexTotal.setCompetition(channelProfile.getCompetition());
                } else {
                    creativeIndexTotal.setCompetition(0);
                }
                if (!ExceptionDeal.isNull(channelProfile.getChannel_name())) {
                    creativeIndexTotal.setChannelname(channelProfile.getChannel_name());
                } else {
                    creativeIndexTotal.setChannelname("");
                }
                if (!ExceptionDeal.isNull(channelProfile.getChannel_url())) {
                    creativeIndexTotal.setChannelurl(channelProfile.getChannel_url());
                } else {
                    creativeIndexTotal.setChannelurl("");
                }

                Position position = positionRepository.findById(channelProfile.getPosition_id()).get();

                if (null != position) {
                    if (!ExceptionDeal.isNull(position.getType())) {
                        creativeIndexTotal.setPositiontype(position.getType());
                    } else {
                        creativeIndexTotal.setPositiontype(3);
                    }
                    if (!ExceptionDeal.isNull(position.getPosition_id())) {
                        creativeIndexTotal.setPositionid(position.getPosition_id());
                    }

                    if (!ExceptionDeal.isNull(position.getType())) {
                        creativeIndexTotal.setPositiontype(position.getType());
                    } else {
                        creativeIndexTotal.setPositiontype(3);
                    }
                }
                if (!ExceptionDeal.isNull(channelProfile.getPrice_id())) {
                    creativeIndexTotal.setPriceid(channelProfile.getPrice_id());
                } else {
                    creativeIndexTotal.setPriceid(0);// 设置默认值
                }
                creativeIndexTotal.setRegion1("中国");
                if (0 == channelProfile.getProvince_id()) {
                    creativeIndexTotal.setRegion2("全国");
                } else {
                    Province province = provinceRepository.findById(channelProfile.getProvince_id()).get();
                    if (null != province) {
                        if (!ExceptionDeal.isNull(province.getName())) {
                            creativeIndexTotal.setRegion2(province.getName());
                        }
                    }
                }
                if (0 == channelProfile.getCity_id()) {
                    creativeIndexTotal.setRegion3("全国");
                } else {
                    City city = cityRepository.findById(channelProfile.getCity_id()).get();
                    if (!ExceptionDeal.isNull(city.getName())) {
                        creativeIndexTotal.setRegion3(city.getName());
                    }
                }
            }
            if (!ExceptionDeal.isNull(creativeInfo.getSubscribe_id())) {
                creativeIndexTotal.setSubscribeid(creativeInfo.getSubscribe_id());
            }
            if (!ExceptionDeal.isNull(creativeInfo.getCampaign_id())) {
                creativeIndexTotal.setCampaignid(creativeInfo.getCampaign_id());
            }

            if (null != campaign) {
                if (0 != campaign.getState()) {
                    continue;
                }
                Long curr = System.currentTimeMillis();
//                 如下是个开关，用于过滤时间没有在[start,end]范围之内的广告。因为目前是测试所以关掉，等到要上线的时候需要关掉
                if ((null != campaign.getStart_time()) && (null != campaign.getEnd_time())) {

                    Long start = campaign.getStart_time().getTime();
                    Long end = campaign.getEnd_time().getTime();
                    if (!(curr >= start && curr <= end)) {
                        continue;
                    }
                }

                Date date = new Date(System.currentTimeMillis());
                if (!ExceptionDeal.isNull(campaign.getStart_time())) {
                    creativeIndexTotal.setStarttime(campaign.getStart_time());
                } else {
                    creativeIndexTotal.setStarttime(date);
                }
                if (!ExceptionDeal.isNull(campaign.getEnd_time())) {
                    creativeIndexTotal.setEndtime(campaign.getEnd_time());
                } else {
                    creativeIndexTotal.setEndtime(date);
                }
                if (!ExceptionDeal.isNull(campaign.getAd_type())) {
                    creativeIndexTotal.setAdtype(campaign.getAd_type());
                }
            }
            creativeIndexTotal.setUserid(creativeInfo.getUser_id());
            if (subscribe != null) {
                if (0 != subscribe.getState()) {
                    continue;
                }
                if (!ExceptionDeal.isNull(subscribe.getBid())) {
                    creativeIndexTotal.setBid(subscribe.getBid());
                }
            }
            if (!ExceptionDeal.isNull(creativeInfo.getDescribe())) {
                creativeIndexTotal.setDescribe(creativeInfo.getDescribe());
            } else {
                creativeIndexTotal.setDescribe("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getTarget_url())) {
                creativeIndexTotal.setTargeturl(creativeInfo.getTarget_url());
            } else {
                creativeIndexTotal.setTargeturl("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getOpen_url())) {
                creativeIndexTotal.setOpenurl(creativeInfo.getOpen_url());
            } else {
                creativeIndexTotal.setOpenurl("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getPic_url())) {
                creativeIndexTotal.setPicurl(creativeInfo.getPic_url());
            } else {
                creativeIndexTotal.setPicurl("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getShow_time())) {
                creativeIndexTotal.setShowtime(creativeInfo.getShow_time());
            } else {
                creativeIndexTotal.setShowtime(15);
            }
            if (!ExceptionDeal.isNull(creativeInfo.getVideo_url())) {
                creativeIndexTotal.setVideourl(creativeInfo.getVideo_url());
            } else {
                creativeIndexTotal.setVideourl("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getVideo_duration())) {
                creativeIndexTotal.setVideoduration(creativeInfo.getVideo_duration());
            } else {
                creativeIndexTotal.setVideoduration(0);
            }
            if (!ExceptionDeal.isNull(creativeInfo.getAppinfo())) {
                creativeIndexTotal.setAppinfo(creativeInfo.getAppinfo());
            } else {
                creativeIndexTotal.setAppinfo("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getAppid())) {
                creativeIndexTotal.setAppid(creativeInfo.getAppid());
            } else {
                creativeIndexTotal.setAppid("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getVid())) {
                creativeIndexTotal.setVid(creativeInfo.getVid());
            } else {
                creativeIndexTotal.setVid("");
            }
            if (!ExceptionDeal.isNull(creativeInfo.getAction())) {
                creativeIndexTotal.setAction(creativeInfo.getAction());
            } else {
                creativeIndexTotal.setAction(0);
            }
            if (!ExceptionDeal.isNull(creativeInfo.getRepeat())) {
                creativeIndexTotal.setRepeat(creativeInfo.getRepeat());
            } else {
                creativeIndexTotal.setRepeat(1);//设置了默认值
            }
            if (!ExceptionDeal.isNull(creativeInfo.getCreative_type())) {
                creativeIndexTotal.setCreativetype(creativeInfo.getCreative_type());
            } else {
                creativeIndexTotal.setCreativetype(4);//设置了默认值
            }
            if (!ExceptionDeal.isNull(creativeInfo.getPosition_type())) {
                creativeIndexTotal.setPositiontype(creativeInfo.getPosition_type());
            } else {
                creativeIndexTotal.setPositiontype(1);
            }

            if (!ExceptionDeal.isNull(creativeInfo.getPic_type())) {
                creativeIndexTotal.setPictype(creativeInfo.getPic_type());
            } else {
                creativeIndexTotal.setPictype(0);
            }
            if (!ExceptionDeal.isNull(creativeInfo.getLocal_industry())) {
                creativeIndexTotal.setLocalindustry(creativeInfo.getLocal_industry());
            } else {
                creativeIndexTotal.setLocalindustry(0);
            }
            creativeIndexTotalService.createOne(creativeIndexTotal);
            System.out.println("succeed: " + ++counter);
            logger.info("succeed: " + ++counter);
        }

        logger.info("Init all data is the end.");
    }
}
