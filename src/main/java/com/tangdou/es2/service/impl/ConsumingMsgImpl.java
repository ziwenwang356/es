package com.tangdou.es2.service.impl;

import com.tangdou.es2.entity.*;
import com.tangdou.es2.repository.*;
import com.tangdou.es2.service.ConsumingMsg;
import com.tangdou.es2.service.CreativeIndexTotalService;
import com.tangdou.es2.service.SubscribeMsgHelperService;
import com.tangdou.es2.utils.ExceptionDeal;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConsumingMsgImpl implements ConsumingMsg {

    Logger logger = LoggerFactory.getLogger(ConsumingMsgImpl.class);
    private static final String TABLE_CREATIVE_INFO_0 = "creative_info_0";
    private static final String TABLE_SUBSCRIBE = "subscribe";
    private static final String TABLE_CAMPAIGN = "campaign";
    private static final String TABLE_OPERATION_ADD = "add";
    private static final String TABLE_OPERATION_UPDATE = "update";
    private static final String TABLE_OPERATION_DELETE = "delete";
    private static final String state = "state";
    private static final String start_time = "start_time";
    private static final String end_time = "end_time";
    private static final String bid = "bid";
    private static final String title = "title";
    private static final String target_url = "target_url";
    private static final String pic_url = "pic_url";
    private static final String appid = "appid";
    private static final String vid = "vid";
    private static final String position_type = "position_type";
    private static final String describe = "describe";
    private static final String pic_type = "pic_type";//
    private static final String repeat = "repeat";
    private Long counter = 0L;

    @Autowired
    SubscribeMsgHelperService subscribeMsgHelper;

    @Autowired
    CreativeIndexTotalRepository creativeIndexTotalRepository;

    @Autowired
    CreativeInfoRepository creativeInfoRepository;

    @Autowired
    CampaignRepository campaignRepository;

    @Autowired
    ChannelProfileRepository channelProfileRepository;

    @Autowired
    SubscribeRepository subscribeRepository;

    @Autowired
    PositionRepository positionRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    CreativeIndexTotalService creativeIndexTotalService;

    private List<Integer> rubbish = new ArrayList<>();

    public void detectTimeIsValidation() {
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalRepository.findAllData();
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            if (null == creativeIndexTotal) {
                continue;
            }
            Long cur = new Date(System.currentTimeMillis()).getTime();
            Long start = creativeIndexTotal.getStarttime().getTime();
            Long end = creativeIndexTotal.getEndtime().getTime();
            if (start == end) {
                continue;
            }
            if (!(cur >= start && cur <= end)) {
                creativeIndexTotalRepository.delete(creativeIndexTotal);
            }
        }
    }


    public void process(String string) {
        logger.info("start to process in es!");
        System.out.println("start to process in es!");
        logger.info("string is :"+string);
        System.out.println("string is :"+string);
        getDirtyCampaignIds();
        String message0 = "table=creative_info_0,id=1,operation=add";//就传这些参数
        // String creative_id, String campaign_id, String position_id, String title, String target_url, String pic_url,String appid,String vid,Integer position_type,String describe,String bid,Integer pic_type, Integer repeat, HttpServletRequest request, HttpSession session,String refuse_reason
        String message1 = "table=creative_info_0,id=468,operation=update,bid=1.9,state=0";//对哪些字段做了修改就传过来,审核对其产生的影响
        String message2 = "table=creative_info_0,id=1,operation=delete,state=1";//此时需要在ES中删除该记录，对哪些字段做了修改就传过来

        String message4 = "table=subscribe,id=1,operation=update,state=0";//审核对其产生的影响

        String message7 = "table=campaign,id=1,operation=update,campaign_name=test,budget=1000,budgetover=1,start_time=1534931968126,end_time=1534932088678";//对哪些字段做了修改就传过来,time:13位毫秒,要是你的时间设置为从现在就开始，那么传给我的这两个时间均为0budgetover做暂停使用
        String message8 = "table=campaign,id=1,operation=delete,state=2";//此时需要在ES中删除该记录，对哪些字段做了修改就传过来

        if (StringUtils.isNotBlank(string)){
            String[] arr = string.split(",");
            if (arr.length >= 3) {
                String table = arr[0].split("=")[1];
                String id = arr[1].split("=")[1];
                String operation = arr[2].split("=")[1];
                Map<String, String> params = new HashMap<>();
                for (int i = 3; i < arr.length && arr[i] != null; i++) {
                    String[] tmp = arr[i].split("=");
                    for (String s:tmp) {
                        System.out.println("s value is:"+s);
                    }
                    params.put(tmp[0], tmp[1]);
                }
                // TODO creative_info_% 后期可能需要扩展，分表
                if (TABLE_CREATIVE_INFO_0.equalsIgnoreCase(table)) {
                    processCreative(id, operation, params);
                    logger.info("Current table is :" + TABLE_CREATIVE_INFO_0);
                }
                if (TABLE_SUBSCRIBE.equalsIgnoreCase(table)) {
                    processSubscribe(id, operation, params);
                    logger.info("Current table is :" + TABLE_SUBSCRIBE);
                }
                if (TABLE_CAMPAIGN.equalsIgnoreCase(table)) {
                    processCampaign(id, operation, params);
                    logger.info("Current table is :" + TABLE_CAMPAIGN);
                }

            }
        }
        logger.info("processed in es sucessfully!");
        System.out.println("processed in es sucessfully!");

    }


    public void processCreative(String id, String operation, Map<String, String> params) {

        Long creativeId = Long.parseLong(id);
        Optional<CreativeInfo> creativeTmp = creativeInfoRepository.findById(creativeId);
        if (!creativeTmp.isPresent()){
            logger.info("CreativeInfo is not present");
            return;
        }
        CreativeInfo creativeInfo = creativeInfoRepository.findById(creativeId).get();
        Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
        if (TABLE_OPERATION_ADD.equalsIgnoreCase(operation)) {
            // TODO 增量处理
            List<CreativeInfo> creativeInfos = creativeInfoRepository.findAll();
            if (null == creativeInfos) {
                logger.info("creativeInfoRepository.findAll() is null");
                return;
            }
            List<Integer> campaignIds = new ArrayList<>();
            for (CreativeInfo creativeInfo2 : creativeInfos) {
                campaignIds.add(creativeInfo2.getCampaign_id());
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
            for (Integer id2 : campaignIds) {
                if (!camIds.contains(id2)) {
                    rubbish.add(id2);
                }
            }

//            for (Integer integer:rubbish) {
//                if (integer==creativeInfo.getCampaign_id()){
//                    return;
//                }
//            }
            if (null==creativeInfo){
                return;
            }
            String string="a";
            String re=string+"3";
            System.out.println(re);

            if (creativeInfo.getState()==0) {
                Campaign campaign=null;
                Optional<Campaign> optional  = campaignRepository.findById(creativeInfo.getCampaign_id());
                if (optional.isPresent()){
                    campaign = optional.get();
                }
                Subscribe subscribe =null;
                if (subscribeRepository.findById(creativeInfo.getSubscribe_id()).isPresent()){
                    subscribe= subscribeRepository.findById(creativeInfo.getSubscribe_id()).get();
                }


                CreativeIndexTotal creativeIndexTotal = new CreativeIndexTotal();
                creativeIndexTotal.setCreativeid(creativeInfo.getCreative_id());
                creativeIndexTotal.setChannelid(creativeInfo.getChannel_id());
                ChannelProfile channelProfile = channelProfileRepository.findById(creativeInfo.getChannel_id()).get();
                if (null != channelProfile) {
                    if (0 == channelProfile.getState()) {
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
                        if (positionRepository.findById(channelProfile.getPosition_id()).isPresent()) {
                            Position position = positionRepository.findById(channelProfile.getPosition_id()).get();
                            if (null != position) {
                                if (0 == position.getState()) {
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
                            if (provinceRepository.findById(channelProfile.getProvince_id()).isPresent()) {
                                Province province = provinceRepository.findById(channelProfile.getProvince_id()).get();
                                if (null != province) {
                                    if (!ExceptionDeal.isNull(province.getName())) {
                                        creativeIndexTotal.setRegion2(province.getName());
                                    }
                                }
                            }
                        }
                        if (0 == channelProfile.getCity_id()) {
                            creativeIndexTotal.setRegion3("全国");
                        } else {
                            if (cityRepository.findById(channelProfile.getCity_id()).isPresent()) {
                                City city = cityRepository.findById(channelProfile.getCity_id()).get();
                                if (!ExceptionDeal.isNull(city.getName())) {
                                    creativeIndexTotal.setRegion3(city.getName());
                                }
                            }
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
                    if (0 == campaign.getState()) {
                        Long curr = System.currentTimeMillis();
                        // 如下是个开关，用于过滤时间没有在[start,end]范围之内的广告。因为目前是测试所以关掉，等到要上线的时候需要关掉
                        if ((null != campaign.getStart_time()) && (null != campaign.getEnd_time())) {
                            Long start = campaign.getStart_time().getTime();
                            Long end = campaign.getEnd_time().getTime();
                            if ((curr >= start && curr <= end)) {
                                creativeIndexTotal.setStarttime(campaign.getStart_time());
                                creativeIndexTotal.setEndtime(campaign.getEnd_time());
                            }
                        } else {
                            Date date = new Date(System.currentTimeMillis());
                            creativeIndexTotal.setStarttime(date);
                            creativeIndexTotal.setEndtime(date);
                        }
                    }

                }
                creativeIndexTotal.setUserid(creativeInfo.getUser_id());
                if (subscribe != null) {
                    if (0 == subscribe.getState()) {
                        if (!ExceptionDeal.isNull(subscribe.getBid())) {
                            creativeIndexTotal.setBid(subscribe.getBid());
                        }
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
            }

            logger.info("table:creative_info_0,ope: add , status:succeed!");
        }
        if (TABLE_OPERATION_UPDATE.equalsIgnoreCase(operation)) {
            if (null != creativeInfo) {
                Optional<CreativeIndexTotal> creativeTmp2 = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id());
                if (creativeTmp2.isPresent()) {
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> entry1 = iterator.next();
                        String key = entry1.getKey();
                        String value = entry1.getValue();
                        if (state.equalsIgnoreCase(key)) {
                            if ("0".equalsIgnoreCase(value)) {
                                createNewRecord(creativeInfo);
                            }
                        }

                    }
                } else {
                    if (creativeIndexTotalRepository.findById(creativeInfo.getCreative_id()).isPresent()){
                        CreativeIndexTotal creativeIndexTotal = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id()).get();
                        while (iterator.hasNext()) {
                            Map.Entry<String, String> entry = iterator.next();
                            String key = entry.getKey();
                            String value = entry.getValue();
                            if (title.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setTitle(value);
                            }
                            if (target_url.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setTargeturl(value);
                            }
                            if (pic_url.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setPicurl(value);
                            }
                            if (appid.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setAppid(value);
                            }
                            if (vid.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setVid(value);
                            }
                            if (position_type.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setPositiontype(Integer.parseInt(value));
                            }
                            if (describe.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setDescribe(value);
                            }
                            if (pic_type.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setPictype(Integer.parseInt(value));
                            }
                            if (repeat.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setRepeat(Integer.parseInt(value));
                            }
                            if (position_type.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setPositionid(Integer.parseInt(value));
                            }
                            if (bid.equalsIgnoreCase(key)) {
                                creativeIndexTotal.setBid(Integer.parseInt(value));
                            }
                            if (state.equalsIgnoreCase(key)) {
                                if ("1".equalsIgnoreCase(value)) {
                                    creativeIndexTotalRepository.delete(creativeIndexTotal);
                                }
                            }
                        }
                        Optional<CreativeIndexTotal> creativeTmp3 = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id());
                        if (creativeTmp3.isPresent()) {
                            creativeIndexTotalRepository.save(creativeIndexTotal);
                        }
                    }
                }

            }
            logger.info("table:creative_info_0,ope: update,  status:succeed!");
        }

        if (TABLE_OPERATION_DELETE.equalsIgnoreCase(operation)) {
            if (null != creativeInfo) {
                Optional<CreativeIndexTotal> creativeIndexTotalTmp = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id());
                if (creativeIndexTotalTmp.isPresent()) {
                    CreativeIndexTotal creativeIndexTotal = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id()).get();
                    creativeIndexTotalRepository.delete(creativeIndexTotal);
                }
            }
            logger.info("table:creative_info_0,ope: delete  status:succeed!");
        }
    }

    public void processSubscribe(String id, String operation, Map<String, String> params) {
        if (TABLE_OPERATION_UPDATE.equalsIgnoreCase(operation)) {
            Integer subscribeId = Integer.parseInt(id);
            CreativeInfo creativeInfo = creativeInfoRepository.findBySubscribeid(subscribeId);
            if (null != creativeInfo) {
                Optional<CreativeIndexTotal> creativeTmp = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id());
                if (!creativeTmp.isPresent()) {
                    return;
                }
                CreativeIndexTotal creativeIndexTotal = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id()).get();
                if (null != creativeIndexTotal) {
                    Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> entry = iterator.next();
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (start_time.equalsIgnoreCase(key)) {
                            detectTimeIsValidation();
                            Date start = new Date(Long.parseLong(value));
                            creativeIndexTotal.setStarttime(start);
                        }
                        if (end_time.equalsIgnoreCase(key)) {
                            detectTimeIsValidation();
                            Date end = new Date(Long.parseLong(value));
                            creativeIndexTotal.setEndtime(end);
                        }
                        if (bid.equalsIgnoreCase(key)) {
                            creativeIndexTotal.setBid(Integer.parseInt(value));
                        }

                    }
                    creativeIndexTotalRepository.save(creativeIndexTotal);

                }
            }

        }
        logger.info("table:subscribe,ope: update  status:succeed!");

    }


    public void processCampaign(String id, String operation, Map<String, String> params) {
        Integer campaignId = Integer.parseInt(id);
        List<CreativeInfo> creativeInfoList = creativeInfoRepository.findByCampaignid(campaignId);
        Date now = new Date(System.currentTimeMillis());
        if (TABLE_OPERATION_UPDATE.equalsIgnoreCase(operation)) {
            for (CreativeInfo creativeInfo : creativeInfoList) {
                if (null == creativeInfo) {
                    continue;
                }
                if (rubbish.contains(creativeInfo.getCampaign_id())) {
                    continue;
                }
                Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
                Optional<CreativeIndexTotal> creativeIndexTotalTmp = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id());

                if (!creativeIndexTotalTmp.isPresent()) {
                    // 开启
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> entry = iterator.next();
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (state.equalsIgnoreCase(key)) {
                            if ("0".equalsIgnoreCase(value)) {
                                createNewRecord(creativeInfo);
                            }
                        }
                    }

                } else {
                    CreativeIndexTotal creativeIndexTotal = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id()).get();
                    while (iterator.hasNext()) {
                        Map.Entry<String, String> entry = iterator.next();
                        String key = entry.getKey();
                        String value = entry.getValue();
                        if (start_time.equalsIgnoreCase(key)) {
                            if (null != creativeIndexTotal) {
                                Long cur = new Date(System.currentTimeMillis()).getTime();
                                Long start = creativeIndexTotal.getStarttime().getTime();
                                Long end = creativeIndexTotal.getEndtime().getTime();
                                if (!((start == end) || (cur >= start && cur <= end))) {
                                    creativeIndexTotalRepository.delete(creativeIndexTotal);
                                }
                                if (0L == Long.parseLong(value)) {
                                    creativeIndexTotal.setStarttime(now);
                                } else {
                                    Date date = new Date(Long.parseLong(value));
                                    creativeIndexTotal.setStarttime(date);
                                }
                            }
                        }
                        if (end_time.equalsIgnoreCase(key)) {
                            if (null != creativeIndexTotal) {
                                Long cur = new Date(System.currentTimeMillis()).getTime();
                                Long start = creativeIndexTotal.getStarttime().getTime();
                                Long end = creativeIndexTotal.getEndtime().getTime();
                                if (!((start == end) || (cur >= start && cur <= end))) {
                                    creativeIndexTotalRepository.delete(creativeIndexTotal);
                                    break;

                                }
                                if (0L == Long.parseLong(value)) {
                                    creativeIndexTotal.setEndtime(now);
                                } else {
                                    Date date = new Date(Long.parseLong(value));
                                    creativeIndexTotal.setEndtime(date);
                                }
                            }

                        }
                        if (state.equalsIgnoreCase(key)) {
                            if (null != creativeIndexTotal) {
                                if ("1".equalsIgnoreCase(value)) {
                                    creativeIndexTotalRepository.delete(creativeIndexTotal);
                                    break;
                                }
                            }
                        }

                    }
                    Optional<CreativeIndexTotal> creativeTmp2 = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id());
                    if (creativeTmp2.isPresent()) {
                        creativeIndexTotalRepository.save(creativeIndexTotal);
                    }
                }


            }
            logger.info("table:campaign,ope: update  status:succeed!");

        }
        if (TABLE_OPERATION_DELETE.equalsIgnoreCase(operation)) {
            for (CreativeInfo creativeInfo : creativeInfoList) {
                if (null == creativeInfo) {
                    continue;
                }
                if (rubbish.contains(creativeInfo.getCampaign_id())) {
                    continue;
                }
                Integer stat = Integer.parseInt(params.get(state));
                Optional<CreativeIndexTotal> creativeIndexTotalTmp = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id());
                if (creativeIndexTotalTmp.isPresent()) {
                    CreativeIndexTotal creativeIndexTotal = creativeIndexTotalRepository.findById(creativeInfo.getCreative_id()).get();
                    creativeIndexTotalRepository.delete(creativeIndexTotal);
                }
            }

            logger.info("table:campaign,ope: delete  status:succeed!");
        }

    }


    public void getDirtyCampaignIds() {
        List<CreativeInfo> creativeInfos = creativeInfoRepository.findAll();
        if (null == creativeInfos) {
            return;
        }
        List<Integer> campaignIds = new ArrayList<>();
        for (CreativeInfo creativeInfo1 : creativeInfos) {
            campaignIds.add(creativeInfo1.getCampaign_id());
        }
        List<Campaign> campaigns = campaignRepository.findAll();
        List<Integer> camIds = new ArrayList<>();
        for (Campaign campaign : campaigns) {
            if (null == campaign) {
                continue;
            }
            camIds.add(campaign.getCampaign_id());
        }

        for (Integer id2 : campaignIds) {
            if (!camIds.contains(id2)) {
                rubbish.add(id2);
            }
        }
    }


    public void createNewRecord(CreativeInfo creativeInfo) {

        if (0 != creativeInfo.getState()) {
            return;
        }
        List<CreativeInfo> creativeInfos = creativeInfoRepository.findAll();
        if (null == creativeInfos) {
            return;
        }
        List<Integer> campaignIds = new ArrayList<>();
        for (CreativeInfo creativeInfo1 : creativeInfos) {
            campaignIds.add(creativeInfo1.getCampaign_id());
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
        Optional<ChannelProfile> optionalChannelProfile = channelProfileRepository.findById(creativeInfo.getChannel_id());
        if (!optionalChannelProfile.isPresent()) {
            return;
        }
        ChannelProfile channelProfile = channelProfileRepository.findById(creativeInfo.getChannel_id()).get();
        if (rubbish.contains(creativeInfo.getCampaign_id())) {
            return;
        }
        Campaign campaign = campaignRepository.findById(creativeInfo.getCampaign_id()).get();
        Subscribe subscribe = subscribeRepository.findById(creativeInfo.getSubscribe_id()).get();
        CreativeIndexTotal creativeIndexTotal = new CreativeIndexTotal();
        creativeIndexTotal.setCreativeid(creativeInfo.getCreative_id());
        creativeIndexTotal.setChannelid(creativeInfo.getChannel_id());
        if (null != channelProfile) {
            if (0 != channelProfile.getState()) {
                return;
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
                return;
            }
            Long curr = System.currentTimeMillis();
//                 如下是个开关，用于过滤时间没有在[start,end]范围之内的广告。因为目前是测试所以关掉，等到要上线的时候需要关掉
            if ((null != campaign.getStart_time()) && (null != campaign.getEnd_time())) {

                Long start = campaign.getStart_time().getTime();
                Long end = campaign.getEnd_time().getTime();
                if (!(curr >= start && curr <= end)) {
                    return;
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
                return;
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

}
