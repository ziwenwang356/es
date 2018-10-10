package com.tangdou.es2.service;


import com.tangdou.es2.service.impl.ConsumingMsgImpl;
import com.tangdou.es2.utils.RedisShuffler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPubSub;

import java.util.Arrays;

@Service
public class SubscribeMsgHelperService extends JedisPubSub {

    @Autowired
    ConsumingMsgImpl consumingMsg;

    Logger logger = LoggerFactory.getLogger(SubscribeMsgHelperService.class);
    protected static String redis_cluster_nodes = "127.0.0.1:6379";
    protected static RedisShuffler redis = new RedisShuffler(Arrays.asList(redis_cluster_nodes.split("\\,")));



    public void onMessage(String channel, String message) {
        System.out.println(String.format("received redis published message, channel %s, message %s", channel, message));
        logger.info("received redis published message, channel %s, message %s", channel, message);
        System.out.printf("received redis published message, channel %s, message %s", channel, message);
        consumingMsg.process(message);
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("subscribed redis channel success, channel %s, subscribedChannels %d", channel, subscribedChannels));
        logger.info("subscribed redis channel success, channel: %s, subscribedChannels: %d", channel, subscribedChannels);
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d", channel, subscribedChannels));

    }


}
