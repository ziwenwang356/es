package com.tangdou.es2;

import com.tangdou.es2.service.ConsumingMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestConsumingMsg {

    @Autowired
    private ConsumingMsg consumingMsg;

    @Test
    public void listenTest() {
    }

}
