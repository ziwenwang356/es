package com.tangdou.es2;


import com.tangdou.es2.service.PublishingMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPublishingMsg {
    @Autowired
    private PublishingMsg publishingMsg;


    @Test
    public void sendMessageTest() {
        String message0 = "table=creative_info_0,id=1,operation=add";
        String message1 = "table=creative_info_0,id=1,operation=update";

        String message2 = "table=subscribe,id=1,operation=add";
        String message3 = "table=subscribe,id=1,operation=update";

        String message4 = "table=campaign,id=1,operation=add";// 就这么发，
        String message5 = "table=campaign,id=1,operation=update";//



        publishingMsg.sendMessage(message0);


    }
}
