package com.tangdou.es2.service;


/**
 * 当creative_info_0,subscribe,campaign更新或者新增的时候需要给kafka中发布消息
 * <p>
 * 1,发布消息的规范如下：（table=tableName,id=id1,operation=operation1）
 * <p>
 * 表名有：creative_info_0,subscribe,campaign
 * <p>
 * 操作符:add,update
 * <p>
 * eg:table=creative_info_0,id=1,operation=add
 */
public interface PublishingMsg {
    void sendMessage(String msg);
}
