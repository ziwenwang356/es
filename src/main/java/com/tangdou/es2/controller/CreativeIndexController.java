package com.tangdou.es2.controller;

import com.tangdou.es2.dto.PlaceField;
import com.tangdou.es2.dto.PlaceField2;
import com.tangdou.es2.entity.CreativeIndexTotal;
import com.tangdou.es2.service.CreativeIndexTotalService;
import com.tangdou.es2.service.UtilsSerive;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CreativeIndexController {

    @Autowired
    private UtilsSerive utilsSerive;
    @Autowired
    CreativeIndexTotalService creativeIndexTotalService;

    @GetMapping("getSome")
    public ResponseEntity getSome(Integer adtype, Integer positionid, String province, String city) {
        if (StringUtils.isBlank(province)) {
            province = ("北京市".equals(city) || "天津市".equals(city) || "上海市".equals(city) || "重庆市".equals(city)) ? city : province;
        }
        int provinceId = utilsSerive.getProvinceByName(province);
        if (StringUtils.isBlank(city)) {
            // 国家-->省
            List<CreativeIndexTotal> creativeIndexTotalsTwo = creativeIndexTotalService.findByAdtypeAndPositionnameAndRegion1AndRegion2(adtype, positionid, provinceId);
            System.out.println("Number is:  " + creativeIndexTotalsTwo.size());
            for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotalsTwo) {
                System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
            }
            return ResponseEntity.ok(creativeIndexTotalsTwo);
        }
        int cityId = utilsSerive.getCityByName(city);
        // 国家-->省-->市
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameAndRegion1AndRegion2AndRegion3(adtype, positionid, provinceId, cityId);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }


    @PostMapping("getSomeAnotherVersion")
    public ResponseEntity getSomeAnothers(@RequestBody PlaceField placeField) {
        Integer adtype = placeField.getAdtype();
        Integer positionid = placeField.getPositionid();
        String province = placeField.getProvince();
        String city = placeField.getCity();
        if (StringUtils.isBlank(province)) {
            province = ("北京市".equals(city) || "天津市".equals(city) || "上海市".equals(city) || "重庆市".equals(city)) ? city : province;
        }
        int provinceId = utilsSerive.getProvinceByName(province);
        if (StringUtils.isBlank(city)) {
            // 国家-->省
            List<CreativeIndexTotal> creativeIndexTotalsTwo = creativeIndexTotalService.findByAdtypeAndPositionnameAndRegion1AndRegion2(adtype, positionid, provinceId);
            System.out.println("Number is:  " + creativeIndexTotalsTwo.size());
            for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotalsTwo) {
                System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
            }
            return ResponseEntity.ok(creativeIndexTotalsTwo);
        }
        int cityId = utilsSerive.getCityByName(city);
        // 国家-->省-->市
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameAndRegion1AndRegion2AndRegion3(adtype, positionid, provinceId, cityId);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }

    @PostMapping("getAnother2Version")
    public ResponseEntity getAnother2(@RequestBody PlaceField placeField) {
        Integer adtype = placeField.getAdtype();
        Integer positionid = placeField.getPositionid();
        String province = placeField.getProvince();
        String city = placeField.getCity();
        if (StringUtils.isBlank(province)) {
            province = ("北京市".equals(city) || "天津市".equals(city) || "上海市".equals(city) || "重庆市".equals(city)) ? city : province;
        }
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeAndPositionnameAndProvinceAndCity(adtype, positionid, province, city);
        return ResponseEntity.ok(creativeIndexTotals);
    }

    // 如下目前是可以的，支持adtype,positionid,province,city这四个维度的查询
    @PostMapping("getAnother3Version")
    public ResponseEntity getAnother3(@RequestBody PlaceField2 placeField2) {
        String adtype = placeField2.getAdtype();//Integer
        String positionid = placeField2.getPositionid();//Integer
        String province = placeField2.getProvince();
        String city = placeField2.getCity();
        String creativetype = placeField2.getCreativetype();//Integer
        String localindustry = placeField2.getLocalindustry();//Integer
        String network = placeField2.getNetwork();//Integer
        String client = placeField2.getClient();//Integer
        String frequency = placeField2.getFrequency();//Integer
        String crowd = placeField2.getCrowd();//Integer
        String priceid = placeField2.getPriceid();//Integer

        if (StringUtils.isBlank(province)) {
            province = ("北京市".equals(city) || "天津市".equals(city) || "上海市".equals(city) || "重庆市".equals(city)) ? city : province;
        }
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeInAndPositionidInAndProvinceInAndCityIn(adtype, positionid, province, city);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }


    @PostMapping("getAnother4Version")
    public ResponseEntity getAnother4(@RequestBody PlaceField2 placeField2) {
        String adtype = placeField2.getAdtype();
        String positionid = placeField2.getPositionid();
        String province = placeField2.getProvince();
        String city = placeField2.getCity();
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByCityInAndProvinceInAndPositionidInAndAdtypeIn(city, province, positionid, adtype);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }


    @PostMapping("getAnother5Version")
    public ResponseEntity getAnother5(@RequestBody PlaceField2 placeField2) {
        String city = placeField2.getCity();
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByRegion3In(city);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }

    @PostMapping("getAnother6Version")
    public ResponseEntity getAnother6(@RequestBody PlaceField2 placeField2) {
        String province = placeField2.getProvince();
        String city = placeField2.getCity();
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByRegion3InAAndRegion2In(city, province);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }

    @PostMapping("getAnother7Version")
    public ResponseEntity getAnother7(@RequestBody PlaceField2 placeField2) {
        String adtype = placeField2.getAdtype();
        String positionid = placeField2.getPositionid();
        String province = placeField2.getProvince();
        String city = placeField2.getCity();
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeIn(adtype);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }

    @GetMapping("getAll")
    public ResponseEntity getAll() {
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findAllData();
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }

    @PostMapping("getAnother8Version")
    public ResponseEntity getAnother8(@RequestBody PlaceField2 placeField2) {
        String adtype = placeField2.getAdtype();
        String positionid = placeField2.getPositionid();
        String province = placeField2.getProvince();
        String city = placeField2.getCity();
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeInAAndPositionidIn(adtype, positionid);
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }


    // 这块已经好了,支持adtype,positionid,province,city,creativetype,localindustry,network,client,frequency,crowd,priceid这十一个维度的查询
    @PostMapping("getAnother9Version")
    public ResponseEntity getAnother9(@RequestBody PlaceField2 placeField2) {
        String adtype = placeField2.getAdtype();//
        String positionid = placeField2.getPositionid();
        String province = placeField2.getProvince();
        String city = placeField2.getCity();
        String creativetype = placeField2.getCreativetype();
        String localindustry = placeField2.getLocalindustry();
        String network = placeField2.getNetwork();
        String client = placeField2.getClient();
        String frequency = placeField2.getFrequency();
        String crowd = placeField2.getCrowd();
        String priceid = placeField2.getPriceid();

        if (StringUtils.isBlank(province)) {
            province = ("北京市".equals(city) || "天津市".equals(city) || "上海市".equals(city) || "重庆市".equals(city)) ? city : province;
        }
        List<CreativeIndexTotal> creativeIndexTotals = creativeIndexTotalService.findByAdtypeInAndPositionidInAndProvinceInAndCityInAndCreativetypeInAndLocalindustryInAndNetworkInAndClientInAndFrequencyInAndCrowdInAndPriceidIn(
                adtype, positionid, province, city, creativetype, localindustry, network, client, frequency, crowd, priceid
        );
        System.out.println("Number is:  " + creativeIndexTotals.size());
        for (CreativeIndexTotal creativeIndexTotal : creativeIndexTotals) {
            System.out.println(creativeIndexTotal.getCreativeid() + ":   " + creativeIndexTotal.getAdtype() + "->" + creativeIndexTotal.getPositionid() + "->" + creativeIndexTotal.getRegion2() + "->" + creativeIndexTotal.getRegion3());
        }
        return ResponseEntity.ok(creativeIndexTotals);
    }


}
