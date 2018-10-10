package com.tangdou.es2;

import com.tangdou.es2.entity.ComposeTable;
import com.tangdou.es2.entity.CreativeIndex;
import com.tangdou.es2.entity.CreativeIndexTotal;
import com.tangdou.es2.service.ComposeTableService;
import com.tangdou.es2.service.CreativeIndexService;
import com.tangdou.es2.service.CreativeIndexTotalService;
import com.tangdou.es2.service.InitEsSerive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    @Autowired
    CreativeIndexService creativeIndexService;
    @Autowired
    CreativeIndexTotalService  creativeIndexTotalService;


    @Autowired
    ComposeTableService composeTableService;

    @Autowired
    InitEsSerive initEsSerive;

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void getOneTTest() {
        List<CreativeIndex> creativeIndices = creativeIndexService.getOneT();
        for (CreativeIndex creativeIndex : creativeIndices) {
            System.out.println(creativeIndex.getAppid());
            System.out.println(creativeIndex.getTitle());
            System.out.println(creativeIndex.getId());
            System.out.println("----------");
            System.out.println(creativeIndex.getCountry_id());
            System.out.println(creativeIndex.getProvince_id());
            System.out.println(creativeIndex.getCity_id());
        }
    }

    @Test
    public void getOneTest() {
        CreativeIndex creativeIndex = creativeIndexService.getOne(1);
        System.out.println(creativeIndex.getTitle());
        System.out.println(creativeIndex.getTarget_url());
        System.out.println(creativeIndex.getPic_url());
        System.out.println(creativeIndex.getShow_time());
    }


    @Test
    public void getAllTest() {
        List<CreativeIndex> creativeIndices = creativeIndexService.getAll();
        for (CreativeIndex creativeIndex : creativeIndices) {
            System.out.println(creativeIndex.getId());
            System.out.println(creativeIndex.getTitle());
            System.out.println("----------");
        }
    }


    @Test
    public void createOneTest() throws ParseException {
        Long id = 50L;
        List<ComposeTable> composeTables = composeTableService.getAllData();

        for (ComposeTable composeTable : composeTables) {
            CreativeIndexTotal creativeIndexTotal = new CreativeIndexTotal();
//            creativeIndexTotal.setCreativeid(id++);
            creativeIndexTotal.setCreativeid(composeTable.getCreativeid());
            creativeIndexTotal.setChannelid(composeTable.getChannelid());
            creativeIndexTotal.setProvinceid(composeTable.getProvinceid());
            creativeIndexTotal.setCityid(composeTable.getCityid());
            creativeIndexTotal.setPositionid(composeTable.getPositionid());
            creativeIndexTotal.setNetwork(composeTable.getNetwork());
            creativeIndexTotal.setClient(composeTable.getClient());
            creativeIndexTotal.setFrequency(composeTable.getFrequency());
            creativeIndexTotal.setCrowd(composeTable.getCrowd());
            creativeIndexTotal.setPriceid(composeTable.getPriceid());
            creativeIndexTotal.setSubscribeid(composeTable.getSubscribeid());
            creativeIndexTotal.setChannelkey(composeTable.getChannelkey());
            creativeIndexTotal.setTitle(composeTable.getCreativetitle());
            creativeIndexTotal.setRegion1("中国");
            creativeIndexTotal.setRegion2(composeTable.getProvincename());
            creativeIndexTotal.setRegion3(composeTable.getCityname());
            creativeIndexTotal.setCampaignid(composeTable.getCampaignid());
            creativeIndexTotal.setUserid(composeTable.getUserid());
            creativeIndexTotal.setStarttime(composeTable.getStarttime());
            creativeIndexTotal.setEndtime(composeTable.getEndtime());
//            creativeIndexTotal.setAdtype(0);
            creativeIndexTotal.setAdtype(composeTable.getAdtype());
            creativeIndexTotal.setBid(composeTable.getBid());
            creativeIndexTotal.setDescribe(composeTable.getDescribe());
            creativeIndexTotal.setTargeturl(composeTable.getTargeturl());
            creativeIndexTotal.setOpenurl(composeTable.getOpenurl());
            creativeIndexTotal.setPicurl(composeTable.getPicurl());
            creativeIndexTotal.setShowtime(composeTable.getShowtime());
            creativeIndexTotal.setVideourl(composeTable.getVideourl());
            if (null != composeTable.getVideoduration()) {
                creativeIndexTotal.setVideoduration(composeTable.getVideoduration());
            }
            creativeIndexTotal.setAppinfo(composeTable.getAppinfo());
            creativeIndexTotal.setAppid(composeTable.getAppid());
            creativeIndexTotal.setVid(composeTable.getVid());
            creativeIndexTotal.setAction(composeTable.getAction());
            creativeIndexTotal.setRepeat(composeTable.getRepeat());
            if (!isNull(composeTable.getCreativetype())) {
                creativeIndexTotal.setCreativetype(composeTable.getCreativetype());
            }
            creativeIndexTotal.setPositiontype(composeTable.getPositiontype());
            if (!isNull(composeTable.getPictype())) {
                creativeIndexTotal.setPictype(composeTable.getPictype());
            }
            if (!isNull(composeTable.getLocalindustry())) {
                creativeIndexTotal.setLocalindustry(composeTable.getLocalindustry());
            }
            if (!isNull(composeTable.getImpression())) {
                creativeIndexTotal.setImpression(composeTable.getImpression());
            }
            if (!isNull(composeTable.getConversion())) {
                creativeIndexTotal.setConversion(composeTable.getConversion());
            }
            if (!isNull(composeTable.getCompetition())) {
                creativeIndexTotal.setCompetition(composeTable.getCompetition());
            }
            if (!isNull(composeTable.getChannelname())) {
                creativeIndexTotal.setChannelname(composeTable.getChannelname());
            }
            if (!isNull(composeTable.getChannelurl())) {
                creativeIndexTotal.setChannelurl(composeTable.getChannelurl());
            }
            creativeIndexTotal.setPositiontype(composeTable.getPositiontype());
            creativeIndexTotalService.createOne(creativeIndexTotal);
        }
    }

    public boolean isNull(Object o) {
        return null == o;
    }

    @Test
    public void getAllDataTest() {
        List<ComposeTable> composeTables = composeTableService.getAllData();
        System.out.println(composeTables.size());
    }

    @Test
    public void getOneComposeTest() {
        ComposeTable composeTables = composeTableService.getOne(29L);
    }

    @Test
    public void findByTypeTest() {

        Integer adtype = 1;
        String positionname = "秀舞视频流第3位";
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByType(adtype);
        System.out.println(creativeIndexTotals.size());

    }

    //入口

    @Test
    public void findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3() {

        Integer adtype = 2;
        Integer positionid = 19;
        String region1 = "中国";
        String region2 = "陕西省";
        String region3 = "西安市";
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3(adtype, positionid, region1, region2, region3);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + ":  " + creativeIndexTotal.getPositiontype() + ":  " + creativeIndexTotal.getRegion2() + ": " + creativeIndexTotal.getRegion3());
        }
    }

    @Test
    public void findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo() {
        Integer adtype = 1;
        String positionname = "广场舞视频播放页相关视频信息流1";
        String region1 = "中国";
        String region2 = "浙江省";
        String region3 = "杭州市";
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo(adtype, positionname, region1, region2, region3);
        System.out.println("number: " + creativeIndexTotals.size());

    }


    @Test
    public void findAllData() {
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findAllData();
        System.out.println(creativeIndexTotals.size());

    }

    @Test
    public void findByDemo3() {
        Integer adtype = 1;
        String positionname = "秀舞视频流第3位";
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByDemo3(adtype, positionname);
        System.out.println(creativeIndexTotals.size());
    }

    @Test
    public void findByRegion2Test() {
        String region2 = "河南省";
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByProvince(region2);
        System.out.println(creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
        }
    }

    @Test
    public void findByRegion3Test() {
        String region3 = "全国";
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByCity(region3);
        System.out.println("总个数为:"+creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid());
        }
    }


    // 如下的是正确的,但是不符合正确的业务逻辑

    @Test
    public void findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo2Test() {
        Integer adtype = 2;
        Integer positionid = 2;
        Integer region2 = 27;
        Integer region3 = 320;
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo2(adtype, positionid, region2, region3);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getRegion2()+"->"+creativeIndexTotal.getRegion3());
        }
    }


    // 国家-->省-->市
    @Test
    public void findByAdtypeAndPositionnameAndRegion1AndRegion2AndRegion3Test(){
        Integer adtype = 2;
        Integer positionid = 19;
        Integer region2 = 27;
        Integer region3 = 320;
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameAndRegion1AndRegion2AndRegion3(adtype, positionid, region2, region3);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getRegion2()+"->"+creativeIndexTotal.getRegion3());
        }
    }

    // 国家-->省
    @Test
    public void findByAdtypeAndPositionnameAndRegion1AndRegion2Test(){
        Integer adtype = 2;
        Integer positionid = 2;
        Integer region2 = 27;
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameAndRegion1AndRegion2(adtype, positionid, region2);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getRegion2()+"->"+creativeIndexTotal.getRegion3());
        }
    }




    @Test
    public void findByProvinceidOrCityidTest() {
        Integer region2 = 12;
        Integer region3 = 109;
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByProvinceidOrCityid(region2, region3);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getTitle());
        }
    }

    @Test
    public void insertIntoComposeTest() {
        initEsSerive.insertIntoCompose();
    }


    @Test
    public void updateCreativeTitleTest(){
        creativeIndexTotalService.updateCreativeTitle(111L,"2");
    }
}
