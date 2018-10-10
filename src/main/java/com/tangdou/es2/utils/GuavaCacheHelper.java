package com.tangdou.es2.utils;


import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

@SuppressWarnings({"rawtypes","unchecked"})
public class GuavaCacheHelper {

	private static final Logger logger = LoggerFactory.getLogger(GuavaCacheHelper.class);
	// public
	public static Cache<String,Object> cache ;
	// 状态
	
	static{
		long timeToLiveSeconds = 60 * 60 * 24 * 3;
		cache = CacheBuilder.newBuilder().maximumSize(10000000).expireAfterWrite(timeToLiveSeconds, TimeUnit.SECONDS).build();
//		videoCache = CacheBuilder.newBuilder().maximumSize(10000000).expireAfterWrite(timeToLiveSeconds, TimeUnit.SECONDS).build();
		
		logger.info("init guava cache success ...");
	}
	
	public static Object get(String key){
		Object res = null;
		try {
			res = cache.get(key, new Callable<Object>() {

				@Override
				public Object call() throws Exception {
					return null;
				}
			});
		} catch (Exception e) {
		}
		return res;
	}
	
	public static String get(Cache cache, String key){
		Object res = null;
		try {
			res = cache.get(key, new Callable<Object>() {
				
				@Override
				public Object call() throws Exception {
					return -1;
				}
			});
		} catch (Exception e) {
		}
		return Convert.toString(res);
	}
	
	public static Integer getInt(Cache cache, String key){
		Object res = null;
		try {
			res = cache.get(key, new Callable<Object>() {
				
				@Override
				public Object call() throws Exception {
					return -1;
				}
			});
		} catch (Exception e) {
		}
		return Convert.toInt(res);
	}
	
	public static void set(String key, Object value){
		try{
			cache.put(key,value);
		}catch(Exception e){
		}
	}
	
	public static void set(Cache cache, String key, Object value){
		try{
			cache.put(key,value);
		}catch(Exception e){
		}
	}

	public static void del(String key) {
		try {
			cache.invalidate(key);
		} catch (Exception e) {
		}
	}
	
	public static void del(Cache cache, String key) {
		try {
			cache.invalidate(key);
		} catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
		GuavaCacheHelper.set(GuavaCacheHelper.cache, "test", "hello world");
		Object res = GuavaCacheHelper.get(GuavaCacheHelper.cache, "test");
		System.out.println(res);
	}
}
