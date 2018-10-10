package com.tangdou.es2.service.impl;

import com.tangdou.es2.service.PublishingMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PublishingMsgImpl implements PublishingMsg {

    private static final String TOPIC_NAME = "adindex";
    Logger logger = LoggerFactory.getLogger(PublishingMsgImpl.class);

    String message0 = "table=creative_info_0,id=1,operation=add";//就传这些参数
    // String creative_id, String campaign_id, String position_id, String title, String target_url, String pic_url,String appid,String vid,Integer position_type,String describe,String bid,Integer pic_type, Integer repeat, HttpServletRequest request, HttpSession session,String refuse_reason
    String message1 = "table=creative_info_0,id=468,operation=update,bid=1.9,state=0";//对哪些字段做了修改就传过来,审核对其产生的影响
    String message2 = "table=creative_info_0,id=1,operation=delete,state=1";//此时需要在ES中删除该记录，对哪些字段做了修改就传过来

    String message4 = "table=subscribe,id=1,operation=update,state=0";//审核对其产生的影响

    String message7 = "table=campaign,id=1,operation=update,campaign_name=test,budget=1000,budgetover=1,start_time=1534931968126,end_time=1534932088678";//对哪些字段做了修改就传过来,time:13位毫秒,要是你的时间设置为从现在就开始，那么传给我的这两个时间均为0budgetover做暂停使用
    String message8 = "table=campaign,id=1,operation=delete,state=2";//此时需要在ES中删除该记录，对哪些字段做了修改就传过来

    @Override
    public void sendMessage(String msg) {

    }
}
