package com.tangdou.es2.service;

import com.tangdou.es2.entity.CreativeIndexTotal;

import java.util.List;

public interface CreativeIndexTotalService {


    CreativeIndexTotal createOne(CreativeIndexTotal creativeIndexTotal);

    List<CreativeIndexTotal> findByProvince(String province);

    List<CreativeIndexTotal> findByCity(String city);

    List<CreativeIndexTotal> findByType(Integer adtype);

    List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3(Integer adtype, Integer positionid, String region1, String region2, String region3);

    List<CreativeIndexTotal> findByAdtypeAndPositionidOrRegion2idOrRegion3id(Integer adtype, Integer positionid, Integer provinceid, Integer cityid);


    List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo(Integer adtype, String positionname, String region1, String region2, String region3);

    List<CreativeIndexTotal> findByAdtypeAndPositionnameOrRegion1OrRegion2OrRegion3Demo2(Integer adtype, Integer positionid, Integer region2id, Integer region3id);

    // 中国->省->市
    List<CreativeIndexTotal> findByAdtypeAndPositionnameAndRegion1AndRegion2AndRegion3(Integer adtype, Integer positionid, Integer region2id, Integer region3id);

    // 省
    List<CreativeIndexTotal> findByAdtypeAndPositionnameAndRegion1AndRegion2(Integer adtype, Integer positionid, Integer region2id);


    List<CreativeIndexTotal> findByAdtypeAndPositionnameAndProvinceAndCity(Integer adtype, Integer positionid, String province, String city);

    List<CreativeIndexTotal> findByAdtypeInAndPositionidInAndProvinceInAndCityIn(String adtype, String positionid, String province, String city);

    List<CreativeIndexTotal> findByAdtypeInAndPositionidInAndProvinceInAndCityInAndCreativetypeInAndLocalindustryInAndNetworkInAndClientInAndFrequencyInAndCrowdInAndPriceidIn(
            String adtype, String positionid, String province, String city,String creativetype,String localindustry,String network,String client,String frequency,String crowd,
            String priceid);

    List<CreativeIndexTotal> findByCityInAndProvinceInAndPositionidInAndAdtypeIn(String city, String province, String positionid, String adtype);

    List<CreativeIndexTotal> findByRegion3In(String city);

    List<CreativeIndexTotal> findByRegion3InAAndRegion2In(String city, String province);

    List<CreativeIndexTotal> findByAdtypeIn(String adtype);

    List<CreativeIndexTotal> findByAdtypeInAAndPositionidIn(String adtype, String positionid);


    List<CreativeIndexTotal> findAllData();

    List<CreativeIndexTotal> findByDemo3(Integer adtype, String positionname);

    List<CreativeIndexTotal> findByProvinceidOrCityid(Integer provinceid, Integer cityid);

    List<CreativeIndexTotal> findByAdtypeOrPositionnameOrRegion1OrRegion2OrRegion3(Integer adtype, String positionname, String region1, String region2, String region3);

    void updateCreativeTitle(Long creativeid, String title);


}
