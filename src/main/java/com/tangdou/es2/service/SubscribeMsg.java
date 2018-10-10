//package com.tangdou.es2.service;
//
//
//import com.tangdou.es2.config.Redis;
//import com.tangdou.es2.utils.RedisShuffler;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//@Service
//public class SubscribeMsg {
//
//    Logger logger = LoggerFactory.getLogger(SubscribeMsg.class);
//    RedisShuffler redis = Redis.getRedis();
//
//    @Autowired
//    SubscribeMsgHelperService subscribeMsgHelper;
//
//
//    private static final String CHANNEL = "adindex_redis";
//
//
//    @Scheduled(cron = "0 0/1 * * * *")
//    public void subscribeMsg() {
//        redis.safeAccess(x -> x.subscribe(subscribeMsgHelper, CHANNEL));
//    }
//}
