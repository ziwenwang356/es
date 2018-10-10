package com.tangdou.es2.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.ResourceBundle;

public class RedisProvider {
	public static final Logger logger = LoggerFactory.getLogger(RedisProvider.class);
	public static final RedisShuffler redis ;
	public static final RedisShuffler subRedis ;
	static {
		ResourceBundle resource = ResourceBundle.getBundle("application");
		String redis_cluster_nodes = resource.getString("redis.cluster.nodes");
		redis = new RedisShuffler(Arrays.asList(redis_cluster_nodes.split("\\,")));
		
		String redis_cluster_message_nodes = resource.getString("redis.cluster.msg.nodes");
		subRedis = new RedisShuffler(Arrays.asList(redis_cluster_message_nodes.split("\\,")));
		//logger.info("RedisProvider init successed ...");
	}
}
