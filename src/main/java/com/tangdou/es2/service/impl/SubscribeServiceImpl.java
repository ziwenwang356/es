package com.tangdou.es2.service.impl;

import com.tangdou.es2.config.Redis;
import com.tangdou.es2.service.SubscribeMsgHelperService;
import com.tangdou.es2.service.SubscribeService;
import com.tangdou.es2.utils.RedisShuffler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class SubscribeServiceImpl implements SubscribeService {


    Logger logger = LoggerFactory.getLogger(SubscribeServiceImpl.class);
    RedisShuffler redis = Redis.getRedis();

    @Autowired
    SubscribeMsgHelperService subscribeMsgHelper;


    private static final String CHANNEL = "adindex_redis";

    @Override
    @Scheduled(cron = "0 0/1 * * * *")
    public void subscribeMsg() {
        redis.safeAccess(x->x.get("a"));
        System.out.println("");
        redis.safeAccess(x -> x.subscribe(subscribeMsgHelper, CHANNEL));
    }


}
