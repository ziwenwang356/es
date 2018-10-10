package com.tangdou.es2.config;

import com.tangdou.es2.utils.RedisShuffler;
import org.springframework.stereotype.Component;

import java.util.Arrays;

//@Configuration
@Component
public class Redis {
//
//    @Value("${spring.redis.cluster.nodes}")
//    private static String clusterNodes;

    //private static String clusterNodes = "10.19.112.202:6379,10.19.12.224:6379";
//    private static String clusterNodes = "10.19.78.105:6380";//这样连接会导致 Connection refused
//    private static String clusterNodes = "127.0.0.1:6379";
    private static String clusterNodes = "127.0.0.1:6379";

    public static RedisShuffler getRedis() {
        System.out.println(clusterNodes);
        RedisShuffler redisShuffler = new RedisShuffler(Arrays.asList(clusterNodes.split("\\,")));
        System.out.println(clusterNodes);
        return redisShuffler;
    }

}
